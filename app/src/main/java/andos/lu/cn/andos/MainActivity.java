package andos.lu.cn.andos;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import andos.lu.cn.andos.fragments.LeakCanaryFragment;
import andos.lu.cn.andos.fragments.SimpleFresccoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getFragmentManager();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is on main thread

                OkHttpClient client = HttpFactory.get();
                Request request = new Request.Builder()
                        .url("https://g.alicdn.com/sd/data_sufei/1.4.3/aplus/index.js")
                        .build();


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.e("okhttp", request.toString(), e);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        Log.d("okhttp", response.body().string());
                    }
                });

            }
        });


        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is on main thread
                SimpleFresccoFragment simpleFresccoFragment = new SimpleFresccoFragment();
                simpleFresccoFragment.show(fragmentManager, "SimpleFresccoFragment");
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is on main thread
                LeakCanaryFragment leakFragment = new LeakCanaryFragment();
                leakFragment.show(fragmentManager, "Leak Fragment");
            }
        });

    }
}
