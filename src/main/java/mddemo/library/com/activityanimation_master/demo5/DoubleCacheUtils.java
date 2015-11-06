package mddemo.library.com.activityanimation_master.demo5;

import android.graphics.Bitmap;


import mddemo.library.com.activityanimation_master.demo4.DiskCache;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日12:51:56
 * Description: 将缓存结合起来实现多级缓存
 */

public class DoubleCacheUtils implements ImageCache {
    MemoryCacheUtils  imageCache=new MemoryCacheUtils();
    DiskCacheUtils diskCache=new DiskCacheUtils();
    //先从内存获取图片再从sd卡获取

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap=imageCache.get(url);
        if (bitmap==null){
            bitmap=diskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        imageCache.put(url,bitmap);
        diskCache.put(url,bitmap);
    }
}
