package andos.lu.cn.andos;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;

/**
 * Created by wuzhong on 15/12/7.
 */
public class MainApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        MainApplication.context = this;

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                ex.printStackTrace();
//                System.exit(0);
                restartApp();
            }

            public void restartApp() {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
            }
        });

        //default INIT METHOD
        //Stetho.initializeWithDefaults(this);


        //Custom dumpapp Plugins
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(new DumperPluginsProvider() {
                    @Override
                    public Iterable<DumperPlugin> get() {
                        return new Stetho.DefaultDumperPluginsBuilder(context)
                                .provide(new DumperPlugin() {

                                    @Override
                                    public String getName() {
                                        return null;
                                    }

                                    @Override
                                    public void dump(DumperContext dumpContext) throws DumpException {

                                    }
                                })
                                .finish();
                    }
                })
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                .build());


        //fresco
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(context, HttpFactory.get()).build();
        Fresco.initialize(context, config);
//        Fresco.initialize(context);

    }


}
