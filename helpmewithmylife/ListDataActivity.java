package com.example.helpmewithmylife;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

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
                Log.d(TAG, "onItemClick: You Clicked on " + name);

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

                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);

                    editScreenIntent.putExtra("cena",cena);

                    editScreenIntent.putExtra("kcal",kcal);

                    editScreenIntent.putExtra("bialko",bialko);

                    editScreenIntent.putExtra("tluszcz",tluszcz);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}