package test.bwie.com.jinrtt.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import test.bwie.com.jinrtt.R;
import test.bwie.com.jinrtt.bean.GsonBean;

/**
 * @ Description:ListView适配器类
 * @ Date:2017/4/10
 * @ Author:刘刚
 */

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private ImageOptions options;
    private List<GsonBean.ResultBean.DataBean>list;

    public MyAdapter(List<GsonBean.ResultBean.DataBean> list, Context context) {
        this.list = list;
        mContext = context;
        options=new ImageOptions.Builder().setFadeIn(true).setLoadingDrawableId(R.mipmap.ic_launcher)
                .setCrop(true).setSize(240,190).setUseMemCache(true).build();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){   //优化View视图
            convertView=View.inflate(mContext, R.layout.item,null);
            holder=new ViewHolder();  //根据ID找控件
            x.view().inject(holder,convertView);
          convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv1.setText(list.get(position).getTitle()+"");
        holder.tv2.setText(list.get(position).getAuthor_name()+"");
        //ImageLoader.getInstance().displayImage(list.get(position).getPicUrl()+"",holder.image,ImageEmpty.imageOptions(R.mipmap.ic_launcher));
        x.image().bind(holder.image, list.get(position).getThumbnail_pic_s(),options);

        return convertView;
    }

    class  ViewHolder{
        @ViewInject(R.id.textView)
        private TextView tv1;
        @ViewInject(R.id.textView2)
        private  TextView tv2;
        @ViewInject(R.id.image)
        private  ImageView image;


    }
}
