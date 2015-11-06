package mddemo.library.com.activityanimation_master.demo4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日11:11:00
 * Description: 于是我就这么直接加上了一个硬盘的缓存
 */

public class DiskCache {
    static  String cacheDir="这里填写你想缓存的地址";
    //从缓存里面获取图片
    public Bitmap get(String url){
        // TODO: 2015/11/6 注意在硬盘缓存的实践中最好是存url的md5不要直接存url 内存也是
        return BitmapFactory.decodeFile(cacheDir+url);
    }
    //将图片保存在硬盘里面
    public void put(String url,Bitmap bitmap){
        FileOutputStream fileOutputStream=null;
        try{
            fileOutputStream=new FileOutputStream(cacheDir+url);
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
