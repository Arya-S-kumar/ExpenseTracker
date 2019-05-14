package com.example.extracker;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button month;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        month=(Button)findViewById(R.id.newmon);

        ViewPager viewPager=findViewById(R.id.viewPager);
        ImageAdapter adapter=new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t=new Intent(Main2Activity.this,selectmonth.class);
                startActivity(t);

            }
        });

    }
}
