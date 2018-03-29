package com.TDP.proyectoBuscador.buscador.DataBases;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.City;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Street;

import org.json.JSONException;
import org.json.JSONObject;

public class CityTable extends PlaceTable {

    private final  String[] columns={Columns.ID, Columns.nameCol, Columns.partyCol, Columns.stateCol, Columns.streetCol, Columns.postalCodeCol};

    protected static final  String createTable= "CREATE TABLE "
            + Columns.tableName + " (" + Columns.ID + " INTEGER, "
            + Columns.nameCol + " TEXT, " + Columns.partyCol + " TEXT, "
            + Columns.stateCol + " TEXT, " + Columns.streetCol + " TEXT, "
            + Columns.postalCodeCol + " INTEGER, "
            + "PRIMARY KEY (" + Columns.nameCol +") ON CONFLICT IGNORE)";



    public City findCity(String name,SQLiteDatabase db){

        //TODO Rehacer

        String query = "Select * FROM " + Columns.tableName + " WHERE " + Columns.nameCol + " =  \"" + name + "\"" ;
        Cursor cursor=db.rawQuery(query,null);
        City toReturn=null;
        JSONObject jsonStreet;
        String stringStreet;
        Street street;

        try {
            if (cursor.moveToFirst()) {

                cursor.moveToFirst();

                //Objeto el objeto Street de la tabla usando un JSONObject.
                stringStreet = cursor.getString(4);
                jsonStreet = new JSONObject(stringStreet);
                street= (Street) jsonStreet.get("street");

                toReturn = new City(street,cursor.getString(2),cursor.getString(3), cursor.getString(1), cursor.getInt(5));
                cursor.close();
            }

        }
        catch (JSONException e){System.out.println("Error al converitr el JSONObject de Street");}
        return toReturn;
    }


    public boolean insert(String name, String party, String state, Street street,int postalCode,SQLiteDatabase db){

        //TODO Hacer json correctamente

        ContentValues values= new ContentValues();
        JSONObject jsonStreet= new JSONObject();



        String stringStreet;

        try {
            jsonStreet.put("street",street);
        }
        catch (JSONException e){System.out.println("Error en JSONObject.put al insertar un Street a la BD");}

        stringStreet=jsonStreet.toString();

        values.put(Columns.nameCol,name);
        values.put(Columns.partyCol,party);
        values.put(Columns.stateCol,state);
        values.put(Columns.streetCol,stringStreet);
        values.put(Columns.postalCodeCol,postalCode);

        return db.insert(Columns.tableName,null,values)>0;
    }

    public Cursor getNames(SQLiteDatabase db) {
        String[] columns={Columns.nameCol};
        return db.query(Columns.tableName,columns,null,null,null,null, null);

    }

    public static String getName() {
        return Columns.tableName;
    }


    public String[] getColumns() {
        return columns;
    }


    public static String getCreateTable() {
        return createTable;
    }


    public boolean isEmpty(SQLiteDatabase db) {
        return db.query(getName(), getColumns(), null, null, null, null, null)
                .getCount() == 0;
    }

    private class Columns implements BaseColumns {

        public static final String ID= "id";
        public static final String tableName = "City";
        public static final String nameCol = "Name";
        public static final String partyCol = "State";
        public static final String stateCol= "Telephone";
        public static final String streetCol= "Desired";
        public static final String postalCodeCol= "Reputation";

    }

}
