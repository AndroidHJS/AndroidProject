package com.jack.UI;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.UI.adapter.MainAdapter;
import com.jack.UI.base.BaseActivity;
import com.jack.UI.base.BaseFragment;
import com.jack.UI.fragment.GanioFragment;
import com.jack.UI.fragment.InternetSafeFragment;
import com.jack.UI.fragment.MeiZiFragment;
import com.jack.UI.fragment.NewFragment;
import com.jack.View.CircleImageView;
import com.jack.global.Constant;
import com.jack.global.UserInfo;
import com.jack.login.Base.User;
import com.jack.login.LoginActivity;
import com.jack.main.R;
import com.jack.utils.BoomButtonUtils;
import com.jack.utils.ToastUtils;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListenerAdapter;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tabs)
    TabLayout mMainTayLayout;
    @Bind(R.id.viewpager)
    ViewPager mMainViewPager;
    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.Layout_drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.tv_zhi_hu)
    TextView  mTvZhiHu;
    @Bind(R.id.tv_gan_huo)
    TextView mTvGanHuo;
    @Bind(R.id.layout_ganhuo_content)
    RelativeLayout mLayoutGanHuoContainer;
    @Bind(R.id.boom_button)
    BoomMenuButton mBoomButton;
    @Bind(R.id.layout_gan_huo)
    FrameLayout mLayoutGanHuo;
    @Bind(R.id.img_avatar)
    CircleImageView mImgAvatar;
    @Bind(R.id.tv_login)
    TextView  mUserName;
    private UserInfo mUserInfo;
    private static final int  PAGE_ZHI_HU= 511;
    private static final  int  PAGE_GAN_HU0= 999;
    private  static  int mCurrentPage=PAGE_ZHI_HU;
    private  static  int mCurrentPos=0;
    private MainAdapter mMainAdapter;
    private String[] mTabs=new String[]{
            "新闻","互联网安全","体育","音乐"
    };
    private String[] mGanHuoTab=new String[]{
            "Android","前端","iOS","妹子"
    };
    private BaseFragment[] mZhiHuFragments =new BaseFragment[] {
            new NewFragment(),
            InternetSafeFragment.getIntance(Constant.ZhiHuCategory.InternetSaFe),
            InternetSafeFragment.getIntance(Constant.ZhiHuCategory.Sports),
            InternetSafeFragment.getIntance(Constant.ZhiHuCategory.Music),
    };
    private BaseFragment[] mGanHuoFragments=new BaseFragment[]{
            GanioFragment.getIntance("Android"),
            GanioFragment.getIntance("前端"),
            GanioFragment.getIntance("iOS"),
            new MeiZiFragment()
    };
    private  BaseFragment mCurrentGanhuoFragment=mGanHuoFragments[0];
    private  Toolbar.OnMenuItemClickListener mMenuItemListener=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_search:
                     showToast("搜索");
                    break;
                case R.id.action_settings:
                    showToast("设置");
                    break;
                case R.id.action_share:
                    showToast("分享");
                    break;
                case R.id.action_aboutMe:
                    showToast("关于我");
                    break;
            }
            return false;
        }
    };

    private View.OnClickListener   mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_gan_huo:
                    mCurrentPage=PAGE_GAN_HU0;
                    switchPage(mCurrentPage);
                    mToolBar.setTitle(mGanHuoTab[mCurrentPos]);
                    if(!mCurrentGanhuoFragment.isAdded()){
                        mFragmentManager.beginTransaction().add(R.id.layout_gan_huo,mCurrentGanhuoFragment).commit();
                        mCurrentGanhuoFragment.setUserVisibleHint(true);
                    }
                    break;
                case R.id.tv_zhi_hu:
                    mCurrentPage=PAGE_ZHI_HU;
                    switchPage(mCurrentPage);
                    mToolBar.setTitle("知乎");
                    break;
                 default:
                     mDrawerLayout.openDrawer(Gravity.LEFT);
            }

        }
    };

    private FragmentManager mFragmentManager;
    @Override
    public void initData() {
        if(mUserInfo.isLogin()){
            User user = mUserInfo.readUser();
            mUserName.setText(user.getNickname());
            Glide.with(this).load(user.getHeadImageUrl()).into(mImgAvatar);
        }
        if(mCurrentPage==PAGE_ZHI_HU){
            switchPage(mCurrentPage);
            mToolBar.setTitle("知乎");
        }else if(mCurrentPage==PAGE_GAN_HU0){
            switchPage(mCurrentPage);
            if(!mCurrentGanhuoFragment.isAdded()){
                mFragmentManager.beginTransaction().add(R.id.layout_gan_huo,mCurrentGanhuoFragment).commit();
                mCurrentGanhuoFragment.setUserVisibleHint(true);
            }
        }
    }
    @Override
    public void init(){
        mFragmentManager=this.getSupportFragmentManager();
        setSupportActionBar(mToolBar);
        mUserInfo=UserInfo.getInstance();
        mMainAdapter= new MainAdapter(mFragmentManager,mTabs, mZhiHuFragments);
        mMainViewPager.setAdapter(mMainAdapter);
        mMainTayLayout.setupWithViewPager(mMainViewPager);
        mMainTayLayout.setTabMode(TabLayout.MODE_FIXED);
        switchPage(mCurrentPage);
        initBoomButton();
        initEvent();

    }

    //初始化菜单
    private  void  initEvent(){
        mToolBar.setOnMenuItemClickListener(mMenuItemListener);
        mToolBar.setNavigationOnClickListener(mClickListener);
        mTvZhiHu.setOnClickListener(mClickListener);
        mTvGanHuo.setOnClickListener(mClickListener);
    }

    /**
     * 初始化干货菜单
     */
    private  void initBoomButton(){
        mBoomButton.setButtonEnum(ButtonEnum.Ham);
        mBoomButton.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        mBoomButton.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        for (int i = 0; i <mBoomButton.getPiecePlaceEnum().pieceNumber() ; i++) {
            mBoomButton.addBuilder(BoomButtonUtils.getHamButtonBuilder(i));
        }
        mBoomButton.setOnBoomListener(new OnBoomListenerAdapter (){
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                mCurrentPos=index;
                BaseFragment baseFragment=mGanHuoFragments[index];
                switchPageGanHuo(baseFragment);
                mToolBar.setTitle(mGanHuoTab[index]);
            }
        });

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    private  void  switchPage(int page){
        switch (page){
            case PAGE_ZHI_HU:
                mMainTayLayout.setVisibility(View.VISIBLE);
                mLayoutGanHuoContainer.setVisibility(View.GONE);
                mMainViewPager.setVisibility(View.VISIBLE);
                break;
            case PAGE_GAN_HU0:
                mMainTayLayout.setVisibility(View.GONE);
                mMainViewPager.setVisibility(View.GONE);
                mLayoutGanHuoContainer.setVisibility(View.VISIBLE);
                break;
        }
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }
   private void switchPageGanHuo(BaseFragment fragment){
       if(mCurrentGanhuoFragment==fragment){
          return;
       }
       if(!fragment.isAdded()){
           mFragmentManager.beginTransaction().add(R.id.layout_gan_huo,fragment).hide(mCurrentGanhuoFragment).commit();
           fragment.setUserVisibleHint(true);
       }else{
           mFragmentManager.beginTransaction().show(fragment).hide(mCurrentGanhuoFragment).commit();
       }
       if(mCurrentGanhuoFragment!=fragment){
           mCurrentGanhuoFragment=fragment;
       }
       mCurrentGanhuoFragment=fragment;

   }
    @OnClick({R.id.img_avatar,R.id.tv_login})
    public  void enterLoging(View view){
        if (UserInfo.getInstance().isLogin()){
            ToastUtils.showToast("已经登录");
            return;
        }
        ToastUtils.showToast("点击登录");
        Intent intent=new Intent(this,LoginActivity.class);
        startActivityForResult(intent,200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==200){
            User user= (User) data.getSerializableExtra("user");

        }
    }
}
