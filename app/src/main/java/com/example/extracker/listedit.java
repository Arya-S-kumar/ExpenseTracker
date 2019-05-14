package com.example.extracker;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.content.Intent;
        import android.widget.Toast;

        import java.util.ArrayList;

public class listedit extends AppCompatActivity {

    private ListView lv;
    private ArrayList<Model> modelArrayList;
    private CustomAdapter customAdapter;
    private Button btnselect, btndeselect, btnnext;
    SQLiteDatabase db;
    StringBuffer bfr=new StringBuffer();


    String animallist[] = {"Grocery", "ElectricBill", "WaterBill", "Food", "Education", "Milk", "Newspaper", "Cable", "Recharge", "Shopping"};
    int imgs[] = {R.drawable.grocery, R.drawable.elect, R.drawable.water, R.drawable.food, R.drawable.edu, R.drawable.milk1, R.drawable.news, R.drawable.cable, R.drawable.recharg, R.drawable.shoping};

    Integer income,year;
    String month,exist;
    String u="update",a="add",p;
    /*String selected[]=new String[10];
    Button add;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listedit);


        lv = (ListView) findViewById(R.id.list);
      /*  btnselect = (Button) findViewById(R.id.select);
        btndeselect = (Button) findViewById(R.id.deselect);*/
        btnnext = (Button) findViewById(R.id.add);

        db=openOrCreateDatabase("exTracker",MODE_PRIVATE,null);

        Intent gr=getIntent();
        income=gr.getIntExtra("income",0);
        month=gr.getStringExtra("month");
        year=gr.getIntExtra("year",0);
        exist=gr.getStringExtra("exist");

        if (exist.equals(u)){
            Toast.makeText(this, "Table already exist", Toast.LENGTH_SHORT).show();
            btnnext.setText("update");
            p="update";
        }

        else{
            db.execSQL("INSERT INTO Expense(income,month,year) values("+income+",'"+month+"',"+year+");");
            Toast.makeText(this, "date inserted", Toast.LENGTH_SHORT).show();
            p="add";
        }



        db.close();
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listedit.this,addcost.class);
                intent.putExtra("lincome",income);
                intent.putExtra("lmonth",month);
                intent.putExtra("lyear",year);
                intent.putExtra("p",p);
                startActivity(intent);
            }

        });

        modelArrayList = getModel(false);
       customAdapter = new CustomAdapter(this,modelArrayList);
        lv.setAdapter(customAdapter);

       /* btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelArrayList = getModel(true);
                customAdapter = new CustomAdapter(MainActivity.this,modelArrayList);
                lv.setAdapter(customAdapter);
            }
        });
        btndeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelArrayList = getModel(false);
                customAdapter = new CustomAdapter(MainActivity.this,modelArrayList);
                lv.setAdapter(customAdapter);
            }
        });*/


    }

    private ArrayList<Model> getModel(boolean isSelect){
        ArrayList<Model> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Model model = new Model();
            model.setSelected(isSelect);
            model.setAnimal(animallist[i]);
            model.setEditTextValue(String.valueOf(i));

            list.add(model);
        }
        return list;
    }

}
