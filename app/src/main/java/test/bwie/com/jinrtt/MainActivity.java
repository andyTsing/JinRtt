package test.bwie.com.jinrtt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import test.bwie.com.jinrtt.topnewgrid.ChannelActivity;
import test.bwie.com.jinrtt.utis.MyXutils;

public class MainActivity extends AppCompatActivity {


    private int theme = R.style.AppTheme;
    /** 自定义HorizontalScrollView */
    LinearLayout mRadioGroup_content;
    LinearLayout ll_more_columns;
    RelativeLayout rl_column;
    private ViewPager mViewPager;
    private ImageView button_more_columns;
    /** 新闻分类列表*/
  //  private ArrayList<NewsClassify> newsClassify=new ArrayList<NewsClassify>();
    /** 当前选中的栏目*/
    private int columnSelectIndex = 0;
    /** 左阴影部分*/
    public ImageView shade_left;
    /** 右阴影部分 */
    public ImageView shade_right;
    /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    protected SlidingMenu side_drawer;

    /** head 头部 的中间的loading*/
    private ProgressBar top_progress;
    /** head 头部 中间的刷新按钮*/
    private ImageView top_refresh;
    /** head 头部 的左侧菜单 按钮*/
    private ImageView top_head;
    /** head 头部 的右侧菜单 按钮*/
    private ImageView top_more;
    private ViewPager vp;
    private TabLayout tablay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_main);
        initView();
        initSildMenu();
        NetWorkStatus();
        initData();

    }
    private void initData() {


        new MyXutils(vp,tablay,getSupportFragmentManager(),MainActivity.this).getXutil("http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news");
    }
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    private boolean NetWorkStatus() {
        boolean netSataus = isNetworkConnected(this);



        if (!netSataus) {
            AlertDialog.Builder b = new AlertDialog.Builder(this).setTitle("没有可用的网络")
                    .setMessage("是否对网络进行设置？");

            b.setPositiveButton("是", new DialogInterface.OnClickListener() {

                private ProgressDialog mDialog;

                public void onClick(DialogInterface dialog, int whichButton) {
                    if(android.os.Build.VERSION.SDK_INT > 10 ){
                        //3.0以上打开设置界面
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }else
                    {
                        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                    }

                }
            }).setNeutralButton("否", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            }).show();
        }

        return netSataus;
    }

    private void initView() {
        button_more_columns = (ImageView) findViewById(R.id.button_more_columns);
        top_head = (ImageView) findViewById(R.id.top_head);
        top_more = (ImageView) findViewById(R.id.top_more);
        vp = (ViewPager) findViewById(R.id.mViewPager);
        tablay = (TabLayout) findViewById(R.id.tab);
        top_refresh = (ImageView) findViewById(R.id.top_refresh);
        top_progress = (ProgressBar) findViewById(R.id.top_progress);

        button_more_columns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
                startActivity(intent);
            }
        });
        top_head.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(side_drawer.isMenuShowing()){
                    side_drawer.showContent();
                }else{
                    side_drawer.showMenu();
                }
            }
        });
        top_more.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                MainActivity.this.recreate();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }
    private void initSildMenu() {
        side_drawer = new SlidingMenu(this);
        WindowManager wm = getWindowManager() ;
        DisplayMetrics displayMetrics = new DisplayMetrics() ;
        wm.getDefaultDisplay() .getMetrics(displayMetrics );
        side_drawer.setBehindWidth( displayMetrics.widthPixels / 5*4);

        //设置侧滑模式为向左侧滑
        side_drawer.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        side_drawer.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        side_drawer.setShadowWidthRes(R.dimen.shadow_width);
        side_drawer.setShadowDrawable(R.color.colorAccent);

        // 设置滑动菜单视图的宽度

        side_drawer.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        side_drawer.setFadeDegree(0.65f);

        side_drawer.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        side_drawer.setMenu(R.layout.left_drawer_fragment);

        TextView login_more = (TextView)side_drawer.findViewById(R.id.login_more);
        ImageView phone_btn= (ImageView) side_drawer.findViewById(R.id.phone_btn);
         login_more.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, Login.class);
                 startActivity(intent);
             }
         });
         phone_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, PhoneLogin.class);
                 startActivity(intent);
             }
         });
    }
}
