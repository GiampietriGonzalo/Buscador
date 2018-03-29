package com.TDP.proyectoBuscador.buscador.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Item;


public abstract class ItemTable {

    public abstract Cursor getNames(SQLiteDatabase db);
    public abstract  String[] getColumns();

    //public Item find(String name, SQLiteDatabase db){return ProductServiceTable.find(name,db);}
    //public boolean insert(Item item, SQLiteDatabase db){return ProductServiceTable.insert(item,db);}

    public static String getCreateTable(){return "null";}
    public static String getName(){return "null";}

}
