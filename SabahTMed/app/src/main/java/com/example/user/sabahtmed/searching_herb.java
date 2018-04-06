package com.example.user.sabahtmed;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by User on 13/3/2017.
 */

public class searching_herb extends AppCompatActivity {


    String a,passDiseaseName;

    private DatabaseHelper dbHelper;
    ArrayList<String> arraySpinner= new ArrayList<String>();
    ArrayList<String> arraySpinner1= new ArrayList<String>();

    String HerbListSpinner,DiseaseListSpinner;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {
        }

        try {
            Cursor cursor = dbHelper.QueryData("select herb_name from herbs group by herb_name");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        HerbListSpinner =cursor.getString(0);
                        arraySpinner.add(HerbListSpinner);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException ioe) {
        }

        // show the queired data
        Spinner herb=(Spinner) findViewById(R.id.spinnerHerb);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arraySpinner);
        herb.setAdapter(adapter);


        //spinner for disease
       final  Spinner disease = (Spinner) findViewById(R.id.spinnerDisease);
       final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner1);


        herb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                a= parent.getItemAtPosition(position).toString();

                //clear spinner after click
                arraySpinner1.clear();
                //Display ArrayList for Disease
                try {
                    Cursor cursor = dbHelper.QueryData("select herb_disease from herbs where herb_name='" + a + "'");
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            do {

                                DiseaseListSpinner = cursor.getString(0);
                                arraySpinner1.add(DiseaseListSpinner);
                            } while (cursor.moveToNext());
                        }
                    }
                } catch (SQLException ioe) {
                }
                //set value adapter
                disease.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



            disease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    passDiseaseName= parent.getItemAtPosition(position).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



        //Butoon implementation
        Button btn = (Button)findViewById(R.id.button_cari);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentDisHerb = new Intent(searching_herb.this, disease_herb_information.class);
                intentDisHerb.putExtra("HerbName", a);
                intentDisHerb.putExtra("DiseaseName", passDiseaseName);
                startActivity(intentDisHerb);

            }
        });


        }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}


