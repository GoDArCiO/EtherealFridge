package com.example.EtherealFridge;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;


public class StatActivity extends AppCompatActivity {

        private TextView cal,pro,fat,calp,prop,fatp,cal3,pro3,fat3,cal3p,pro3p,fat3p,texv1,texv2,texv3,texv4,texv5,texv6,texv7,texv8,texv9,texv10;
        private Button Reset,Set,switchman;
        private Integer kcal=0,bialko=0,tluszcz=0;
        private Integer Zkcal=0,Zbialko=0,Ztluszcz=0,Zkcal3=0,Zbialko3=0,Ztluszcz3=0;
        private EditText editText1,editText2,editText3,weight;
        private Switch sex,body,sport;
        private CheckBox rem;
public int i=0;
    private String text,text1,text2,text3;
    private boolean switchOnOff1,switchOnOff2,switchOnOff3;
    private int iii;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String TEXTc = "textc";
    public static final String TEXTp = "textp";
    public static final String TEXTf = "textf";
    public static final String SWITCH1 = "switch1";
    public static final String SWITCH2 = "switch2";
    public static final String SWITCH3 = "switch3";
    public static final String III = "iii";

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
            texv1 = (TextView) findViewById(R.id.textView16);
            texv2 = (TextView) findViewById(R.id.textView17);
            texv3 = (TextView) findViewById(R.id.textView18);
            weight = (EditText) findViewById(R.id.editText9);
            sex = (Switch) findViewById(R.id.sex);
            body = (Switch) findViewById(R.id.body);
            sport = (Switch) findViewById(R.id.sport);
            rem = (CheckBox) findViewById(R.id.remember);
            switchman = (Button) findViewById(R.id.button4);
            texv4 = (TextView) findViewById(R.id.textView34);
            texv5 = (TextView) findViewById(R.id.textView35);
            texv6 = (TextView) findViewById(R.id.textView36);
            texv7 = (TextView) findViewById(R.id.textView37);
            texv8 = (TextView) findViewById(R.id.textView38);
            texv9 = (TextView) findViewById(R.id.textView39);
            texv10 = (TextView) findViewById(R.id.textView41);

            mDatabaseHelper = new DatabaseHelper(this);

