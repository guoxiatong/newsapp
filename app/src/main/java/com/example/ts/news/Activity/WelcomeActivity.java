package com.example.ts.news.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ts.news.R;
import com.example.ts.news.Utils.ApplicationUtil;
import com.example.ts.news.Utils.SharedPreUtil;

public class WelcomeActivity extends AppCompatActivity {
    //private boolean flag = false;
    private Button btn_jump;

    //Handler传递是否登入的信息，确保进入的时候是对应的界面
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                //判断用户是否登录
                boolean userIsLogin = (boolean) SharedPreUtil.getParam(WelcomeActivity.this,
                        SharedPreUtil.IS_LOGIN, false);
                if (userIsLogin) {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, LoginOrRegisterActivity.class);
                    startActivity(intent);
                }

                finish();
            } else if (msg.what == 0) {
                thread.interrupt();
            }

        }

    };

    //开始界面点击自动跳过，不点击则三秒后
    final Message message = new Message();
    final Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                message.what = 1;
                handler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //setAnimation();

        thread.start();

        btn_jump = (Button) findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                flag = true;
                message.what = 0;
                handler.sendMessage(message);

                //判断用户是否登录
                boolean userIsLogin = (boolean) SharedPreUtil.getParam(WelcomeActivity.this,
                        SharedPreUtil.IS_LOGIN, false);
                if (userIsLogin) {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, LoginOrRegisterActivity.class);
                    startActivity(intent);
                }

                finish();


            }
        });

        ApplicationUtil.getInstance().addActivity(this);
    }
}
