package com.example.helpmewithmylife;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2/28/2017.
 */

public class EditDataActivity extends AppCompatActivity {



    private Button btnSave,btnDelete;
    private EditText editText,editText2,editText3,editText4,editText5;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int cena;
    private int kcal;
    private int bialko;
    private int tluszcz;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave = (Button) findViewById(R.id.btnSave);//save
        btnDelete = (Button) findViewById(R.id.btnDelete);//del
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value
        Bundle bundle = getIntent().getExtras();
        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        cena = bundle.getInt("cena");
        kcal = bundle.getInt("kcal");
        bialko = bundle.getInt("bialko");
        tluszcz = bundle.getInt("tluszcz");

        //set the text to show the current selected name
        editText.setText(selectedName);
        editText2.setText(""+cena);
        editText3.setText(""+kcal);
        editText4.setText(""+bialko);
        editText5.setText(""+tluszcz);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editText.getText().toString();
                String cenas = editText2.getText().toString();
                cena = Integer.parseInt(cenas);
                String kcals = editText3.getText().toString();
                kcal = Integer.parseInt(kcals);
                String bialkos = editText4.getText().toString();
                bialko = Integer.parseInt(bialkos);
                String tluszczs = editText5.getText().toString();
                tluszcz = Integer.parseInt(tluszczs);
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,cena,kcal,bialko,tluszcz,selectedID,selectedName);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editText.setText("");
                toastMessage("removed from database");
            }
        });

    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}