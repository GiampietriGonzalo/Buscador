package com.TDP.proyectoBuscador.buscador.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Commerce;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class CommercesTable  extends  BusinessTable{

    private static String[] columns={Columns.ID, Columns.nameCol, Columns.locationCol, Columns.telephoneCol, Columns.desiredCol, Columns.reputationCol,Columns.votesCol, Columns.qualityCol,Columns.typeCol, Columns.sectorCol};
    protected static final String createTable= "CREATE TABLE "
            + Columns.tableName + " (" + Columns.ID + " INTEGER, "
            + Columns.nameCol + " TEXT, " + Columns.locationCol + " TEXT, "
            + Columns.telephoneCol + " TEXT, " + Columns.desiredCol + " TEXT, "
            + Columns.reputationCol + " INTEGER, "+ Columns.votesCol + " INTEGER, "
            + Columns.typeCol +" TEXT, "+ Columns.qualityCol +" TEXT, "+ Columns.sectorCol +" TEXT, "
            + "PRIMARY KEY (" + Columns.nameCol +") ON CONFLICT IGNORE)";


    public static boolean insert(Commerce commerce, SQLiteDatabase db){

        //insert listo

        Map<String,Double> desiredPrice= commerce.getDesiredPrice();
        Map<String,Double> desiredQuality=commerce.getDesiredQuality();
        JSONObject jsonDesiredPrice= new JSONObject();
        JSONObject jsonDesiredQuality= new JSONObject();
        JSONObject jsonLocation= new JSONObject();


        ArrayList<String> locationList= commerce.getLocation();

        ContentValues values= new ContentValues();

        try {

            for(String d: desiredPrice.keySet()) {
                jsonDesiredPrice.put(d, desiredPrice.get(d));
            }

            for(String d: desiredQuality.keySet()) {
                jsonDesiredQuality.put(d, ""+desiredQuality.get(d));
            }

            for(String s: locationList){
                jsonLocation.put(s,s);
            }



            values.put(Columns.desiredCol,jsonDesiredPrice.toString());
            values.put(Columns.qualityCol,jsonDesiredQuality.toString());
            values.put(Columns.nameCol,commerce.getName().trim());
            values.put(Columns.sectorCol, commerce.getSector());
            values.put(Columns.locationCol,jsonLocation.toString());
            values.put(Columns.telephoneCol,commerce.getTelephone());
            values.put(Columns.reputationCol,commerce.getReputation());
            values.put(Columns.votesCol,commerce.getVotes());
            values.put(Columns.typeCol,commerce.getType());

        }
        catch (JSONException e) {
                e.printStackTrace();
        }

        return db.insert(getName(),null,values)>0;

    }

    public static Commerce find(String name,SQLiteDatabase db){

        Cursor cursor=db.query(Columns.tableName,columns, Columns.nameCol +" = '"+name.trim()+"'",null,null,null,null);
        Commerce toReturn=null;

        JSONObject jsonDesiredPrice;
        JSONObject jsonDesiredQuality;
        JSONObject jsonLocation;

        Iterator<String> itLoc;
        Iterator<String> itPriceKeys;
        Iterator<String> itQualityKeys;

        ArrayList<String> locationList= new ArrayList<String>();
        Map<String,Double> desiredPriceMap= new HashMap<String, Double>();
        Map<String,Double> desiredQualityMap=new HashMap<String, Double>();

        String stringDesiredPrice;
        String stringDesiredQuality;
        String stringLocation;
        String key;
        Double value;


            try {

                if(cursor!=null && !cursor.isClosed()) {

                    cursor.moveToNext();

                    stringDesiredPrice = cursor.getString(4);
                    stringDesiredQuality = cursor.getString(7);
                    stringLocation=cursor.getString(2);
                    System.out.println("jsonLocation:  "+stringLocation);
                    System.out.println("jsonPrice:  "+stringDesiredPrice);
                    System.out.println("jsonQuality:  "+stringDesiredQuality);
                    jsonLocation = new JSONObject(stringLocation);




                    if(stringDesiredPrice!="{}"){

                        jsonDesiredPrice = new JSONObject(stringDesiredPrice.toString());
                        itPriceKeys = jsonDesiredPrice.keys();

                        while(itPriceKeys.hasNext()) {
                            key = itPriceKeys.next();
                            value = jsonDesiredPrice.getDouble(key);
                            desiredPriceMap.put(key, value);
                        }

                    }

                    if(stringDesiredQuality!="{}"){

                        jsonDesiredQuality = new JSONObject(stringDesiredQuality.toString());
                        itQualityKeys= jsonDesiredQuality.keys();

                        while(itQualityKeys.hasNext()){
                            key= itQualityKeys.next();
                            value= jsonDesiredQuality.getDouble(key);
                            desiredQualityMap.put(key,value);
                        }
                    }


                    //Si el objeto json obtenido desde la tabla no esta vacio entonce se itera las keys y se guarda en la lista de location del negocio.

                    if(stringLocation!="{}"){
                        itLoc=jsonLocation.keys();
                        locationList.add(itLoc.next());
                        System.out.print("Entro al while del iteraror de keys del jsonLocation en find()");
                        while (itLoc.hasNext())
                            locationList.add(itLoc.next());

                    }

                    toReturn = new Commerce(cursor.getString(1), cursor.getInt(0), locationList, cursor.getString(3), cursor.getString(7), cursor.getString(9));
                    toReturn.setDesiredPrice(desiredPriceMap);
                    toReturn.setDesiredQuality(desiredQualityMap);
                    toReturn.setReputation(cursor.getDouble(5));
                    toReturn.setVotes(cursor.getInt(6));

                    cursor.close();
                }

            } catch (JSONException e) {
                System.out.println("Error en JSONObject de Location en findCommerce");
                e.printStackTrace();
                }

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
        public static final String tableName = "Commerces";
        public static final String nameCol = "Name";
        public static final String locationCol = "Location";
        public static final String telephoneCol= "Telephone";
        public static final String desiredCol= "Desireds";
        public static final String reputationCol= "Reputation";
        public static final String votesCol="Votes";
        public static final String typeCol="Type";
        public static final String qualityCol="Quality";
        public static final String sectorCol="Sector";

    }

}