            loadData();
            updateViews();
            String weights = weight.getText().toString();
            String kcals = editText1.getText().toString();
            String proteins = editText2.getText().toString();
            String fats = editText3.getText().toString();

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
            switchman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==0) {
                        i = 1;
                        switchman.setText("Switch to generator");
                        editText1.setVisibility(View.VISIBLE);
                        editText2.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.VISIBLE);
                        texv1.setVisibility(View.VISIBLE);
                        texv2.setVisibility(View.VISIBLE);
                        texv3.setVisibility(View.VISIBLE);
                        texv4.setVisibility(View.INVISIBLE);
                        texv5.setVisibility(View.INVISIBLE);
                        texv6.setVisibility(View.INVISIBLE);
                        texv7.setVisibility(View.INVISIBLE);
                        texv8.setVisibility(View.INVISIBLE);
                        texv9.setVisibility(View.INVISIBLE);
                        texv10.setVisibility(View.INVISIBLE);
                        sex.setVisibility(View.INVISIBLE);
                        body.setVisibility(View.INVISIBLE);
                        sport.setVisibility(View.INVISIBLE);
                        weight.setVisibility(View.INVISIBLE);
                    }else{
                        i=0;
                        switchman.setText("Switch to manual");
                        editText1.setVisibility(View.INVISIBLE);
                        editText2.setVisibility(View.INVISIBLE);
                        editText3.setVisibility(View.INVISIBLE);
                        texv1.setVisibility(View.INVISIBLE);
                        texv2.setVisibility(View.INVISIBLE);
                        texv3.setVisibility(View.INVISIBLE);
                        texv4.setVisibility(View.VISIBLE);
                        texv5.setVisibility(View.VISIBLE);
                        texv6.setVisibility(View.VISIBLE);
                        texv7.setVisibility(View.VISIBLE);
                        texv8.setVisibility(View.VISIBLE);
                        texv9.setVisibility(View.VISIBLE);
                        texv10.setVisibility(View.VISIBLE);
                        sex.setVisibility(View.VISIBLE);
                        body.setVisibility(View.VISIBLE);
                        sport.setVisibility(View.VISIBLE);
                        weight.setVisibility(View.VISIBLE);
                    }
                }
            });
            Set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int kcalA,proteinA,fatA;
                    double WeightA;
            String kcals = editText1.getText().toString();
            String proteins = editText2.getText().toString();
            String fats = editText3.getText().toString();
            String weights = weight.getText().toString();

                    if(i==0){
                            //calculate cal
                            WeightA = Double.parseDouble(weights);
                            if(sex.isChecked()){
                                WeightA*=0.9;
                            }
                            WeightA*=24;
                            if(body.isChecked()){
                                WeightA*=0.9;
                            }else{
                                WeightA*=0.95;
                            }
                            if(sport.isChecked()){
                                WeightA*=1.80;
                            }else{
                                WeightA*=1.55;
                            }
                            kcalA= (int) WeightA;

                            //calculate protein
                            WeightA = Double.parseDouble(weights);
                            if(sex.isChecked()){
                                WeightA*=0.9;
                            }else{
                                WeightA*=1.2;
                            }
                            if(sport.isChecked()){
                                WeightA*=1.05;
                            }else{
                                WeightA*=0.95;
                            }
                            proteinA= (int) WeightA;

                            //calculate fat
                            WeightA=kcalA*0.25;
                            WeightA/=9;
                            fatA= (int) WeightA;

                    }else{
                            //walidacja czy podany text jest zlozony z cyfr
                            if(kcals.matches("-?\\d+")&&proteins .matches("-?\\d+")&&fats.matches("-?\\d+")) {
                                kcalA = Integer.parseInt(kcals);
                                fatA = Integer.parseInt(fats);
                                proteinA = Integer.parseInt(proteins);
                            }else{
                                toastMessage("Your input needs to be Integer!");
                                return;
                            }

                    }

                    calp.setText(""+Zkcal*100/kcalA+"%");
                    prop.setText(""+Zbialko*100/proteinA+"%");
                    fatp.setText(""+Ztluszcz*100/fatA+"%");
                    cal3p.setText(""+Zkcal3*100/(kcalA*30)+"%");
                    pro3p.setText(""+Zbialko3*100/(proteinA*30)+"%");
                    fat3p.setText(""+Ztluszcz3*100/(fatA*30)+"%");

                    if(rem.isChecked()){
                        saveData();
                    }

                }
            });

            setStats();

            if(!weights.isEmpty()&&i==0){
                Set.performClick();
                i=1;
                switchman.performClick();
            }
            if((!kcals.isEmpty()||!proteins.isEmpty()||!fats.isEmpty())&&i==1) {
                Set.performClick();
                i=0;
                switchman.performClick();
            }

    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, weight.getText().toString());
        editor.putString(TEXTc, editText1.getText().toString());
        editor.putString(TEXTp, editText2.getText().toString());
        editor.putString(TEXTf, editText3.getText().toString());
        editor.putBoolean(SWITCH1, sex.isChecked());
        editor.putBoolean(SWITCH2, body.isChecked());
        editor.putBoolean(SWITCH3, sport.isChecked());
        editor.putInt(III, i);

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        text1 = sharedPreferences.getString(TEXTc, "");
        text2 = sharedPreferences.getString(TEXTp, "");
        text3 = sharedPreferences.getString(TEXTf, "");
        switchOnOff1 = sharedPreferences.getBoolean(SWITCH1, false);
        switchOnOff2 = sharedPreferences.getBoolean(SWITCH2, false);
        switchOnOff3 = sharedPreferences.getBoolean(SWITCH3, false);
        iii = sharedPreferences.getInt(III, 0);
    }

    public void updateViews() {
        weight.setText(text);
        editText1.setText(text1);
        editText2.setText(text2);
        editText3.setText(text3);
        sex.setChecked(switchOnOff1);
        body.setChecked(switchOnOff2);
        sport.setChecked(switchOnOff3);
        i=iii;
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
