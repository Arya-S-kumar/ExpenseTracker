package com.example.extracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;


public class gridlist extends AppCompatActivity {

    CardView card1,card2,card3,card4,card5,card6,card7,card8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent gmon=getIntent();
       /* gmon.getIntExtra("income",0);
        gmon.putExtra("month",'i');
        gmon.putExtra("year",'0');*/

       card1=(CardView)findViewById(R.id.card1);
       card2=(CardView)findViewById(R.id.card2);
       card3=(CardView)findViewById(R.id.card3);
       card4=(CardView)findViewById(R.id.card4);
       card5=(CardView)findViewById(R.id.card5);
       card6=(CardView)findViewById(R.id.card6);
       card7=(CardView)findViewById(R.id.card7);
       card8=(CardView)findViewById(R.id.card8);

      /*  card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(gridlist.this, "Card1 selected", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}


