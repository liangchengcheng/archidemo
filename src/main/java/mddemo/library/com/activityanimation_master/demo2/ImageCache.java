package mddemo.library.com.activityanimation_master.demo2;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日09:59:22
 * Description: 将缓存的类给提取出来
 */

public class ImageCache {

    //创建LRU缓存
    LruCache<String, Bitmap> mImageCache;

    //创建构造函数
    public ImageCache() {
    }

    //获取手机的最大内存，
    private void initImageCache() {
        final int maxCache = (int) (Runtime.getRuntime().maxMemory() / 1024);

        final int cacheSize = maxCache / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    //设置内存
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    //从内存获取图片
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}
