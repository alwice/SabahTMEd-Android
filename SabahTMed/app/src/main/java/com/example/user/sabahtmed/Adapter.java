package com.example.user.sabahtmed;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by User on 28/2/2017.
 */

public class Adapter extends ArrayAdapter<Item> implements Filterable {

    private Activity activity;
    int id;
    public ArrayList<Item> items;
    public ArrayList<Item> orig;



    public Adapter(Activity context, int resources, ArrayList<Item> object) {
        super(context, resources, object);
        this.activity = context;
        this.id = resources;
        this.items = object;
        this.orig = object;

    }


    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                ArrayList<Item> results = new ArrayList<>();
                if (orig == null)
                    orig = items;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (Item g : orig) {
                            //searching for herblist
                            if(g.getHerbsList() != null) {
                                if (g.getHerbsList().toString().toLowerCase()
                                        .contains(constraint.toString().toLowerCase())) {
                                    results.add(g);
                                }
                            }

                            //searching for disease list
                            if(g.getDiseaseList() != null) {
                                if (g.getDiseaseList().toString().toLowerCase()
                                        .contains(constraint.toString().toLowerCase())) {
                                    results.add(g);
                                }
                            }

                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }


            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                items = (ArrayList<Item>) results.values;
                notifyDataSetChanged();
            }


        };
    }


    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public long getItemId(int position) {

        int itemID;

        // orig will be null only if we haven't filtered yet:
        if (orig == null)
        {
            itemID = position;
        }
        else
        {
            itemID = orig.indexOf(items.get(position));
        }
        return itemID;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(id, null);
        }

        Item  HerbInfo = (Item) items.get(position);

        if(HerbInfo.getHerbsList()!=null) {
            TextView herbList = (TextView) convertView.findViewById(R.id.txtItem);
            herbList.setText(items.get(position).getHerbsList());

        }

        if(HerbInfo.getHerbsList()!=null ) {
            TextView herbList = (TextView) convertView.findViewById(R.id.txtItem);
            herbList.setText(items.get(position).getHerbsList());

        }


        if(HerbInfo.getDiseaseList()!=null) {
            TextView diseaseList = (TextView) convertView.findViewById(R.id.txtItemDisease);
            diseaseList.setText(items.get(position).getDiseaseList());

        }



        if(HerbInfo.getDiseaseListHerb()!=null) {
            TextView herbList = (TextView) convertView.findViewById(R.id.txtItem);
            herbList.setText(items.get(position).getDiseaseListHerb());
        }

//////////////////////////
        if(HerbInfo.getHerbDiseaseList()!=null) {
            TextView diseaseList = (TextView) convertView.findViewById(R.id.txtItemDisease);
            diseaseList.setText(items.get(position).getHerbDiseaseList());

        }

        Item HerbInfo1 =(Item) orig.get(position);

        if (HerbInfo1.getHerb_part()!=null) {


            TextView herbPart = (TextView) convertView.findViewById(R.id.herb_part);
            TextView herbDis = (TextView) convertView.findViewById(R.id.herb_disease);
            TextView herbIns = (TextView) convertView.findViewById(R.id.herb_instruction);


                herbPart.setText(HerbInfo1.getHerb_part());
                herbDis.setText(HerbInfo1.getHerb_name_disease());
                herbIns.setText(HerbInfo1.getHerb_instruction());



           // ((TextView) convertView.findViewById(R.id.herb_disease)).setText(HerbInfo.getHerb_name_disease());
           // ((TextView) convertView.findViewById(R.id.herb_instruction)).setText(HerbInfo.getHerb_instruction());
        }


        return convertView;
    }



}

