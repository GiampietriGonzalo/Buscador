package com.TDP.proyectoBuscador.buscador.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*Clase abstracta que generaliza la tabla de ciudades*/

public abstract class PlaceTable {

    public  abstract Cursor getNames(SQLiteDatabase db);
    public abstract  String[] getColumns();
    public static String getCreateTable(){return "null";}
    public static String getName(){return "null";}
}
