package mddemo.library.com.activityanimation_master.demo5;

import android.graphics.Bitmap;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日13:02:33
 * Description:  设计一个接口
 */

public interface ImageCache {
     Bitmap get(String url);
     void put(String url, Bitmap bitmap);
}
