package com.example.luke.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class firstpage extends AppCompatActivity {

    Button button1,button2,button3,button4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);

        button1 = (Button) findViewById(R.id.player1);
        button2 = (Button) findViewById(R.id.player2);
        button3 = (Button) findViewById(R.id.how);
        button4 = (Button) findViewById(R.id.about);
        button1.setOnClickListener(calcResult);
        button2.setOnClickListener(calcResult);
        button3.setOnClickListener(calcResult);
        button4.setOnClickListener(calcResult);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(firstpage.this)
                    .setTitle("退出遊戲")
                    .setMessage("確定要退出嗎?")
                    .setIcon(R.drawable.ic_launcher)
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                        }
                    }).show();
        }
        return true;
    }

    private View.OnClickListener calcResult = new View.OnClickListener() {

        public void onClick(View v) {
            if (v.getId() == R.id.player1)
            {
                Intent intent = new Intent();
                intent.setClass(firstpage.this, MainActivity.class);
                startActivity(intent);
            }
            else if(v.getId() == R.id.player2)
            {
                Intent intent = new Intent();
                intent.setClass(firstpage.this, player.class);
                startActivity(intent);
            }
            else if(v.getId()==R.id.how)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(firstpage.this);
                ad.setTitle("說明");
                ad.setMessage("遊戲方式 : \n\n" +
                "1. 單人挑戰\n" + "翻牌規則為，翻出兩張相同圖案的牌，不同時牌會蓋回，每翻開兩張算一次，最後會計算出所花費的次數。\n\n" +
                "2. 雙人P.K\n" + "翻牌規則同上，兩人輪流翻牌，其中獲得回合的人，能選擇是否對兩張牌作換牌，最後結束時，翻出最多牌的人獲勝。");
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface di, int i) {
                    }
                };
                    ad.setPositiveButton("離開",listener);
                    ad.show();
            }
            else if(v.getId()==R.id.about)
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(firstpage.this);
                ad.setTitle("關於");
                ad.setMessage("版本 : 0.4\n\n" + "製作人員 :\n" + "陳彥廷\n" + "何寬昱\n" + "詹杰燊\n" + "劉威宏");
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface di, int i) {
                    }
                };
                ad.setPositiveButton("離開",listener);
                ad.show();
            }
        }
    };
}
