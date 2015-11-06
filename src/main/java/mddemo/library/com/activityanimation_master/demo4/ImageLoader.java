package mddemo.library.com.activityanimation_master.demo4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mddemo.library.com.activityanimation_master.demo2.ImageCache;
import mddemo.library.com.activityanimation_master.demo3.DiskCache;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日09:56:38
 * Description: 多级缓存的实现
 */

// TODO: 2015/11/6  要是加入更多的缓存的话要不断的修改代码
public class ImageLoader {

    //创建图片的缓存
    ImageCache imageCache = new ImageCache();
    //实例化硬盘的缓存
    DiskCache diskCache=new DiskCache();
    //2级缓存
    DoubleCache doubleCache=new DoubleCache();
    //创建线程池为cpu的最大的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //展示图片
    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = doubleCache.get(url);
        //对外开放字段表示用哪个缓存
        //Bitmap imagebitmap = imageCache.get(url);
        //Bitmap diskbitmap = diskCache.get(url);
        //Bitmap doublebitmap = doubleCache.get(url);
        
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

    //是否开启缓存
    public void userDiskCache(boolean useDiskCache){

    }
}
