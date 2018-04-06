package com.example.user.sabahtmed;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 9/3/2017.
 */

public class disease_list extends AppCompatActivity{

    ArrayList<Item> arrayListDisease= new ArrayList<Item>();
    Adapter adapter;
    ListView listDisease ;
    SearchView diseaseSearch;
    private DatabaseHelper dbHelper;

    Item diseaseList;
    String passDiseaseName;


    public String getPassDiseaseName() {
        return passDiseaseName;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dbHelper = new DatabaseHelper(this);

        try{
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        }catch (SQLException e)
        {
        }

        try
        {
            Cursor cursor=dbHelper.QueryData("select herb_disease from herbs group by herb_disease");
            if (cursor!=null)
            {
                if (cursor.moveToFirst())
                {
                    do{
                        Item item = new Item();
                        item.setDiseaseList(cursor.getString(0));
                        arrayListDisease.add(item);
                    }while (cursor.moveToNext());
                }
            }
        }catch(SQLException ioe) {}




        adapter = new Adapter(this, R.layout.disease_list,arrayListDisease);

        listDisease= (ListView) findViewById(R.id.listDis);
        listDisease.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listDisease.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int b= (int) listDisease.getAdapter().getItemId(position);
                diseaseList  = (Item) parent.getItemAtPosition(b);
                passDiseaseName = diseaseList.getDiseaseList().toString();

                adapter.getItemId(position);

                Intent intentDisease = new Intent(disease_list.this, disease_herb_list.class);
                intentDisease.putExtra("DiseaseName", passDiseaseName);
                startActivity(intentDisease);
            }
        });

        diseaseSearch=(SearchView)findViewById(R.id.searchDis);
        listDisease.setTextFilterEnabled(true);


        diseaseSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                //for filter
                adapter.getFilter().filter(text);

                return true;
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
