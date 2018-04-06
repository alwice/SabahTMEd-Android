package com.example.user.sabahtmed;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 9/3/2017.
 */

public class herbs_disease_list extends AppCompatActivity {


    ArrayList<Item> arrayListDiseaseHerb = new ArrayList<Item>();
    Adapter adapter;
    ListView listDiseaseHerb;
    private DatabaseHelper dbHelper;


    TextView NamaPenyakit;
    String temp;

    String passHerbName;
    Item diseaseListHerb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_herb);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //for toolbar back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        final Intent intent = getIntent();
        temp = intent.getExtras().getString("HerbName");

        NamaPenyakit = (TextView) findViewById(R.id.NamaBesarPenyakit);
        NamaPenyakit.setText(temp);


        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {
        }

        try {
            Cursor cursor = dbHelper.QueryData("select herb_disease from herbs where herb_name='" + temp + "'");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Item item = new Item();
                        item.setHerbDiseaseList(cursor.getString(0));
                        arrayListDiseaseHerb.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException ioe) {
        }

        adapter = new Adapter(this, R.layout.disease_list, arrayListDiseaseHerb);
        listDiseaseHerb= (ListView) findViewById(R.id.listDisHerb);
        listDiseaseHerb.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listDiseaseHerb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int c= (int) listDiseaseHerb.getAdapter().getItemId(position);
                diseaseListHerb  = (Item) parent.getItemAtPosition(c);
                passHerbName = diseaseListHerb.getHerbDiseaseList().toString();

                adapter.getItemId(position);

                Intent intentDisHerb = new Intent(herbs_disease_list.this, disease_herb_information.class);
                intentDisHerb.putExtra("HerbName", temp);
                intentDisHerb.putExtra("DiseaseName",passHerbName);
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