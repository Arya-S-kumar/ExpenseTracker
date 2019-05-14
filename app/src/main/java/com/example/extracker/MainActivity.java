package com.example.extracker;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        database=openOrCreateDatabase("exTracker",MODE_PRIVATE,null);
        //database.execSQL("delete from Expense;" + "");
        database.execSQL("CREATE TABLE IF NOT EXISTS Expense(id Integer PRIMARY KEY AUTOINCREMENT,income Integer,month varchar,year Integer,Grocery Integer default 0,ElectricBill Integer default 0,WaterBill Integer default 0,Food Integer default 0,Education Integer default 0,Milk Integer default 0,Newspaper Integer default 0,Cable Integer default 0,Recharge Integer default 0,Shopping Integer default 0,Balance Integer default 0);");
       // Toast.makeText(MainActivity.this, "DB created"+database, Toast.LENGTH_SHORT).show();


        progressBar=findViewById(R.id.progress);
        textView=findViewById(R.id.percentage);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
        database.close();
    }
    public  void progressAnimation(){
        ProgressBarAnimation anim=new ProgressBarAnimation(this,progressBar,textView,0f,100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }

}
