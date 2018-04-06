package com.example.user.sabahtmed;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 2/3/2017.
 */

public class disease_herb_information extends AppCompatActivity{

    String tempHerbName;
    String tempDiseaseName;
    int tempMode;
    TextView NamaHerba;
    TextView NamaHerba_local;
    TextView NamaHerba_eng;
    TextView NamaHerba_sci;
    TextView NamaHerba_dis;
    TextView NamaHerba_part;
    TextView NamaHerba_ins;

    TextView NamaHerba_toolbar;

    ImageView gambar_herba;

    DatabaseHelper dbHelper;

    ArrayList<Item> arrayList= new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_disease_herb_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        Intent intent = getIntent();
        tempHerbName = intent.getExtras().getString("HerbName");
        tempDiseaseName= intent.getExtras().getString("DiseaseName");
        tempMode=intent.getExtras().getInt("Mode");

        NamaHerba_toolbar = (TextView) findViewById(R.id.NamaBesarPenyakitHerba);
        if(tempMode == 2) {

            NamaHerba_toolbar.setText( tempDiseaseName + " > " + tempHerbName);

        }else
        {
            NamaHerba_toolbar.setText(tempHerbName + " > " + tempDiseaseName);
        }

        NamaHerba = (TextView) findViewById(R.id.NamaBesar);
        NamaHerba.setText(tempHerbName);


        Item item = new Item();

        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {
        }

        //herb name Query
        try {
            Cursor cursor = dbHelper.QueryData("select * from herb_information where herb_name='" + tempHerbName + "'");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        item.setHerb_name(cursor.getString(1));
                        item.setHerb_eng(cursor.getString(2));
                        item.setHerb_pic(cursor.getString(3));
                        item.setHerb_sci(cursor.getString(4));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException ioe) {
        }


        //show herb name

        gambar_herba = (ImageView) findViewById(R.id.gambar_herb);

        int id = getResources().getIdentifier(item.getHerb_pic(),"mipmap",getPackageName());
        gambar_herba.setImageResource(id);

        NamaHerba_local = (TextView) findViewById(R.id.local_name);
        NamaHerba_local.setText(item.getHerb_name());


        NamaHerba_sci = (TextView) findViewById(R.id.sci_name);
        NamaHerba_sci.setText(item.getHerb_sci());


        NamaHerba_eng = (TextView) findViewById(R.id.eng_name);
        NamaHerba_eng.setText(item.getHerb_eng());


        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {
        }

        try {
            Cursor cursor = dbHelper.QueryData("select * from herbs where herb_name='"+tempHerbName+"' and herb_disease='"+tempDiseaseName+"'");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do{

                        item.setHerb_part(cursor.getString(2));
                        item.setHerb_name_disease(cursor.getString(3));
                        item.setHerb_instruction(cursor.getString(4));
                        arrayList.add(item);

                    }while (cursor.moveToNext());

                }
            }
        } catch (SQLException ioe) {}



        NamaHerba_dis = (TextView) findViewById(R.id.herb_disease);
        NamaHerba_dis.setText(item.getHerb_name_disease());


        NamaHerba_part = (TextView) findViewById(R.id.herb_part);
        NamaHerba_part.setText(item.getHerb_part());


        NamaHerba_ins = (TextView) findViewById(R.id.herb_instruction);
        NamaHerba_ins.setText(item.getHerb_instruction());








    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
