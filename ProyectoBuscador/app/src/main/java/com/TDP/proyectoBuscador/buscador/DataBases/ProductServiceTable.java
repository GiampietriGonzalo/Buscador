package com.TDP.proyectoBuscador.buscador.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Item;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.ProductService;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static android.content.ContentValues.TAG;

public class ProductServiceTable extends ItemTable {

    //Attributes
    private static final String[] columns={Columns.ID, Columns.nameCol, Columns.searchesCol, Columns.commercesCol, Columns.tagsCol};
    protected static String createTable= "CREATE TABLE "
            + Columns.tableName + " (" + Columns.ID + " INTEGER, "
            + Columns.nameCol + " TEXT, " + Columns.searchesCol + " INTEGER, "
            + Columns.commercesCol + " TEXT, " + Columns.tagsCol + " TEXT, "
            + "PRIMARY KEY (" + Columns.nameCol +") ON CONFLICT IGNORE)";


    public static boolean insert(Item item, SQLiteDatabase db) {

        List<String> businesses= item.getBusiness();
        List<String> tags= item.getTags();

        JSONObject jsonCommerces=new JSONObject();
        JSONObject jsonTags=new JSONObject();
        ContentValues values= new ContentValues();

        values.put(Columns.nameCol,item.getName());
        values.put(Columns.searchesCol,item.getSearches());

        //Convierto Hashtable a String y lo guardo.
        try {

            for(String b: businesses)
                jsonCommerces.put(b,b);

            for(String tag: tags)
                jsonTags.put(tag,tag);

            values.put(Columns.nameCol,item.getName());
            values.put(Columns.searchesCol,item.getSearches());
            values.put(Columns.commercesCol, jsonCommerces.toString());
            values.put(Columns.tagsCol, jsonTags.toString());
        }

        catch (JSONException e){}
        return db.insert(Columns.tableName,null, values)>0;
    }


    public static Item find(String name,SQLiteDatabase db){

        JSONObject jsonCommerces=new JSONObject();
        JSONObject jsonTags=new JSONObject();
        String commerces;
        List<String> tags= new LinkedList<String>();
        List<String> businesses= new LinkedList<String>();
        Item toReturn=null;
        Iterator<String> commercesKey;
        Iterator<String> tagsKey;
        Cursor cursor=db.query(Columns.tableName,columns,Columns.nameCol +" = '"+name.trim().toLowerCase()+"'",null,null,null,null);


        try {
            while (!cursor.isClosed() && cursor.moveToNext()) {

                toReturn = new ProductService(name.trim());


                jsonCommerces = new JSONObject(cursor.getString(3));
                commercesKey=jsonCommerces.keys();
                while(commercesKey.hasNext())
                    businesses.add(commercesKey.next());

                jsonTags = new JSONObject(cursor.getString(4));
                tagsKey=jsonTags.keys();
                while(tagsKey.hasNext())
                    tags.add(tagsKey.next());

                toReturn.setTags(tags);
                toReturn.setSearches(cursor.getInt(2));
                toReturn.setCommerces(businesses);

                cursor.close();
            }
        }
        catch (JSONException e){
            Log.d(TAG, "Error en JSON de ProductServiceTable");
        }

        return toReturn;

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


    public static class Columns implements BaseColumns {

        public static final String ID= "id";
        public static final String tableName = "ProductService";
        public static final String nameCol = "Name";
        public static final String searchesCol = "Searches";
        public static final String commercesCol = "Commerces";
        public static final String tagsCol= "Tags";

    }

}
