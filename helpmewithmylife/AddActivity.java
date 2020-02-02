package com.example.helpmewithmylife;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData, btnQadd;
    private EditText editText,editText2,editText3,editText4,editText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnQadd = (Button) findViewById(R.id.btnQadd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editText.getText().toString();
                String cenas = editText2.getText().toString();
                int cena = Integer.parseInt(cenas);
                String kcals = editText3.getText().toString();
                int kcal = Integer.parseInt(kcals);
                String bialkos = editText4.getText().toString();
                int bialko = Integer.parseInt(bialkos);
                String tluszczs = editText5.getText().toString();
                int tluszcz = Integer.parseInt(tluszczs);
                if (editText.length() != 0) {
                    AddData(item,cena,kcal,bialko,tluszcz);
                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText5.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

        btnQadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, Qadd.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String item, int cena, int kcal, int bialko, int tluszcz) {
        boolean insertData = mDatabaseHelper.addData(item,cena,kcal,bialko,tluszcz);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}