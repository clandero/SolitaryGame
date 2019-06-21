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
    // Nombre de la base de datos, y tabla asociada
    private static final String DATABASE_NAME = "MiDB";
    private static final String DATABASE_TABLE = "solitario";
    private static final int DATABASE_VERSION = 1;
    // Las constantes que representan las columnas de la tabla
    private static final String FILAID = "_id";
    private static final String NOMBRE = "nombre";
    private static final String VICTORIAS = "victorias";
    private static final String DERROTAS = "derrotas";

    private static final String TAG = "DBHelper";

    // Este String contiene el comando SQL para la creación de la base de datos
    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + "(" + FILAID + " integer primary key, " + NOMBRE + " text not null," + VICTORIAS + "integer not null," + DERROTAS + " integer not null);";
    private final Context contexto; // Contexto de la aplicacion
    private DatabaseHelper Helper; // Clase interna para acceso a base de datos SQL
    private SQLiteDatabase db; // La base de datos SQL
    public DBClass(Context contexto) {
        //Log.i("alo","olaola");
        this.contexto = contexto;
        Helper = new DatabaseHelper(contexto);
    }
    // Clase privada interna para acceso a base de datos SQL
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private Context context;
        DatabaseHelper(Context contexto) {
            super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
            context = contexto;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
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

    public long agregaJugador(String nombre,int victorias, int derrotas){
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE, nombre);
        valores.put(VICTORIAS, victorias);
        valores.put(DERROTAS, derrotas);
        return db.insert(DATABASE_TABLE, null, valores);
    }
    public boolean eliminaJugador(long filaID){
        return db.delete(DATABASE_TABLE,FILAID + "="+filaID,null)>0;
    }
    public Cursor recuperaJugadores(){
        return db.query(DATABASE_TABLE,new String[]{FILAID,NOMBRE,VICTORIAS,DERROTAS},null,null,null,null,null);
    }

    public Cursor recuperaJugador(long filaID) throws SQLException {
        Cursor c = db.query(DATABASE_TABLE, new String[]{FILAID,NOMBRE,VICTORIAS,DERROTAS},FILAID+"="+filaID,null,null,null,null);
        if (c != null){
            c.moveToFirst();
        }
        return c;
    }
    public boolean actualizaJugador(long filaID, String nombre, int victorias, int derrotas){
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE,nombre);
        valores.put(VICTORIAS, victorias);
        valores.put(DERROTAS, derrotas);
        return db.update(DATABASE_TABLE,valores,FILAID+"="+filaID,null)>0;
    }
    public Cursor recuperaJugadorPorNombre(String nombre) throws SQLException{
        Cursor c = db.query(DATABASE_TABLE, new String[]{FILAID,NOMBRE,VICTORIAS,DERROTAS},NOMBRE+"="+ DatabaseUtils.sqlEscapeString(nombre),null,null,null,null);
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }
    public void eliminaJugadores(){
        contexto.deleteDatabase(DATABASE_NAME);
    }
}

