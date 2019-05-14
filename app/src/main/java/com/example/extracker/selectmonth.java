package com.example.extracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class selectmonth extends AppCompatActivity {

    EditText income;
    Spinner month,year;
    Button submit,viewed;
    SQLiteDatabase sdb;
    String exist;
    Integer imo;
    String mon[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
    Integer yr[]={2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030,2031,2032,2033,2034,2035,2036,2037,2038,2039,2040};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmonth);

        Intent m=getIntent();

        income=(EditText)findViewById(R.id.income);
        month=(Spinner) findViewById(R.id.month);
        year=(Spinner) findViewById(R.id.year);
        submit=(Button)findViewById(R.id.button);
        viewed=(Button)findViewById(R.id.viewlist);
       // viewed.setVisibility(View.INVISIBLE);

        sdb=openOrCreateDatabase("exTracker",MODE_PRIVATE,null);

        month.setPrompt("Select Month");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,mon);
        month.setAdapter(adapter);


        year.setPrompt("Select Year");
        ArrayAdapter<Integer> adapter1=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_spinner_item,yr);
        year.setAdapter(adapter1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String in=income.getText().toString();
                String mont=month.getSelectedItem().toString();
                String yer=year.getSelectedItem().toString();

                Cursor c=sdb.rawQuery("select income from Expense where month='"+mont+"' and year="+yer,null);
                if (c.getCount()!=0){
                    exist="update";

                }
                else{
                    exist="add";
                }


                if(in.length()>0 && mont.length()>0 && yer.length()>0) {
                    imo=Integer.valueOf(in);

                    Integer yre=Integer.valueOf(yer);
                   // Toast.makeText(selectmonth.this, imo+"-"+mont+"-"+yre, Toast.LENGTH_SHORT).show();
                    Intent mon = new Intent(selectmonth.this, listedit.class);
                    mon.putExtra("income", imo);
                    mon.putExtra("month", mont);
                    mon.putExtra("year", yre);
                    mon.putExtra("exist",exist);
                    startActivity(mon);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Fill Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vw=new Intent(selectmonth.this,expenselist.class);
                startActivity(vw);
            }
        });


    }
}
