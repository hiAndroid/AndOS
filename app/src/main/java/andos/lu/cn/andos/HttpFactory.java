package andos.lu.cn.andos;

import android.support.annotation.Nullable;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

/**
 * Created by wuzhong on 15/12/7.
 */
public class HttpFactory {

    public static OkHttpClient get(){
        //Enable Network Inspection
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new StethoInterceptor());

        //set cache dir
        final File baseDir = MainApplication.context.getExternalCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, "HttpResponseCache");
            client.setCache(new Cache(cacheDir, 10 * 1024 * 1024));
        }

        return client;
    }

}
