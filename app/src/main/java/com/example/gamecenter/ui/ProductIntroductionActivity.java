package com.example.gamecenter.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.gamecenter.R;

public class ProductIntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_introduction);
        initData();
        ImageView buttonReturn = findViewById(R.id.image_back_button_product_introduction);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        ImageView gameIcon = findViewById(R.id.imageview_game_icon);
        TextView gameName = findViewById(R.id.textview_game_name);
        TextView gameVersion = findViewById(R.id.textview_game_version);
        TextView appIntroductionDetails = findViewById(R.id.textview_app_introduction_details);
        TextView appUpdateDetails = findViewById(R.id.textview_app_update_content_details);


        gameName.setText(getIntent().getStringExtra("game_name"));
        gameVersion.setText("版本号：" + getIntent().getStringExtra("game_version_name"));
        appIntroductionDetails.setText(getIntent().getStringExtra("game_introduction"));
        appUpdateDetails.setText(getIntent().getStringExtra("game_update_content"));

        Glide.with(this)
                .load(getIntent().getStringExtra("game_icon_url"))
                .transform(new RoundedCorners(42)) // 圆角设置
                .into(gameIcon);
    }
}