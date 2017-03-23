package com.wind.huangzhijian.minimalistread.ui.main.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SearchEvent;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.wind.huangzhijian.minimalistread.APP.App;
import com.wind.huangzhijian.minimalistread.APP.Constants;
import com.wind.huangzhijian.minimalistread.Component.RxBus;
import com.wind.huangzhijian.minimalistread.R;
import com.wind.huangzhijian.minimalistread.Util.SharedPreferenceUtil;
import com.wind.huangzhijian.minimalistread.base.BaseActivity;
import com.wind.huangzhijian.minimalistread.presenter.MainPresenter;
import com.wind.huangzhijian.minimalistread.presenter.contract.MainContract;
import com.wind.huangzhijian.minimalistread.ui.zhihu.fragment.ZhihuFragment;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.view_search)
    MaterialSearchView mSearchView;


    @Inject
    ZhihuFragment zhihuFragment;

    MenuItem mLastMenuItem;
    MenuItem mSearchMenuItem;
    ActionBarDrawerToggle mDrawerToggle;

    private int hideFragment = Constants.TYPE_ZHIHU;
    private int showFragment = Constants.TYPE_ZHIHU;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            showFragment = Constants.TYPE_ZHIHU;
            hideFragment = Constants.TYPE_ZHIHU;
            showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
            mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(false);
            mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
            hideFragment = showFragment;
        }
    }

    private SupportFragment getTargetFragment(int item){
        switch (item){
            case Constants.TYPE_ZHIHU:
                return zhihuFragment;
        }
        return zhihuFragment;
    }

    private int getCurrentItem(int item){
        switch (item){
            case Constants.TYPE_ZHIHU:
                return R.id.drawer_zhihu;
        }
        return R.id.drawer_zhihu;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar,"知乎日报");
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mLastMenuItem=mNavigationView.getMenu().findItem(R.id.drawer_zhihu);
        loadMultipleRootFragment(R.id.main_content,0,zhihuFragment);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_zhihu:
                        showFragment=Constants.TYPE_ZHIHU;
                        mSearchMenuItem.setVisible(false);
                        break;
                }
                if(mLastMenuItem != null){
                    mLastMenuItem.setChecked(false);
                }
                mLastMenuItem=item;
                item.setChecked(true);
                mToolbar.setTitle(item.getTitle());
                mDrawerLayout.closeDrawer(mNavigationView);
                showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
                hideFragment = showFragment;
                return true;
            }
        });
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //掘金还没写，留一个搜索栏
//                if(showFragment == Constants.TYPE_GANK) {
//                    mGankFragment.doSearch(query);
//                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            showExitDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        mSearchView.setMenuItem(item);
        mSearchMenuItem = item;
        return true;
    }

    private void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出minimalistRead吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showupdateDialog(String versionContent) {

    }

    @Override
    public void startDownloadService() {

    }

    @Override
    public void showError(String msg) {

    }
}
