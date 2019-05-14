package com.example.extracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class expenselist extends AppCompatActivity {
    Button bt;
    ListView lv;
    Spinner sp1,sp2;
    SQLiteDatabase db;
    String mon[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
    Integer yr[]={2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030,2031,2032,2033,2034,2035,2036,2037,2038,2039,2040};
    String itemlist[] = {"Grocery", "ElectricBill", "WaterBill", "Food", "Education", "Milk", "Newspaper", "Cable", "Recharge", "Shopping"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenselist);
        bt=(Button)findViewById(R.id.show);
        lv=(ListView)findViewById(R.id.showlist);
        sp1=(Spinner)findViewById(R.id.smonth);
        sp2=(Spinner)findViewById(R.id.syear);

        lv.setVisibility(View.INVISIBLE);

        db=openOrCreateDatabase("exTracker",MODE_PRIVATE,null);

        sp1.setPrompt("Select Month");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,mon);
        sp1.setAdapter(adapter);


        sp2.setPrompt("Select Month");
        ArrayAdapter<Integer> adapter1=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_spinner_item,yr);
        sp2.setAdapter(adapter1);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setVisibility(View.VISIBLE);
                String mont=sp1.getSelectedItem().toString();
                String yer=sp2.getSelectedItem().toString();

                Cursor c=db.rawQuery("select * from Expense where month='"+mont+"' and year="+yer,null);
                String a[]=new String[10];


                if (c.getCount()!=0){
                    while (c.moveToNext()){
                        a[0]=itemlist[0]+"       "+String.valueOf(c.getInt(4));
                        a[1]=itemlist[1]+"  "+String.valueOf(c.getInt(5));
                        a[2]=itemlist[2]+"      "+String.valueOf(c.getInt(6));
                        a[3]=itemlist[3]+"           "+String.valueOf(c.getInt(7));
                        a[4]=itemlist[4]+"    "+String.valueOf(c.getInt(8));
                        a[5]=itemlist[5]+"              "+String.valueOf(c.getInt(9));
                        a[6]=itemlist[6]+"   "+String.valueOf(c.getInt(10));
                        a[7]=itemlist[7]+"            "+String.valueOf(c.getInt(11));
                        a[8]=itemlist[8]+"       "+String.valueOf(c.getInt(12));
                        a[9]=itemlist[9]+"       "+String.valueOf(c.getInt(13));
                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(expenselist.this,android.R.layout.simple_list_item_1,a);
                    lv.setAdapter(ar);

                }
                else{
                    Toast.makeText(expenselist.this, "No Expenses added in this time", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
