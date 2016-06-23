package com.example.luke.myapplication;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements Runnable{
    static final int TRUE = 1;
    static final int FALSE = 0;
    ImageButton button[] =new ImageButton[30];//12
    int ime[] = { R.drawable.card01_1, R.drawable.card02_1,R.drawable.card03_1,R.drawable.card04_1,R.drawable.card05_1,R.drawable. card06_1, R.drawable.card07_1,
            R.drawable.card08_1, R.drawable.card09_1, R.drawable.card10_1, R.drawable.card11_1, R.drawable.card12_1, R.drawable.card13_1, R.drawable.card14_1,
            R.drawable.card15_1, R.drawable.cardback_1};
    int num[] = new int[30];//12
    int e,preButton;
    int count=0;
    int nowButton;//讓Handler知到把哪個button還原
    int check_delay = FALSE;
    int btn_num;
    Thread thread;
    TextView playcount;
    int end=0;
    int testcount;

    TextView bestgrade;
    TextView textgrade;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bestgrade = (TextView)findViewById(R.id.bestgrade);
        textgrade = (TextView)findViewById(R.id.textgrade);
        SharedPreferences pref = getSharedPreferences("BEST_GRADE",MODE_PRIVATE);
        String best = pref.getString("MYBEST", "");
        bestgrade.setText(best);

        playcount=(TextView) findViewById(R.id.textcount);

        button[0]=(ImageButton) findViewById(R.id.imageButton1);
        button[1]=(ImageButton) findViewById(R.id.imageButton2);
        button[2]=(ImageButton) findViewById(R.id.imageButton3);
        button[3]=(ImageButton) findViewById(R.id.imageButton4);
        button[4]=(ImageButton) findViewById(R.id.imageButton5);
        button[5]=(ImageButton) findViewById(R.id.imageButton6);
        button[6]=(ImageButton) findViewById(R.id.imageButton7);
        button[7]=(ImageButton) findViewById(R.id.imageButton8);
        button[8]=(ImageButton) findViewById(R.id.imageButton9);
        button[9]=(ImageButton) findViewById(R.id.imageButton10);
        button[10]=(ImageButton) findViewById(R.id.imageButton11);
        button[11]=(ImageButton) findViewById(R.id.imageButton12);
        button[12]=(ImageButton) findViewById(R.id.imageButton13);
        button[13]=(ImageButton) findViewById(R.id.imageButton14);
        button[14]=(ImageButton) findViewById(R.id.imageButton15);
        button[15]=(ImageButton) findViewById(R.id.imageButton16);
        button[16]=(ImageButton) findViewById(R.id.imageButton17);
        button[17]=(ImageButton) findViewById(R.id.imageButton18);
        button[18]=(ImageButton) findViewById(R.id.imageButton19);
        button[19]=(ImageButton) findViewById(R.id.imageButton20);
        button[20]=(ImageButton) findViewById(R.id.imageButton21);
        button[21]=(ImageButton) findViewById(R.id.imageButton22);
        button[22]=(ImageButton) findViewById(R.id.imageButton23);
        button[23]=(ImageButton) findViewById(R.id.imageButton24);
        button[24]=(ImageButton) findViewById(R.id.imageButton25);
        button[25]=(ImageButton) findViewById(R.id.imageButton26);
        button[26]=(ImageButton) findViewById(R.id.imageButton27);
        button[27]=(ImageButton) findViewById(R.id.imageButton28);
        button[28]=(ImageButton) findViewById(R.id.imageButton29);
        button[29]=(ImageButton) findViewById(R.id.imageButton30);

        setnumber();

        button[0].setOnClickListener(calcResult);
        button[1].setOnClickListener(calcResult);
        button[2].setOnClickListener(calcResult);
        button[3].setOnClickListener(calcResult);
        button[4].setOnClickListener(calcResult);
        button[5].setOnClickListener(calcResult);
        button[6].setOnClickListener(calcResult);
        button[7].setOnClickListener(calcResult);
        button[8].setOnClickListener(calcResult);
        button[9].setOnClickListener(calcResult);
        button[10].setOnClickListener(calcResult);
        button[11].setOnClickListener(calcResult);
        button[12].setOnClickListener(calcResult);
        button[13].setOnClickListener(calcResult);
        button[14].setOnClickListener(calcResult);
        button[15].setOnClickListener(calcResult);
        button[16].setOnClickListener(calcResult);
        button[17].setOnClickListener(calcResult);
        button[18].setOnClickListener(calcResult);
        button[19].setOnClickListener(calcResult);
        button[20].setOnClickListener(calcResult);
        button[21].setOnClickListener(calcResult);
        button[22].setOnClickListener(calcResult);
        button[23].setOnClickListener(calcResult);
        button[24].setOnClickListener(calcResult);
        button[25].setOnClickListener(calcResult);
        button[26].setOnClickListener(calcResult);
        button[27].setOnClickListener(calcResult);
        button[28].setOnClickListener(calcResult);
        button[29].setOnClickListener(calcResult);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("遊戲暫停")
                    .setMessage("確定要結束目前遊戲嗎?")
                    .setIcon(R.drawable.ic_launcher)
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {

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

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    button[preButton].setImageResource(ime[15]);  //把圖還原//6
                    button[nowButton].setImageResource(ime[15]);//6
                    testcount++;
                    String s="現在已操作次數 : "+testcount;
                    playcount.setText(s);
                    thread.interrupt();   //把thread中止
                    thread=null;
                    break;
                default:
            }
        }};//點第二張圖的延遲用

        @Override
        public void run() {
            try {
                check_delay = TRUE;
                Thread.sleep(500);  //延遲 0.5秒
                handler.sendEmptyMessage(1); //延遲完記訊息給Handler
                check_delay = FALSE;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }       //點第二張圖的延遲用


    private ImageButton.OnClickListener calcResult = new ImageButton.OnClickListener() {

        public void onClick(View v) {
            if(check_delay == FALSE)
            {
                count=count+1;
                if(v.getId()==R.id.imageButton1)
                {
                    btn_num = 0;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton2)
                {
                    btn_num = 1;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton3)
                {
                    btn_num = 2;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton4)
                {
                    btn_num = 3;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton5)
                {
                    btn_num = 4;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton6)
                {
                    btn_num = 5;
                    determine(btn_num);
                }
                if (v.getId()==R.id.imageButton7)
                {
                    btn_num = 6;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton8)
                {
                    btn_num = 7;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton9)
                {
                    btn_num = 8;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton10)
                {
                    btn_num = 9;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton11)
                {
                    btn_num = 10;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton12)
                {
                    btn_num = 11;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton13)
                {
                    btn_num = 12;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton14)
                {
                    btn_num = 13;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton15)
                {
                    btn_num = 14;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton16)
                {
                    btn_num = 15;
                    determine(btn_num);
                }
                if (v.getId()==R.id.imageButton17)
                {
                    btn_num = 16;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton18)
                {
                    btn_num = 17;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton19)
                {
                    btn_num = 18;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton20)
                {
                    btn_num = 19;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton21)
                {
                    btn_num = 20;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton22)
                {
                    btn_num = 21;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton23)
                {
                    btn_num = 22;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton24)
                {
                    btn_num = 23;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton25)
                {
                    btn_num = 24;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton26)
                {
                    btn_num = 25;
                    determine(btn_num);
                }
                if (v.getId()==R.id.imageButton27)
                {
                    btn_num = 26;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton28)
                {
                    btn_num = 27;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton29)
                {
                    btn_num = 28;
                    determine(btn_num);
                }
                if(v.getId()==R.id.imageButton30)
                {
                    btn_num = 29;
                    determine(btn_num);
                }
            }
        }
    };

    private void setnumber() {
        int math, i = 0;
        int same[] = new int[15];//6
        int checkNum[] = new int[30];//12
        /*while(i!=7) {
            i=i+1;
            while(same[math]<=2) {
                same[math] = same[math] + 1;
                num[i]=math;
            }
        }*/
        for (i = 0; i < 30; i++) {//12
            while(checkNum[i] == 0)
            {
                math = (int) (Math.random() * 15);//6
                if (same[math] < 2)
                {
                    same[math] = same[math] + 1;
                    num[i] = math;
                    checkNum[i] = 1;
                }
            }
        }
    }
    private void determine(int btn_num)
    {
        button[btn_num].setImageResource(ime[num[btn_num]]);
        if(count%2!=0)
        {
            e=num[btn_num];
            preButton = btn_num;
        }
        else if(preButton == btn_num)
        {
            count = count - 1;
        }
        else if( e!=num[btn_num])
        {
            nowButton=btn_num;
            thread = new Thread(MainActivity.this);
            thread.start();  //跑完延遲後還原
        }
        else if(e == num[btn_num])
        {
            button[btn_num].setEnabled(false);
            button[preButton].setEnabled(false);
            testcount++;
            end++;
            String s="總共操作次數 : "+testcount;
            playcount.setText(s);
            button[btn_num].setEnabled(false);
            button[preButton].setEnabled(false);

            if(end == 15)//6
            {
                AlertDialog.Builder ad = new AlertDialog.Builder(this);
                ad.setTitle("GAME OVER");

                ad.setMessage(s);

                DialogInterface.OnClickListener back = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface di,int i) {
                        Intent intent = new Intent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setClass(MainActivity.this, firstpage.class);
                        startActivity(intent);
                        finish();
                    }

                };
                DialogInterface.OnClickListener again = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface di,int i) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                };
                ad.setNegativeButton("回到主畫面", back);
                ad.setPositiveButton("重新開始一局",again);
                ad.show();

                SharedPreferences pref = getSharedPreferences("BEST_GRADE", MODE_PRIVATE);
                String best = pref.getString("MYBEST", "0");
                int bestgrade=Integer.parseInt(best);
                if (testcount<bestgrade||bestgrade==0)
                {
                    SharedPreferences.Editor preEdt = pref.edit();
                    preEdt.putString("MYBEST",""+testcount);
                    preEdt.commit();
                }
            }
        }
    }
}


