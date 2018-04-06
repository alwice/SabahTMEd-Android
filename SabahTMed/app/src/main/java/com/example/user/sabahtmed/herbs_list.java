package com.example.user.sabahtmed;


import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.app.SearchManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import android.widget.TextView;

public class herbs_list extends AppCompatActivity {
 //   String[] herbsList;


   // ArrayList<String> listItem;
   ArrayList<Item> arrayList= new ArrayList<Item>();

   //ArrayAdapter<String> adapter;
   String a;
    Item dt;
    Adapter adapter;
    ListView listherb ;
    SearchView sv;
    private DatabaseHelper dbHelper;

    public String getA() {
        return a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.herb_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //back toolbar
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
            Cursor cursor=dbHelper.QueryData("select herb_name from herbs group by herb_name");
            if (cursor!=null)
            {
                if (cursor.moveToFirst())
                {
                    do{
                        Item item = new Item();
                        item.setHerbsList(cursor.getString(0));
                        arrayList.add(item);
                }while (cursor.moveToNext());
                }
            }
        }catch(SQLException ioe) {}



        adapter = new Adapter(this, R.layout.herb_list,arrayList);

        listherb = (ListView) findViewById(R.id.list);
        listherb.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listherb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int b= (int) listherb.getAdapter().getItemId(position);
                dt  = (Item) parent.getItemAtPosition(b);
                a = dt.getHerbsList().toString();

                adapter.getItemId(position);

                Intent intentHList = new Intent(herbs_list.this, herbs_disease_list.class);
                intentHList.putExtra("HerbName", a);
                startActivity(intentHList);
            }
        });

        sv=(SearchView)findViewById(R.id.searcHerb);
       listherb.setTextFilterEnabled(true);


       sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
