package com.example.extracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class search extends AppCompatActivity {
    //Spinner month,year;
    SQLiteDatabase db2;
    String month;
    Integer year;
    ListView ls;
   // Button search;
    String mon[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
    Integer yr[]={2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030,2031,2032,2033,2034,2035,2036,2037,2038,2039,2040};
    String itemlist[] = {"Grocery", "ElectricBill", "WaterBill", "Food", "Education", "Milk", "Newspaper", "Cable", "Recharge", "Shopping"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ls=(ListView)findViewById(R.id.ls);
        Button home=(Button)findViewById(R.id.home);
        home.setVisibility(View.INVISIBLE);

        Intent in=getIntent();
        month=in.getStringExtra("lmonth");
        year=in.getIntExtra("lyear",0);

       /* month=(Spinner) findViewById(R.id.spinner);
        year=(Spinner) findViewById(R.id.spinner2);
        search=(Button)findViewById(R.id.search);*/

        db2=openOrCreateDatabase("exTracker",MODE_PRIVATE,null);
        Cursor tms=db2.rawQuery("select * from Expense where month='"+month+"' and year="+year,null);
        String a[]=new String[10];

        if (tms.getCount()!=0){
            // int i=0;
            tms.moveToNext();


            a[0]=itemlist[0]+"       "+String.valueOf(tms.getInt(4));
            a[1]=itemlist[1]+"  "+String.valueOf(tms.getInt(5));
            a[2]=itemlist[2]+"      "+String.valueOf(tms.getInt(6));
            a[3]=itemlist[3]+"          "+String.valueOf(tms.getInt(7));
            a[4]=itemlist[4]+"    "+String.valueOf(tms.getInt(8));
            a[5]=itemlist[5]+"             "+String.valueOf(tms.getInt(9));
            a[6]=itemlist[6]+"   "+String.valueOf(tms.getInt(10));
            a[7]=itemlist[7]+"           "+String.valueOf(tms.getInt(11));
            a[8]=itemlist[8]+"       "+String.valueOf(tms.getInt(12));
            a[9]=itemlist[9]+"       "+String.valueOf(tms.getInt(13));


            ArrayAdapter<String> ar=new ArrayAdapter<String>(search.this,android.R.layout.simple_list_item_1,a);
            ls.setAdapter(ar);
        }
        db2.close();

       /* home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(search.this,selectmonth.class);
                startActivity(j);
            }
        });*/

       /* ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,mon);
        month.setAdapter(adapter);

        ArrayAdapter<Integer> adapter1=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_spinner_item,yr);
        year.setAdapter(adapter1);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mont=month.getSelectedItem().toString();
                String yer=year.getSelectedItem().toString();
                Integer yre=Integer.valueOf(yer);

                Cursor c=db2.rawQuery("select  Grocery,ElectricBill,WaterBill,Food,Education ,Milk,Newspaper,Cable,Recharge,Shopping from Expense where month='"+month+"' and year="+year,null);
                String str[]=new String[c.getCount()];
                if (c.getCount()!=0){
                    int i=0;
                    while(c.moveToNext()){
                        str[i]=c.getString(0)+"\n"+c.getString(1)+"\n"+c.getString(2)+"\n"+c.getString(3)+"\n"+c.getString(4)+"\n"+c.getString(5)+"\n"+c.getString(6)+"\n"+c.getString(7)+"\n"+c.getString(8);

                                i++;
                    }
                    ArrayAdapter ad=new ArrayAdapter(search.this,android.R.layout.simple_list_item_1,str);
                    ListView listView=(ListView) findViewById(R.id.list);
                    listView.setAdapter(ad);

                }

            }
        });*/
    }
}
