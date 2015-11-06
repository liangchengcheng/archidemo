package mddemo.library.com.activityanimation_master;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import mddemo.library.com.activityanimation_master.demo5.DiskCacheUtils;
import mddemo.library.com.activityanimation_master.demo5.DoubleCacheUtils;
import mddemo.library.com.activityanimation_master.demo5.ImageLoader;
import mddemo.library.com.activityanimation_master.demo5.MemoryCacheUtils;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月6日14:36:56
 * Description: 有错误bug请邮件我谢谢
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final ImageView iv_head = (ImageView) findViewById(R.id.iv_head);
        final ImageLoader imageLoader = new ImageLoader();
        DoubleCacheUtils doubleCacheUtils = new DoubleCacheUtils();
        imageLoader.setImageCache(new MemoryCacheUtils());
        imageLoader.setImageCache(new DiskCacheUtils());
        imageLoader.setImageCache(doubleCacheUtils);
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageLoader.displayImage("http://www.17yxb.cn/zb_users/upload/2015/6/2015062453163341.png", iv_head);
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
