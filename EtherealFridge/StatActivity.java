package com.example.EtherealFridge;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;


public class StatActivity extends AppCompatActivity {

        private TextView cal,pro,fat,calp,prop,fatp,cal3,pro3,fat3,cal3p,pro3p,fat3p;
        private Button Reset,Set;
        private Integer kcal=0,bialko=0,tluszcz=0;
        private Integer Zkcal=0,Zbialko=0,Ztluszcz=0,Zkcal3=0,Zbialko3=0,Ztluszcz3=0;
        private EditText editText1,editText2,editText3;
        DatabaseHelper mDatabaseHelper;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.stat_layout);//2%i3% 1i4normalized
            cal = (TextView) findViewById(R.id.txtCalToday);
            pro = (TextView) findViewById(R.id.txtProToday);
            fat = (TextView) findViewById(R.id.txtFatToday);
            calp = (TextView) findViewById(R.id.txtCalToday2);
            prop = (TextView) findViewById(R.id.txtProToday2);
            fatp = (TextView) findViewById(R.id.txtFatToday2);
            cal3 = (TextView) findViewById(R.id.txtCalToday4);
            pro3 = (TextView) findViewById(R.id.txtProToday4);
            fat3 = (TextView) findViewById(R.id.txtFatToday4);
            cal3p = (TextView) findViewById(R.id.txtCalToday3);
            pro3p = (TextView) findViewById(R.id.txtProToday3);
            fat3p = (TextView) findViewById(R.id.txtFatToday3);
            Reset = (Button) findViewById(R.id.btnReset);
            Set = (Button) findViewById(R.id.Set);
            editText1 = (EditText) findViewById(R.id.editText6);
            editText2 = (EditText) findViewById(R.id.editText7);
            editText3 = (EditText) findViewById(R.id.editText8);


            mDatabaseHelper = new DatabaseHelper(this);

            Reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Zkcal=0;
                    Zbialko=0;
                    Ztluszcz=0;
                    cal.setText(""+Zkcal);
                    pro.setText(""+Zbialko);
                    fat.setText(""+Ztluszcz);
                    mDatabaseHelper.clearStats();
                }
            });
            Set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int kcalA,proteinA,fatA;
            String kcals = editText1.getText().toString();
            String proteins = editText2.getText().toString();
            String fats = editText3.getText().toString();
            //walidacja czy podany text jest zlozony z cyfr
                    if(kcals.matches("-?\\d+")&&proteins .matches("-?\\d+")&&fats.matches("-?\\d+")) {
                        kcalA = Integer.parseInt(kcals);
                        fatA = Integer.parseInt(fats);
                        proteinA = Integer.parseInt(proteins);
                    }else{
                        toastMessage("Your input needs to be Integer!");
                        return;
                    }
                    calp.setText(""+Zkcal*100/kcalA+"%");
                    prop.setText(""+Zbialko*100/proteinA+"%");
                    fatp.setText(""+Ztluszcz*100/fatA+"%");
                    cal3p.setText(""+Zkcal3*100/(kcalA*30)+"%");
                    pro3p.setText(""+Zbialko3*100/(proteinA*30)+"%");
                    fat3p.setText(""+Ztluszcz3*100/(fatA*30)+"%");
                }
            });

            setStats();

    }
        private void setStats() {

            Calendar calendar= Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

            int kcal = 0;
            int bialko = 0;
            int tluszcz = 0;

                Cursor data3 = mDatabaseHelper.getStatKcal(currentDate);
                Cursor data4 = mDatabaseHelper.getStatBialko(currentDate);
                Cursor data5 = mDatabaseHelper.getStatTluszcz(currentDate);

                for (data3.moveToFirst(); !data3.isAfterLast(); data3.moveToNext()) {
                    kcal = data3.getInt(0);
                    Zkcal+=kcal;
                }
                while(data4.moveToNext()){
                    bialko = data4.getInt(0);
                    Zbialko+=bialko;
                }
                while(data5.moveToNext()){
                    tluszcz = data5.getInt(0);
                    Ztluszcz+=tluszcz;
                }

                data3.close();
                data4.close();
                data5.close();
            //te byly z dzis a teraz z last30d



            int diff=0;
            while(diff>=-29){
                kcal = 0;
                bialko = 0;
                tluszcz = 0;
                calendar= Calendar.getInstance();
                calendar.add(Calendar.DATE, diff);

                currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                    Cursor dat3a3 = mDatabaseHelper.getStatKcal(currentDate);
                    Cursor dat3a4 = mDatabaseHelper.getStatBialko(currentDate);
                    Cursor dat3a5 = mDatabaseHelper.getStatTluszcz(currentDate);

                    for (dat3a3.moveToFirst(); !dat3a3.isAfterLast(); dat3a3.moveToNext()) {
                        kcal = dat3a3.getInt(0);
                        Zkcal3+=kcal;
                    }
                    while(dat3a4.moveToNext()){
                        bialko = dat3a4.getInt(0);
                        Zbialko3+=bialko;
                    }
                    while(dat3a5.moveToNext()){
                        tluszcz = dat3a5.getInt(0);
                        Ztluszcz3+=tluszcz;
                    }

                    dat3a3.close();
                    dat3a4.close();
                    dat3a5.close();

diff--;
            }

            cal.setText(""+Zkcal);
            pro.setText(""+Zbialko);
            fat.setText(""+Ztluszcz);
            cal3.setText(""+Zkcal3);
            pro3.setText(""+Zbialko3);
            fat3.setText(""+Ztluszcz3);
        }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
