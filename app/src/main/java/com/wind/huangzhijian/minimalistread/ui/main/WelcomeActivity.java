package com.wind.huangzhijian.minimalistread.ui.main;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wind.huangzhijian.minimalistread.Component.ImageLoader;
import com.wind.huangzhijian.minimalistread.ui.main.activity.MainActivity;
import com.wind.huangzhijian.minimalistread.R;
import com.wind.huangzhijian.minimalistread.base.BaseActivity;
import com.wind.huangzhijian.minimalistread.module.bean.WelcomeBean;
import com.wind.huangzhijian.minimalistread.presenter.WelcomePresenter;
import com.wind.huangzhijian.minimalistread.presenter.contract.WelcomeContract;

import butterknife.BindView;

/**
 * Created by huangzhijian on 2017/3/7.
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View{

    @BindView(R.id.welcome_mg)
    ImageView welcome_mg;
    @BindView(R.id.welcome_text)
    TextView welcome_text;

    @Override
    protected void initEventAndData() {
        mpresenter.getWelcomeData();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
        ImageLoader.load(this,welcomeBean.getImg(),welcome_mg);
        welcome_mg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        welcome_text.setText(welcomeBean.getText());
    }

    @Override
    public void JumpToMain() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        Glide.clear(welcome_mg);
        super.onDestroy();
    }
}
