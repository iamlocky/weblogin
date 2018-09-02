package cn.cyber.weblogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cyber.weblogin.utils.StartActivityUtil;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.btn_gzucm)
    AppCompatButton btnGzucm;
    @BindView(R.id.btn_tel)
    AppCompatButton btnTel;
    @BindView(R.id.btn_back_main)
    ImageView btnBackMain;
    @BindView(R.id.btn_more_main)
    ImageView btnMoreMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        btnMoreMain.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_gzucm)
    public void onBtnGzucmClicked() {
        StartActivityUtil.start(getApplicationContext(), GzucmActivity.class);
    }

    @OnClick(R.id.btn_tel)
    public void onBtnTelClicked() {
        StartActivityUtil.start(getApplicationContext(), TelActivity.class);
    }

    @OnClick(R.id.btn_back_main)
    public void onBtnBackMainClicked() {
        finish();
    }


}
