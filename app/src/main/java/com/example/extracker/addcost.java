package com.example.extracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addcost extends AppCompatActivity {
    TextView t1,bt;
    Integer income,year,bal=0,exp=0,totalb,getin;
    String month,exist;
    SQLiteDatabase db1;
    final Context c=this;
    Button bv;
    ListView ls;
    StringBuffer bf=new StringBuffer();
    String itemlist[] = {"Grocery", "ElectricBill", "WaterBill", "Food", "Education", "Milk", "Newspaper", "Cable", "Recharge", "Shopping"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcost);

        t1 = (TextView) findViewById(R.id.t1);
        bt = (TextView) findViewById(R.id.bt);
        bv=(Button)findViewById(R.id.view);
        ls=(ListView)findViewById(R.id.lst);
        ls.setVisibility(View.INVISIBLE);

        Intent gt=getIntent();

        income=gt.getIntExtra("lincome",0);
        month=gt.getStringExtra("lmonth");
        year=gt.getIntExtra("lyear",0);
        exist=gt.getStringExtra("p");

        Toast.makeText(this, income+"-"+month+"-"+year, Toast.LENGTH_SHORT).show();
        db1=openOrCreateDatabase("exTracker",MODE_PRIVATE,null);
        bal=0;

        Cursor c=db1.rawQuery("SELECT income FROM Expense WHERE month='"+month+"' AND year="+year, null);
        if (c.getCount()!=0){
            while(c.moveToNext()){
                income=c.getInt(0);
            }
        }
        Cursor up=db1.rawQuery("SELECT Balance FROM Expense WHERE month='"+month+"' AND year="+year, null);

        for (int i = 0; i < CustomAdapter.modelArrayList.size(); i++){
            if(CustomAdapter.modelArrayList.get(i).getSelected()) {
              //  t1.setText( CustomAdapter.modelArrayList.get(i).getAnimal()+" "+CustomAdapter.modelArrayList.get(i).getEditTextValue());
                String item=CustomAdapter.modelArrayList.get(i).getAnimal();
                String coost=CustomAdapter.modelArrayList.get(i).getEditTextValue();
                Integer cost=Integer.valueOf(coost);

                 if(c.getCount()!=0){
                     db1.execSQL("update Expense set "+item+"="+cost+" where month='"+month+"' and year="+year+";");
                     exp=exp+cost;

                 }
                 if (exist=="update"){
                     while (up.moveToNext()){
                          totalb=up.getInt(0);
                     }
                     bal=income-totalb;
                 }
                 else{
                     bal=income-exp;
                 }

            }
          /*  else{
               // db1.execSQL("INSERT INTO Expense("+CustomAdapter.modelArrayList.get(i).getAnimal()+") values(0);");

            }*/
        }
        if (c.getCount()!=0){
            db1.execSQL("update Expense set Balance="+bal+" where month='"+month+"' and year="+year+";");
            bt.setText(bt.getText()+"\n"+bal);
            Cursor sor=db1.rawQuery("select * from Expense WHERE month='"+month+"' AND year="+year,null);
            bf.delete(0,bf.length());
            if (sor.getCount()!=0){
                while(sor.moveToNext()){
                    bf.append(sor.getString(0)+" ");
                    bf.append(sor.getString(1)+" ");
                    bf.append(sor.getString(2)+" ");
                    bf.append(sor.getString(3)+" ");
                    bf.append(sor.getString(4)+"\n");
                    bf.append(sor.getString(5)+" ");
                    bf.append(sor.getString(6)+" ");
                    bf.append(sor.getString(7)+" ");
                    bf.append(sor.getString(8)+"\n");
                }
                Toast.makeText(this, bf.toString(), Toast.LENGTH_SHORT).show();
            }

        }

        bv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(addcost.this,search.class);
                i.putExtra("lmonth",month);
                i.putExtra("lyear",year);
                startActivity(i);
              /* ls.setVisibility(View.VISIBLE);
                Cursor tms=db1.rawQuery("select * from Expense where month='"+month+"' and year="+year,null);
                String a[]=new String[10];
                if (tms.getCount()!=0){
                   // int i=0;
                    tms.moveToNext();
                     //a[i]=c.getString(0)+ " " +c.getString(1);
                       a[0]=itemlist[0]+" "+String.valueOf(tms.getInt(3));
                       a[1]=itemlist[1]+" "+String.valueOf(tms.getInt(4));
                       a[2]=itemlist[2]+" "+String.valueOf(tms.getInt(5));
                       a[3]=itemlist[3]+" "+String.valueOf(tms.getInt(6));
                       a[4]=itemlist[4]+" "+String.valueOf(tms.getInt(7));
                       a[5]=itemlist[5]+" "+String.valueOf(tms.getInt(8));
                       a[6]=itemlist[6]+" "+String.valueOf(tms.getInt(9));
                      a[7]=itemlist[7]+" "+String.valueOf(tms.getInt(10));
                    a[8]=itemlist[8]+" "+String.valueOf(tms.getInt(11));
                    a[9]=itemlist[9]+" "+String.valueOf(tms.getInt(12));






                    //i++;



                    ArrayAdapter ar=new ArrayAdapter(addcost.this,android.R.layout.simple_list_item_1,a);
                    ls.setAdapter(ar);
                }
                */

            }
        });


       /* Bundle card=getIntent().getExtras();;
        ArrayList<String> selarray=(ArrayList<String>) card.getStringArrayList("selarray");
        Log.i("List", "Passed Array List :: " + selarray);
         int size=selarray.size();*/


        /*bt=(Button)findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
                View mView = layoutInflaterAndroid.inflate(R.layout.ip_input, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                alertDialogBuilderUserInput.setView(mView);

                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderUserInput.setCancelable(false).setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ToDo get user input here
                        Integer inp=Integer.valueOf( userInputDialogEditText.getText().toString());
                        Toast.makeText(addcost.this, inp, Toast.LENGTH_SHORT).show();
                    }
                })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });*/

        db1.close();



    }
}
