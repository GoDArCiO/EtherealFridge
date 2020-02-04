package com.example.EtherealFridge;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;


public class MenuActivity extends AppCompatActivity {

    private Button ButtonP, ButtonE, ButtonS, ButtonF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        ButtonP = (Button) findViewById(R.id.buttonP);
        ButtonE = (Button) findViewById(R.id.buttonE);
        ButtonS = (Button) findViewById(R.id.buttonS);

        ButtonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        ButtonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, EatActivity.class);
                startActivity(intent);
            }
        });

        ButtonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, StatActivity.class);
                startActivity(intent);
            }
        });

    }

}
