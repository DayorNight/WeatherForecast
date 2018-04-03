package com.lwb.lajitianqi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwb.lajitianqi.Utils.FramentManages;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMenu();
    }

    private void initMenu() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        ImageView img_settings = (ImageView) findViewById(R.id.img_settings);
        tv_title.setOnClickListener(this);
        img_settings.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()){
            case R.id.tv_title:
                bundle.putString(Constant.Bundle_key,getString(R.string.run_city));
                startActivity(PublicFragmentActivity.createIntent(this, FramentManages.Run_City, bundle));
                break;
            case R.id.img_settings:
                bundle.putString(Constant.Bundle_key,getString(R.string.settings));
                startActivity(PublicFragmentActivity.createIntent(this, FramentManages.Settings, bundle));
                break;
        }
    }
}
