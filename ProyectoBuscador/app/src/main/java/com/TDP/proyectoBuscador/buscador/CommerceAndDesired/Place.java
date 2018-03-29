package com.TDP.proyectoBuscador.buscador.CommerceAndDesired;

import android.database.sqlite.SQLiteDatabase;

public abstract class Place {

    protected String party;
    protected String state;
    protected int ID;

    public Place(String party, String state){
        this.party=party;
        this.state=state;
    }

    public String getParty() {
        return party;
    }

    public String getState() {
        return state;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setState(String state) {
        this.state = state;
    }

    public abstract String getDirection();

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
