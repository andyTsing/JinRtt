package test.bwie.com.jinrtt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import test.bwie.com.jinrtt.utis.MyXUtil;

/**
 * @ Description:业务操作类
 * @ Date:2017/4/10
 * @ Author:刘刚
 */
@ContentView(R.layout.lv)
public class UserFragment extends Fragment {
   @ViewInject(R.id.lv)
    private ListView mLv;
    public String category;

 /*   //自定义静态方法添加Fragment
    public  static UserFragment getFragment(String uri){
        UserFragment userFragment = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uri",uri);    //利用Bundle传值
        userFragment.setArguments(bundle);
        return  userFragment;
    }*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


       /* MyAsyncTask myAsyncTask = new MyAsyncTask(mLv, getActivity()); //异步加载网络数据
        myAsyncTask.execute(uri);*/
        MyXUtil myXUtil = new MyXUtil(getActivity(), mLv);
        myXUtil.getXutil(category);
    }
}
