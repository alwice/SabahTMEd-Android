package com.example.user.sabahtmed;

import java.security.PublicKey;

/**
 * Created by User on 28/2/2017.
 */

public class Item {
    private String herbsList;
    private String diseaseList;
    private String diseaseListHerb;

    public String getHerbDiseaseList() {
        return herbDiseaseList;
    }

    public void setHerbDiseaseList(String herbDiseaseList) {
        this.herbDiseaseList = herbDiseaseList;
    }

    private String herbDiseaseList;

    //for herb name assocate
    private String herb_name;
    private String herb_eng;
    private String herb_pic;

    private String herb_sci;


    //for herb infroamtion and part
    private String herb_name_disease;
    private String herb_part;
    private String herb_instruction;


    public String getDiseaseListHerb() {
        return diseaseListHerb;
    }

    public void setDiseaseListHerb(String diseaseListHerb) {
        this.diseaseListHerb = diseaseListHerb;
    }


    public String getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(String diseaseList) {
        this.diseaseList = diseaseList;
    }


    public String getHerbsList() {
        return herbsList;
    }

    public void setHerbsList(String herbsList) {
        this.herbsList = herbsList;
    }

    public String getHerb_name() {
        return herb_name;
    }

    public void setHerb_name(String herb_name) {
        this.herb_name = herb_name;
    }

    public String getHerb_eng() {
        return herb_eng;
    }

    public void setHerb_eng(String herb_eng) {
        this.herb_eng = herb_eng;
    }

    public String getHerb_pic() {
        return herb_pic;
    }

    public void setHerb_pic(String herb_pic) {
        this.herb_pic = herb_pic;
    }

    public String getHerb_sci() {
        return herb_sci;
    }

    public void setHerb_sci(String herb_sci) {
        this.herb_sci = herb_sci;
    }


    public String getHerb_name_disease() {
        return herb_name_disease;
    }

    public void setHerb_name_disease(String herb_name_disease) {
        this.herb_name_disease = herb_name_disease;
    }

    public String getHerb_part() {
        return herb_part;
    }

    public void setHerb_part(String herb_part) {
        this.herb_part = herb_part;
    }

    public String getHerb_instruction() {
        return herb_instruction;
    }

    public void setHerb_instruction(String herb_instruction) {
        this.herb_instruction = herb_instruction;
    }
}