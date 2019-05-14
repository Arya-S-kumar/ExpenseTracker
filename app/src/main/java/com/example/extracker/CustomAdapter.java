package com.example.extracker;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter  extends BaseAdapter {

    private Context context;
    public static ArrayList<Model> modelArrayList;


    public CustomAdapter(Context context, ArrayList<Model> modelArrayList) {

        this.context = context;
        this.modelArrayList = modelArrayList;

    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.singlerow, null, true);

            holder.checkBox = (CheckBox) convertView.findViewById(R.id.ch);
            holder.tvAnimal = (TextView) convertView.findViewById(R.id.item);
            holder.image=(ImageView) convertView.findViewById(R.id.image);
            holder.cost=(EditText)convertView.findViewById(R.id.cost);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }


        //  holder.checkBox.setText("Checkbox "+position);
        holder.tvAnimal.setText(modelArrayList.get(position).getAnimal());
        holder.image.setImageResource(modelArrayList.get(position).setimage(position));

        holder.checkBox.setChecked(modelArrayList.get(position).getSelected());
        holder.image.setTag(position);

        holder.checkBox.setTag(R.integer.btnplusview, convertView);
        holder.checkBox.setTag( position);
        holder.cost.setVisibility(View.INVISIBLE);

        holder.cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                modelArrayList.get(position).setEditTextValue(holder.cost.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.checkBox.getTag(R.integer.btnplusview);
                TextView tv = (TextView) tempview.findViewById(R.id.item);
                Integer pos = (Integer)  holder.checkBox.getTag();
                Toast.makeText(context, "Checkbox "+pos+" clicked!", Toast.LENGTH_SHORT).show();

                if(modelArrayList.get(pos).getSelected()){
                    modelArrayList.get(pos).setSelected(false);
                }else {
                    modelArrayList.get(pos).setSelected(true);
                    holder.cost.setVisibility(View.VISIBLE);
                }

            }
        });

        return convertView;
    }

    private class ViewHolder {

        protected CheckBox checkBox;
        private TextView tvAnimal;
        private ImageView image;
        private EditText cost;

    }

}
