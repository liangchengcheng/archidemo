package mddemo.library.com.activityanimation_master.demo5;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.FileOutputStream;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日13:09:05
 * Description: 硬盘缓存的实现
 */
public class DiskCacheUtils implements  ImageCache{
    static  String cacheDir=  Environment.getExternalStorageDirectory().getPath()+"/";
    //从缓存里面获取图片
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir +Md5Utils.encode(url)+".png");
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream=null;
        try{
            fileOutputStream=new FileOutputStream(cacheDir+Md5Utils.encode(url));
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (fileOutputStream!=null){
                try{
                    fileOutputStream.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
