package com.example.EtherealFridge;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EatActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.eat_layout);
            mListView = (ListView) findViewById(R.id.listView);
            mDatabaseHelper = new DatabaseHelper(this);

            populateListView();
    }

    private void populateListView() {

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }

        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                Cursor data2 = mDatabaseHelper.getItemCena(name);
                Cursor data3 = mDatabaseHelper.getItemKcal(name);
                Cursor data4 = mDatabaseHelper.getItemBialko(name);
                Cursor data5 = mDatabaseHelper.getItemTluszcz(name);
                int itemID = -1;
                int cena = -1;
                int kcal = -1;
                int bialko = -1;
                int tluszcz = -1;

                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                while(data2.moveToNext()){
                    cena = data2.getInt(0);
                }
                while(data3.moveToNext()){
                    kcal = data3.getInt(0);
                }
                while(data4.moveToNext()){
                    bialko = data4.getInt(0);
                }
                while(data5.moveToNext()){
                    tluszcz = data5.getInt(0);
                }

                if(itemID > -1){//add to eaten table and delete from fridge table
                    Calendar calendar= Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                    boolean insertData = mDatabaseHelper.addStats(name,cena,kcal,bialko,tluszcz,currentDate);
                    boolean insertData2 = mDatabaseHelper.addHistory(name,cena,kcal,bialko,tluszcz);
                    if (insertData&&insertData2) {
                        toastMessage("Data Successfully Eaten!");
                        mDatabaseHelper.deleteName(itemID,name);
                    } else {
                        toastMessage("Something went wrong");
                    }
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
