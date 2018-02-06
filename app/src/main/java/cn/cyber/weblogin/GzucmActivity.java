package cn.cyber.weblogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cyber.weblogin.utils.StringUtils;
import cn.cyber.weblogin.utils.ToastUtil;

public class GzucmActivity extends AppCompatActivity {

    @BindView(R.id.ll_gzucmbg)
    LinearLayout llGzucmbg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back_main)
    ImageView btnBackMain;
    @BindView(R.id.btn_more_main)
    ImageView btnMoreMain;
    @BindView(R.id.titlebar_bg)
    FrameLayout titlebarBg;
    @BindView(R.id.edt_url)
    EditText edtUrl;
    @BindView(R.id.btn_go)
    AppCompatButton btnGo;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.ipAdress)
    EditText ipAdress;
    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_do)
    AppCompatButton btnDo;
    @BindView(R.id.progressbar_main)
    ProgressBar progressbarMain;

    private Context context;
    String ip = "";
    String url = "http://cy-ber.cn/";
    String ua = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
    String uaTest = "http://ie.icoa.cn/";
    String username = "";
    String passwordtext = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzucm);
        ButterKnife.bind(this);
        context = getApplicationContext();
        initView();
    }

    private void initView() {
        tvTitle.setText("广中医网页认证(广州热点计费)");
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUserAgentString(ua);
        webview.setWebViewClient(new myWebClient());
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.e("jsLog", cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId());
                return false;
            }

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
        webview.loadUrl(url);
        passwordtext = getMyPreferences();
        ipAdress.setText(ip);
        user.setText(username);
        password.setText(passwordtext);
        edtUrl.setCursorVisible(false);
        edtUrl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    edtUrl.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });
    }

    public class myWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            edtUrl.setText(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                edtUrl.setText(request.getUrl().toString());
            }
            edtUrl.setCursorVisible(false);
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            edtUrl.setCursorVisible(false);
            username = user.getText().toString();
            passwordtext = password.getText().toString();
            String urlNow = webview.getUrl();
            try {
                boolean a = url.contains("10.50.2.2/a70");

                if (a) {
                    ip = StringUtils.getValueByName(url, "wlanuserip");
                    if (!TextUtils.isEmpty(ip)) {

                        ToastUtil.show("已跳转到认证页面,ip为：" + ip);
                        ipAdress.setText(ip);
                        webview.loadUrl("javascript:setTimeout( function(){ try{ f3.DDDDD.value='" + username + "'}catch(e){}},200);");
                        webview.loadUrl("javascript:setTimeout( function(){ try{ f3.upass.value ='" + passwordtext + "';f3.onsubmit();}catch(e){}},300);");

                        setMyPreferences(ip, username, passwordtext);
                    } else {
                        ToastUtil.show("无法获取ip，请手动输入或刷新");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(" ", e.getMessage());
            }


        }
    }


    public void setMyPreferences(String ip, String userID, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("userID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("ip", ip);
        editor.putString("username", userID);
        editor.putString("password2", password);
        editor.commit();
    }

    public String getMyPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("userID", Context.MODE_PRIVATE);
        ip = sharedPreferences.getString("ip", "");
        username = sharedPreferences.getString("username", "");
        passwordtext = sharedPreferences.getString("password2", "");

        return passwordtext;
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
        webview.reload();
    }

    @OnClick(R.id.titlebar_bg)
    public void onTitlebarBgClicked() {
    }

    @OnClick(R.id.edt_url)
    public void onEdtUrlClicked() {
    }

    @OnClick(R.id.btn_go)
    public void onBtnGoClicked() {
        webview.loadUrl(edtUrl.getText().toString().trim());
    }


    @OnClick(R.id.btn_do)
    public void onBtnDoClicked() {
        if (ipAdress.getText().equals("")) {
            ToastUtil.show("无法获取ip，请手动输入");
            return;
        }
        ip = ipAdress.getText().toString().trim();
        webview.loadUrl("http://10.50.2.2/a70.htm?wlanuserip=" + ip + "&wlanacip=null&wlanacname=null&vlanid=0&" + ip + "&ssid=null&areaID=null&mac=00-00-00-00-00-00");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {
                webview.goBack();
                return true;
            } else return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        webview.clearCache(true);
        webview.destroy();
        super.onDestroy();
    }
}
