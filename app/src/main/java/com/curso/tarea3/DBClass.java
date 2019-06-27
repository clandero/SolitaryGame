package com.curso.tarea3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBClass {
    private static final String DATABASE_NAME = "MiDB";
    private static final String DATABASE_TABLE = "solitario";
    private static final int DATABASE_VERSION = 1;
    private static final String FILAID = "_id";
    private static final String NOMBRE = "nombre";
    private static final String PARTIDAS = "partidas";
    private static final String VICTORIAS = "victorias";
    private static final String DERROTAS = "derrotas";
    private static final String TAG = "DBClass";

    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + "(" + FILAID + " integer primary key autoincrement, " + NOMBRE + " text not null," + PARTIDAS + " integer not null," + VICTORIAS + " integer not null," + DERROTAS + " integer not null);";
    private final Context contexto; // Contexto de la aplicacion
    private DatabaseHelper Helper; // Clase interna para acceso a base de datos SQL
    private SQLiteDatabase db; // La base de datos SQL

    DBClass(Context contexto) {
        //Log.i("alo","olaola");
        this.contexto = contexto;
        Helper = new DatabaseHelper(contexto);
    }
    // Clase privada interna para acceso a base de datos SQL
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context contexto) {
            super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.w(TAG,"Creando la base de datos");
                db.execSQL(DATABASE_CREATE);
                //db.execSQL("create table solitario(_id integer primary key autoincrement,nombre text not null,victorias integer not null,derrotas integer not null)");
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
            Log.w(TAG, "Actualizando la base de datos desde la versión " + versionAnterior +
                    " a la versión " + versionNueva);
            db.execSQL("DROP TABLE IF EXISTS contactos");
            onCreate(db);
        }
    }
    // Abre la base de datos para escritura
    public DBClass abre() throws SQLException {
        // Abre la base de datos para escritura
        db = Helper.getWritableDatabase();
        return this;
    }
    // Cierra la base de datos
    public void cierra() {
        Helper.close();
    }

    public long agregaJugador(String nombre, int partidas, int victorias, int derrotas){
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE, nombre);
        valores.put(PARTIDAS, partidas);
        valores.put(VICTORIAS, victorias);
        valores.put(DERROTAS, derrotas);
        return db.insert(DATABASE_TABLE, null, valores);
    }
    public boolean eliminaJugadorPorId(long filaID){
        return db.delete(DATABASE_TABLE,FILAID + "="+filaID,null)>0;
    }
    public Cursor recuperaJugadores(){
        return db.query(DATABASE_TABLE,new String[]{FILAID,NOMBRE,PARTIDAS,VICTORIAS,DERROTAS},null,null,null,null,null);
    }

    public Cursor recuperaJugadorPorId(long filaID) throws SQLException {
        Cursor c = db.query(DATABASE_TABLE, new String[]{FILAID,NOMBRE,PARTIDAS,VICTORIAS,DERROTAS},FILAID+"="+filaID,null,null,null,null);
        if (c != null){
            c.moveToFirst();
        }
        return c;
    }
    public boolean actualizaJugador(long filaID, String nombre, int partidas, int victorias, int derrotas){
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE,nombre);
        valores.put(PARTIDAS,partidas);
        valores.put(VICTORIAS, victorias);
        valores.put(DERROTAS, derrotas);
        return db.update(DATABASE_TABLE,valores,FILAID+"="+filaID,null)>0;
    }
    public Cursor recuperaJugadorPorNombre(String nombre) throws SQLException{
        Cursor c = db.query(DATABASE_TABLE, new String[]{FILAID,NOMBRE,PARTIDAS,VICTORIAS,DERROTAS},NOMBRE+"="+ DatabaseUtils.sqlEscapeString(nombre),null,null,null,null);
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }
    public void eliminaJugadores(){
        contexto.deleteDatabase(DATABASE_NAME);
    }
}

