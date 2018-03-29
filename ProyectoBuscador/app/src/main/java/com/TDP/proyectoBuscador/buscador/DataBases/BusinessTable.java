package com.TDP.proyectoBuscador.buscador.DataBases;


import android.database.sqlite.SQLiteDatabase;

import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Commerce;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Shop;

public abstract class BusinessTable {

    public  boolean insert(Shop shop, SQLiteDatabase db){return false;}
    public  boolean insert(Entrepreneur entrepreneur, SQLiteDatabase db){return false;}
    public  static Commerce find(String name, SQLiteDatabase db){return null;}

    public static String getName(){return "null";}
    public abstract String[] getColumns();
    public static String getCreateTable(){return "null";}
}
