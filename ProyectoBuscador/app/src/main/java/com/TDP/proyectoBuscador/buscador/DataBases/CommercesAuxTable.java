package com.TDP.proyectoBuscador.buscador.DataBases;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class CommercesAuxTable {

    //Attributes
    private SQLiteDatabase db;
    private final String[] columns={Columns.ID,Columns.nameCol,Columns.stateCol,Columns.cityCol, Columns.streetCol,Columns.roadCol,Columns.telephoneCol, Columns.desiredCol, Columns.reputationCol};
    protected final String createTable="create table if not exists "
            + Columns.tableName + "(" + Columns.ID + " integer primary key autoincrement, "
            + Columns.nameCol + " text not null," + Columns.stateCol
            + " text not null, " + Columns.cityCol + "text"
            + Columns.streetCol + "text" + Columns.roadCol + "text"
            + Columns.telephoneCol + "text not null," + Columns.desiredCol + "text not null,"
            + Columns.reputationCol + "integer not null";

    //Constructor
    private CommercesAuxTable(){}

    public static class Columns implements BaseColumns {

        public static final String ID= "_id";
        public static final String tableName = "CommercesAux";
        public static final String nameCol = "Name";
        public static final String stateCol = "State";
        public static final String cityCol = "City";
        public static final String streetCol= "Street";
        public static final String roadCol= "Road";
        public static final String telephoneCol= "Telephone";
        public static final String desiredCol= "Desired";
        public static final String reputationCol= "Reputation";

    }

    /**
     * @return true if the insert was successful
     * */
    public boolean insert(String name, String state, String city, String street, String road, String desireds, int reputation){

        ContentValues values= new ContentValues();
        values.put(Columns.nameCol,name);
        values.put(Columns.stateCol,state);
        values.put(Columns.cityCol,city);
        values.put(Columns.streetCol,street);
        values.put(Columns.roadCol,road);
        values.put(Columns.desiredCol,desireds);
        values.put(Columns.reputationCol,reputation);

        return db.insert(Columns.tableName,null,values)>0;
    }

    public Cursor getNames(){
        String[] columns={Columns.nameCol};
        return db.query(Columns.tableName,columns,null,null,null,null, null);
    }

    public  String getName(){
        return Columns.tableName;
    }

    public String[] getColumns(){
        return columns;
    }

    public String getCreateTable(){
        return createTable;
    }

    public boolean isEmpty() {
        return db.query(getName(), getColumns(), null, null, null, null, null)
                .getCount() == 0;
    }
}

