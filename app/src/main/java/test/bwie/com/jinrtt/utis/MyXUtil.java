package test.bwie.com.jinrtt.utis;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import test.bwie.com.jinrtt.adapter.MyAdapter;
import test.bwie.com.jinrtt.bean.GsonBean;
import test.bwie.com.jinrtt.bean.UrL;
import test.bwie.com.jinrtt.utilcode.Utils;

/**
 * @ Description:
 * @ Date:2017/4/10
 * @ Author:刘刚
 */

public class MyXUtil {

    private ListView mLv;
    private Context mContext;
    private ProgressDialog mDialog;
    private List<GsonBean.ResultBean.DataBean> list;
    public MyXUtil(Context context, ListView lv) {
        mContext = context;
        mLv = lv;
    }

    public  void getXutil(String uri){
        RequestParams params = new RequestParams(UrL.url1);
        params.addQueryStringParameter("uri",uri);
        ///System.out.println(params);
        Log.d("zzz",params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {




            @Override
            public void onSuccess(String result) {
                //解析result

                    Gson gson = new Gson();
                    GsonBean gsonBean = gson.fromJson(result, GsonBean.class);
                GsonBean.ResultBean bean = gsonBean.getResult();
                List<GsonBean.ResultBean.DataBean> data = bean.getData();
                Log.d("zzzzz",data.toString());
                            mLv.setAdapter(new MyAdapter(data, Utils.getContext()));

                    }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
}
