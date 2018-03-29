package com.TDP.proyectoBuscador.buscador.DataBases;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/*Tabla de usuarios*/

public class UsersTable {

    //Attributes
    private SQLiteDatabase db;
    private final static String[] columns={Columns.ID,Columns.nameCol,Columns.passwordCol,Columns.typeCol, Columns.reputationCol};
    protected final static String createTable="create table if not exists "
            + Columns.tableName + "(" + Columns.ID + " integer primary key autoincrement, "
            + Columns.nameCol + " text not null, " + Columns.passwordCol
            + " text not null, " + Columns.typeCol + " text not null,"
            + Columns.reputationCol + "integer not null";

    //Constructor
    private UsersTable(){}

    public static class Columns implements BaseColumns {

        public static final String ID= "_id";
        public static final String tableName = "Users";
        public static final String nameCol = "Name";
        public static final String passwordCol = "Password";
        public static final String typeCol = "Type";
        public static final String reputationCol= "Reputation";

    }

    /**
     * @return true if the insert was successful
     * */
    public boolean insert(String name, int searches, String commerces, String tags){

        ContentValues values= new ContentValues();
        values.put(Columns.nameCol,name);
        values.put(Columns.passwordCol,searches);
        values.put(Columns.typeCol,commerces);
        values.put(Columns.reputationCol,tags);

        return db.insert(Columns.tableName,null,values)>0;
    }

    public Cursor getNames(){
        String[] columns={Columns.nameCol};
        return db.query(Columns.tableName,columns,null,null,null,null, null);
    }

    public static String getName(){
        return Columns.tableName;
    }

    public String[] getColumns(){
        return columns;
    }

    public static String getCreateTable(){
        return createTable;
    }

    public boolean isEmpty() {
        return db.query(getName(), getColumns(), null, null, null, null, null)
                .getCount() == 0;
    }
}

