package mddemo.library.com.activityanimation_master.demo5;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    内存的缓存的类
 * Description:
 */

public class MemoryCacheUtils implements ImageCache{
    //创建LRU缓存
    LruCache<String, Bitmap> mImageCache;

    //创建构造函数
    public MemoryCacheUtils() {
        initImageCache();
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

    @Override
    public Bitmap get(String url) {
        return mImageCache.get(Md5Utils.encode(url));
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(Md5Utils.encode(url),bitmap);
    }
}
