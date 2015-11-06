package mddemo.library.com.activityanimation_master.demo4;

import android.graphics.Bitmap;

import mddemo.library.com.activityanimation_master.demo2.ImageCache;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日12:51:56
 * Description: 将缓存结合起来实现多级缓存
 */

public class DoubleCache {
    mddemo.library.com.activityanimation_master.demo2.ImageCache  imageCache=new ImageCache();
    DiskCache diskCache=new DiskCache();

    //先从内存获取图片再从sd卡获取
    public Bitmap get(String url){
        Bitmap bitmap=imageCache.get(url);
        if (bitmap==null){
            bitmap=diskCache.get(url);
        }
        return bitmap;
    }

    //将图片保存在内存和sd卡里面
    public void put(String url,Bitmap bitmap){
        imageCache.put(url,bitmap);
        diskCache.put(url,bitmap);
    }
}
