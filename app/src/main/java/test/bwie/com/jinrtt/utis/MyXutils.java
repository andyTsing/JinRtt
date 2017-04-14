package test.bwie.com.jinrtt.utis;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import test.bwie.com.jinrtt.AppApplication;
import test.bwie.com.jinrtt.adapter.MyPagerAdapter;
import test.bwie.com.jinrtt.topnewgrid.bean.ChannelItem;
import test.bwie.com.jinrtt.topnewgrid.bean.ChannelManage;


/**
 * @ Description:
 * @ Date:2017/4/13
 * @ Author:刘刚
 */

public class MyXutils {
    private DbManager db;
    private FragmentManager fm;
    private ViewPager vp;
    private TabLayout tablay;
    private  Context mContext;
    private ProgressDialog mDialog;
    private ArrayList<ChannelItem> userChannelList=new ArrayList<>();


    //接收构造方法初始化的DbManager对象
    public MyXutils(ViewPager vp, TabLayout tablay, FragmentManager fm, Context context) {
        this.vp = vp;
        this.tablay = tablay;
        this.mContext=context;
        this.fm=fm;
        db = DatabaseOpenHelper.getInstance();
    }






    public  void getXutil(String uri){

        mDialog = new ProgressDialog(mContext, ProgressDialog.STYLE_SPINNER).show(mContext, "正在加载", "网络已连接");
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
        RequestParams params = new RequestParams(uri);
        Log.d("zzz",params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {

            private MyPagerAdapter mAdapter;

            public void onSuccess(String result) {
                userChannelList = (ArrayList<ChannelItem>) ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).getUserChannel();
                String[] tablist = new String[userChannelList.size()];

                for (int i = 0; i <userChannelList.size() ; i++) {
                    tablist[i]= userChannelList.get(i).getName();

                }
                String [] pathlist=new String[tablist.length];
                for(int i=0;i<userChannelList.size();i++) {
                    switch (userChannelList.get(i).getName()) {
                        case "头条":
                            pathlist[i] = "tt";
                            break;
                        case "社会":
                            pathlist[i] = "shehui";
                            break;
                        case "国际":
                            pathlist[i] = "gj";
                            break;
                        case "国内":
                            pathlist[i] = "gn";
                            break;
                        case "娱乐":
                            pathlist[i] = "yl";
                            break;
                        case "体育":
                            pathlist[i] = "ty";
                            break;
                        case "军事":
                            pathlist[i] = "tt";
                            break;
                        case "科技":
                            pathlist[i] = "kj";
                            break;
                        case "财经":
                            pathlist[i] = "cj";
                            break;
                        case "时尚":
                            pathlist[i] = "ss";
                            break;
                        case "八卦":
                            pathlist[i] = "bg";
                           break;
                    }

                }
                mAdapter = new MyPagerAdapter(fm, tablist, pathlist);
                 vp.setAdapter(mAdapter);
                tablay.setTabMode(TabLayout.MODE_SCROLLABLE);
                tablay.setTabTextColors(Color.GRAY,Color.RED);
                tablay.setSelectedTabIndicatorColor(Color.TRANSPARENT);
                tablay.setupWithViewPager(vp);

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
                mDialog.dismiss();
            }
        });
    }
}
