package com.TDP.proyectoBuscador.buscador.CommerceAndDesired;


import android.database.sqlite.SQLiteDatabase;

public class City extends Place {

    private String name;
    private int postalCode;
    private Street street;

    public City(Street street, String party, String state,String name, int postalCode){
        super(party,state);
        this.name=name;
        this.postalCode=postalCode;
        this.street=street;
    }


    /*
    public String getDirection() {
        return "Ubicación: "+name+" - "+street.getName()+" "+street.getNumber();
    }
    */
    public String getDirection() {
        return "Dirección: "+street.getName()+" "+street.getNumber();
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getName() {
        return name;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }


}
