package cn.cyber.weblogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TelActivity extends AppCompatActivity {


    Context context;
    @BindView(R.id.progressbar_main)
    ProgressBar progressbarMain;
    @BindView(R.id.webview_main)
    WebView webviewMain;
    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_do)
    AppCompatButton btnDo;
    @BindView(R.id.ipAdress)
    EditText ipAdress;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back_main)
    ImageView btnBackMain;
    @BindView(R.id.btn_more_main)
    ImageView btnMoreMain;
    @BindView(R.id.titlebar_bg)
    FrameLayout titlebarBg;

    String url = "http://cy-ber.cn/";
    String authurl = "http://219.136.125.139/portalReceiveAction.do?wlanacname=gzucm&wlanuserip=";
    String ip = "";
    String ua = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
    String uaTest = "http://ie.icoa.cn/";
    Boolean startFlag = false;

    String username = "";
    String passwordtext = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel);
        ButterKnife.bind(this);
        context = getApplicationContext();
        tvTitle.setText("电信网页认证");

        WebSettings settings = webviewMain.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUserAgentString(ua);
        loadUrl(url);
        passwordtext = getMyPreferences();
        ipAdress.setText(ip);
        user.setText(username);
        password.setText(passwordtext);
        webviewMain.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 85) {
                    progressbarMain.setProgress(100);
                } else {
                    progressbarMain.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        webviewMain.setWebViewClient(new myWebClient());
    }


    void loadUrl(String string) {
        webviewMain.loadUrl(string);
    }

    private static Toast mToast;

    public void toast(String msg) {

        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    @OnClick({R.id.user, R.id.password, R.id.btn_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user:
                break;
            case R.id.password:
                break;
            case R.id.btn_do: {//--------------按钮
                startFlag = true;
                if (ipAdress.getText().equals("")) {
                    toast("无法获取ip，请手动输入");
                    return;
                }
                loadUrl(authurl + ip);
            }
            break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webviewMain.canGoBack()) {
                webviewMain.goBack();
                return true;
            } else return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.tv_title)
    public void onTvTitleClicked() {
    }

    @OnClick(R.id.btn_back_main)
    public void onBtnBackMainClicked() {
        finish();
    }

    @OnClick(R.id.btn_more_main)
    public void onBtnMoreMainClicked() {
        webviewMain.reload();

    }

    @OnClick(R.id.titlebar_bg)
    public void onTitlebarBgClicked() {
    }

    public class JsInteration extends Object {
        @JavascriptInterface
        public void sendEnter() {
            Log.e(" !!!", "send succeed");
            webviewMain.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
            webviewMain.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
        }
    }

    public void setMyPreferences(String ip, String userID, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("userID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("ip", ip);
        editor.putString("username", userID);
        editor.putString("password", password);
        editor.commit();
    }

    public String getMyPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("userID", Context.MODE_PRIVATE);
        ip = sharedPreferences.getString("ip", "");
        username = sharedPreferences.getString("username", "");
        passwordtext = sharedPreferences.getString("password", "");

        return passwordtext;
    }

    public class myWebClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            username = user.getText().toString();
            passwordtext = password.getText().toString();
            String urlNow = webviewMain.getUrl();
            try {
                boolean a = url.contains("219.136.125.139");

                String[] urls;
                urls = urlNow.split("wlanuserip=");
                if (urls != null && a) {
                    if ((urls.length) == 1) {
                        return;
                    }
                    ip = urls[urls.length - 1];
                    toast("已跳转到认证页面,ip为：" + ip);
                    ipAdress.setText(ip);
                    webviewMain.loadUrl("javascript:setTimeout( function(){ try{ var t = document.getElementById('useridtemp');t.focus();t.select();t.value='" + username + "'}catch(e){}},300);");
                    webviewMain.loadUrl("javascript:setTimeout( function(){ try{ var t2 = document.getElementById('passwd');t2.focus();t2.select();t2.value='" + passwordtext + "';t2.onsubmit();t2.submit();}catch(e){}},400);");
                    webviewMain.loadUrl("javascript:setTimeout( function(){ control.sendEnter();},600);");
                    setMyPreferences(ip, username, passwordtext);
                } else {
                    if (a) {
                        toast("无法获取ip，请手动输入");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.e(e.getMessage());
            }


        }
    }

    @Override
    protected void onDestroy() {
        webviewMain.clearCache(true);
        webviewMain.destroy();
        super.onDestroy();
    }
}
