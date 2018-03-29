package com.TDP.proyectoBuscador.buscador.DataBases;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Business;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Commerce;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.Item;
import com.TDP.proyectoBuscador.buscador.CommerceAndDesired.ProductService;
import com.TDP.proyectoBuscador.buscador.Users.User;
import java.util.ArrayList;


public class DBController {

    private final static int DB_VERSION= 1;
    private final static String DB_NAME="DataBase";
    private static DBController dbC;
    private DBHelper dbHelper;
    private SQLiteDatabase sqlDB;



    //CONSTRUCTOR
    private DBController(Context context) {
        dbHelper = new DBHelper(context);
        sqlDB=dbHelper.getWritableDatabase();

    }

    public static DBController getDbC(Context context) {
        if(dbC==null)
            dbC=new DBController(context);
        return dbC;
    }

    public void open(){
       sqlDB =dbHelper.getWritableDatabase();
    }

    public void close(){
        sqlDB.close();
    }

    public int getCantCommerces(){
        String[] columns={CommercesTable.Columns.ID, CommercesTable.Columns.nameCol, CommercesTable.Columns.locationCol, CommercesTable.Columns.telephoneCol, CommercesTable.Columns.desiredCol, CommercesTable.Columns.reputationCol, CommercesTable.Columns.votesCol, CommercesTable.Columns.qualityCol, CommercesTable.Columns.typeCol, CommercesTable.Columns.sectorCol};
        Cursor cursor=sqlDB.query(CommercesTable.Columns.tableName,columns, null,null,null,null,null);

        return cursor.getCount();

    }

    public boolean insertItem(Item desired){

        boolean toReturn=false;

        if (desired!=null)
            toReturn = ProductServiceTable.insert(desired,sqlDB);
        return toReturn;
    }

    public Item findItem(String name){

        Item toReturn=null;

        if (name!=null && name!="")
            toReturn = ProductServiceTable.find(name, sqlDB);

        return toReturn;
    }

    public Commerce findCommerce(String name){

        Commerce toReturn=null;

        if (name!="" && name!=null)
            toReturn = CommercesTable.find(name, sqlDB);

        return toReturn;
    }

    public boolean insertSector(String name, int quantity){
        return SectorTable.insert(name,quantity,sqlDB);
    }

    public ArrayList<ContentValues> getSectors(){
        return SectorTable.find(sqlDB);
    }

    public boolean insertProductService(ProductService s){

        boolean toReturn=false;

        if (s != null)
            toReturn = ProductServiceTable.insert(s,sqlDB);

        return toReturn;
    }

    public boolean insertCommerce(Business e){

        boolean toReturn=false;

        if (e != null)
           toReturn = CommercesTable.insert((Commerce) e, sqlDB);
        return toReturn;
    }

    public User findUser(String name){
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context, DB_NAME,null,DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CommercesTable.getCreateTable());
            db.execSQL(CityTable.getCreateTable());
            db.execSQL(ProductServiceTable.getCreateTable());
            db.execSQL(SectorTable.getCreateTable());
            //db.execSQL(CommercesAuxTable.getCreateTable());
            //db.execSQL(UsersTable.getCreateTable());
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ProductServiceTable.getName());
            db.execSQL("DROP TABLE IF EXISTS " + CommercesTable.getName());
            db.execSQL("DROP TABLE IF EXISTS " + CityTable.getName());
            db.execSQL("DROP TABLE IF EXISTS " + SectorTable.getName());
            // db.execSQL("drop table if exists " +  CommercesAuxTable.getName());
            //db.execSQL("drop table if exists " + UsersTable.getName());

            onCreate(db);   // Llamamos al método onCreate para volver a crear las tablas.
        }

        /*public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }*/


    }



}
