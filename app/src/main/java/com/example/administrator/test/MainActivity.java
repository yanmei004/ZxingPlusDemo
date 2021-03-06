package com.example.administrator.test;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qrlibrary.qrcode.utils.PermissionUtils;
import com.example.qrlibrary.qrcode.utils.QRCodeUtil;


public class MainActivity extends AppCompatActivity {

    TextView test;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (TextView) findViewById(R.id.test);
        imageView = (ImageView) findViewById(R.id.image1);
        //6.0加上动态权限申请，需要在外面Acitvity做申请
        PermissionUtils.getInstance().requestPermission(this);
    }

    public void click(View view){
        switch (view.getId()){
            //生成二维码
            case R.id.score:
                imageView.setImageBitmap(QRCodeUtil.CreateTwoDCode(test.getText().toString()));
                break;
            //扫描二维码
            case R.id.scan:
                startActivity(new Intent(this,QRCodeScanAtivity.class));
                break;
            //生成二维码带图标,这和图片大小相关，默认取3/4显示
            case R.id.scorewithlogo:
                imageView.setImageBitmap(QRCodeUtil.createWithLogo(test.getText().toString(), BitmapFactory.decodeResource(getResources(), R.drawable.timg)));
                break;
            //生成带图标二维码,自设置大小  取 multipart/divisor显示
            case R.id.custom_scorewithlogo:
                imageView.setImageBitmap(QRCodeUtil.createWithLogo(test.getText().toString(), BitmapFactory.decodeResource(getResources(), R.drawable.timg),1,4));
                break;
        }
    }
}
