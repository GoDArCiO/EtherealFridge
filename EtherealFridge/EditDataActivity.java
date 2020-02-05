package com.example.EtherealFridge;

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
        editText2.setText(""+cena/100+"."+cena%100);
        editText3.setText(""+kcal);
        editText4.setText(""+bialko);
        editText5.setText(""+tluszcz);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tluszcz,bialko,kcal,cena;
                String item = editText.getText().toString();
                String cenas = editText2.getText().toString();
                String kcals = editText3.getText().toString();
                String bialkos = editText4.getText().toString();
                String tluszczs = editText5.getText().toString();
                //walidacja czy inty
                if(kcals.matches("-?\\d+")&&tluszczs.matches("-?\\d+")&&bialkos.matches("-?\\d+")) {
                    tluszcz = Integer.parseInt(tluszczs);
                    bialko = Integer.parseInt(bialkos);
                    kcal = Integer.parseInt(kcals);
                    //and now get float to integer
                    float cenaF;
                    if(cenas.matches("\\d+\\.\\d\\d")) {
                        cenaF = Float.parseFloat(cenas);
                        cenaF=cenaF*100;
                        cena = (int) cenaF;
                    } else {
                        toastMessage("Price must be a float, with 2digits after point.");
                        return;
                    }
                }else{
                    toastMessage("Calories,Proteins and Fat needs to be Integer!");
                    return;
                }
                if (editText.length() != 0) {
                    mDatabaseHelper.updateName(item,cena,kcal,bialko,tluszcz,selectedID,selectedName);
                    toastMessage("Saved Changes");
                    Intent intent = new Intent(EditDataActivity.this, AddActivity.class);
                    startActivity(intent);
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editText.setText("");
                toastMessage("Removed from database");
                Intent intent = new Intent(EditDataActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}