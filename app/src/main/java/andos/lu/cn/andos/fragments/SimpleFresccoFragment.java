package andos.lu.cn.andos.fragments;

import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;

import andos.lu.cn.andos.MainApplication;
import andos.lu.cn.andos.R;

/**
 * Created by wuzhong on 15/12/7.
 */
public class SimpleFresccoFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.simple_fresco, container, false);
        getDialog().setTitle("simple_fresco");
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = (Button) view.findViewById(R.id.dismiss);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onStop();
                dismiss();
            }
        });


        Uri uri = Uri.parse("https://gd3.alicdn.com/bao/uploaded/i3/TB1B_BAKFXXXXbjXpXXXXXXXXXX_!!0-item_pic.jpg?t=" + System.currentTimeMillis());
        SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("andos","on destory simple frescco fragment");
        MainApplication.refWatcher.watch(this);
    }
}
