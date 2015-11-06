package mddemo.library.com.activityanimation_master.demo2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日09:56:38
 * Description: 这里是在demo1的基础上进行的一次改进（单一职责的第一次改进）
 */

// TODO: 2015/11/6 将项目进行了一次改版后更方便了维护，可以需要加入一个硬盘的缓存---->demo3
public class ImageLoader {

    //创建图片的缓存
    ImageCache imageCache = new ImageCache();
    //创建线程池为cpu的最大的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //展示图片
    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = imageCache.get(url);
        if (bitmap != null) {
            //要是在内存里面找到的话就直接返回。
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=downloadBitmap(url);
               if (bitmap==null){
                   return;
               }
                if (imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                imageCache.put(url,bitmap);
            }
        });
    }

    //在网站上下载图片
    private Bitmap downloadBitmap(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
