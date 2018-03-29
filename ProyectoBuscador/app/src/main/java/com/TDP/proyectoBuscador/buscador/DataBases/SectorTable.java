package com.TDP.proyectoBuscador.buscador.DataBases;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

/*Tabla donde se guardan los rubros*/

public class SectorTable {

    private final static String[] columns={Columns.ID, Columns.nameCol ,Columns.quantityCol};

    protected final static String createTable= "CREATE TABLE "
            + Columns.tableName + " (" + Columns.ID + " INTEGER, "
            + Columns.nameCol + " TEXT, "
            + Columns.quantityCol + " INTEGER, PRIMARY KEY (" + Columns.nameCol +") ON CONFLICT IGNORE)";

    public static boolean insert(String name, int quantity, SQLiteDatabase db){

        ContentValues cv= new ContentValues();
        cv.put("Name",name);
        cv.put("Quantity",quantity);

        return db.insert(getName(),null,cv) > 0;

    }

    public static ArrayList<ContentValues> find(SQLiteDatabase db){

        ArrayList<ContentValues> toReturn=new ArrayList<ContentValues>();
        ContentValues cv;

        Cursor cursor=db.query(Columns.tableName,columns, null,null,null,null,null);

        //if(cursor.moveToFirst()){

        while(cursor.moveToNext()){

            cv=new ContentValues();

            cv.put("Name",cursor.getString(1));
            cv.put("Quantity",cursor.getInt(2));

            toReturn.add(cv);
        }

        //}

        return toReturn;

    }

    public static String getName(){
        return Columns.tableName;
    }

    public static String getCreateTable() {
        return createTable;
    }

    public String[] getColumns(){
        return columns;
    }

    protected class Columns implements BaseColumns {

        public static final String ID= "id";
        public static final String nameCol="Name";
        public static final String tableName = "Sectors";
        public static final String quantityCol = "Quantity";

    }

}
