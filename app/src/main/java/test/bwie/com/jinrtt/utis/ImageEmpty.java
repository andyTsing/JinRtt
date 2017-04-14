package test.bwie.com.jinrtt.utis;

import org.xutils.image.ImageOptions;

/**
 * @ Description:加载默认图片工具类
 * @ Date:2017/4/10
 * @ Author:刘刚
 */

public class ImageEmpty {       //加载默认图片方法
    public static ImageOptions imageUtils(){
        //通过ImageOptions.Builder().set方法设置图片的属性
        ImageOptions imageOptions= new ImageOptions.Builder().setFadeIn(true)
                //.setCircular(true) //设置图片显示为圆形
                //.setSquare(true) //设置图片显示为正方形
                .setCrop(true).setSize(200,150) //设置大小
                .setUseMemCache(true).build(); //设置使用MemCache，默认true
        return imageOptions;

    }
}
