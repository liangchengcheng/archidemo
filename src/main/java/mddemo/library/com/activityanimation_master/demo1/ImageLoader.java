package mddemo.library.com.activityanimation_master.demo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日09:30:14
 * Description: 最原始的一个工具类去加载网络上的图片
 */
// TODO: 2015/11/6 demo1展示了一个图片加载器的最简单的示例代码 ：改进——>demo2
public class ImageLoader {

    //创建图片缓存
    LruCache<String, Bitmap> mImageCache;
    //创建一个线程池为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //创建构造函数
    public ImageLoader() {
        initImageCache();
    }

    private void initImageCache() {
        //计算可以用的最大的内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //将内存的四分之一可以用的内存作为缓存区
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    //展示图片
    public void displayImage(final String url, final ImageView imageView) {
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                //下载图片
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url, bitmap);
            }
        });
    }

    //去网上下载一个图片
    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
