package com.example.EtherealFridge;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class BonusActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
private Button calories,protein,fat;
    private TextView top1,top2,top3,top4,top5,top1r,top2r,top3r,top4r,top5r;//r-ratio
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int wybor=1;
        setContentView(R.layout.bonus_layout);
        top1 = (TextView) findViewById(R.id.textView21);
        top2 = (TextView) findViewById(R.id.textView22);
        top3 = (TextView) findViewById(R.id.textView23);
        top4 = (TextView) findViewById(R.id.textView24);
        top5 = (TextView) findViewById(R.id.textView25);
        top1r = (TextView) findViewById(R.id.textView27);
        top2r = (TextView) findViewById(R.id.textView28);
        top3r = (TextView) findViewById(R.id.textView29);
        top4r = (TextView) findViewById(R.id.textView30);
        top5r = (TextView) findViewById(R.id.textView31);
        calories = (Button) findViewById(R.id.calories);
        protein = (Button) findViewById(R.id.button2);
        fat = (Button) findViewById(R.id.button3);
        mDatabaseHelper = new DatabaseHelper(this);
        Cursor dat3a4 = mDatabaseHelper.getHistory();
        int n=0;
        //smart way to get no of items in database
        while(dat3a4.moveToNext()){
            dat3a4.getInt(0);
            n++;
        }
        dat3a4.close();
        //debug mess
        //toastMessage("We have "+n+" items!");

        FoodObject[] food=new FoodObject[n];

        dat3a4 = mDatabaseHelper.getHistory();

        int i=0;
        //set data names,macros,price -unsorted array of objects
        while(dat3a4.moveToNext()){
            food[i]=new FoodObject();
            food[i].setName(dat3a4.getString(1));
            food[i].setPrice(dat3a4.getInt(2));
            food[i].setCal(dat3a4.getInt(3));
            food[i].setPro(dat3a4.getInt(4));
            food[i].setFat(dat3a4.getInt(5));
            i++;
        }

        final Map<String, Double> calRatio = new HashMap<String, Double>();
        final Map<String, Double> proRatio = new HashMap<String, Double>();
        final Map<String, Double> fatRatio = new HashMap<String, Double>();

        for(i=0;i<n;i++) {
            calRatio.put(food[i].getName(), (double) food[i].getCal() / food[i].getPrice()*100);
            proRatio.put(food[i].getName(), (double) food[i].getPro() / food[i].getPrice()*100);
            fatRatio.put(food[i].getName(), (double) food[i].getFat() / food[i].getPrice()*100);
        }

        //nie wiem jak to zrobic lepiej???
        calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int i = 0;
                Map<String, Double> treeMap = sortByValue(calRatio);
                    for (double key : treeMap.values()) {
                        if (i == 0) {
                            top1r.setText("" + key);
                        }
                        if (i == 1) {
                            top2r.setText("" + key);
                        }
                        if (i == 2) {
                            top3r.setText("" + key);
                        }
                        if (i == 3) {
                            top4r.setText("" + key);
                        }
                        if (i == 4) {
                            top5r.setText("" + key);
                        }
                        i++;
                    }
                    i = 0;
                    for (String key : treeMap.keySet()) {
                        if (i == 0) {
                            top1.setText("" + key);
                        }
                        if (i == 1) {
                            top2.setText("" + key);
                        }
                        if (i == 2) {
                            top3.setText("" + key);
                        }
                        if (i == 3) {
                            top4.setText("" + key);
                        }
                        if (i == 4) {
                            top5.setText("" + key);
                        }
                        i++;
                    }
                }


        });
        protein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Double> treeMap = sortByValue(proRatio);
                   int i = 0;
                    for (double key : treeMap.values()) {
                        if (i == 0) {
                            top1r.setText("" + key);
                        }
                        if (i == 1) {
                            top2r.setText("" + key);
                        }
                        if (i == 2) {
                            top3r.setText("" + key);
                        }
                        if (i == 3) {
                            top4r.setText("" + key);
                        }
                        if (i == 4) {
                            top5r.setText("" + key);
                        }
                        i++;
                    }
                    i = 0;
                    for (String key : treeMap.keySet()) {
                        if (i == 0) {
                            top1.setText("" + key);
                        }
                        if (i == 1) {
                            top2.setText("" + key);
                        }
                        if (i == 2) {
                            top3.setText("" + key);
                        }
                        if (i == 3) {
                            top4.setText("" + key);
                        }
                        if (i == 4) {
                            top5.setText("" + key);
                        }
                        i++;
                    }
                }


        });
        fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Double> treeMap = sortByValue(fatRatio);
                int i = 0;
                for (double key : treeMap.values()) {
                    if (i == 0) {
                        top1r.setText("" + key);
                    }
                    if (i == 1) {
                        top2r.setText("" + key);
                    }
                    if (i == 2) {
                        top3r.setText("" + key);
                    }
                    if (i == 3) {
                        top4r.setText("" + key);
                    }
                    if (i == 4) {
                        top5r.setText("" + key);
                    }
                    i++;
                }
                i = 0;
                for (String key : treeMap.keySet()) {
                    if (i == 0) {
                        top1.setText("" + key);
                    }
                    if (i == 1) {
                        top2.setText("" + key);
                    }
                    if (i == 2) {
                        top3.setText("" + key);
                    }
                    if (i == 3) {
                        top4.setText("" + key);
                    }
                    if (i == 4) {
                        top5.setText("" + key);
                    }
                    i++;
                }

            }
        });
        dat3a4.close();

    }

    private static Map<String, Double> sortByValue(Map<String, Double> unsortMap) {

        // mapa -> lista map
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        // sortuj
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());//desc or asc order
            }
        });

        // odkladamy
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
