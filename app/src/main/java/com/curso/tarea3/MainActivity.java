package com.curso.tarea3;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends Activity {
    // NO CONSIDERAR ESTO COMMO MAIN ACTIVITY, SON EJEMPLOS DE COMO INTERACTUAR CON LA BASE DE DATOS ;^)
    /*DBClass db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBClass(this);
    }
    public void agregaJugadores(View view) {
// Agrega los contactos iniciales
        db.abre();
        db.agregaJugador("Bill Gates",0, 0,0);
        db.agregaJugador("Sheldon Cooper",0, 0,0);
        db.agregaJugador("Homer Simpson",0, 0,0);
        db.agregaJugador("James Bond",0, 0,0);
        db.agregaJugador("Jon Snow",0, 0,0);
        db.cierra();
    }
    public void buscaJugadorPorId(View view) {
        EditText et = findViewById(R.id.IDBuscar);
        long id = Integer.parseInt(et.getText().toString());
        DisplayToast("Se busca el contacto (" + id + ")");
        db.abre();
        Cursor c = db.recuperaJugadorPorId(id);
        if (c.moveToFirst()) {
            MuestraJugador(c);
        }
        else {
            DisplayToast("Jugador no fue encontrado");
        }
        c.close();
        db.cierra();
    }
    public void buscaJugadorPorNombre(View view) {
//Recupera un contacto por nombre
        EditText et = findViewById(R.id.NombreBuscar);
        String nombre = et.getText().toString();
        DisplayToast("Se busca el jugador (" + nombre + ")");
        db.abre();
        Cursor c = db.recuperaJugadorPorNombre(nombre);
        if (c.moveToFirst()) {
            MuestraJugador(c);
        }
        else {
            DisplayToast("Jugador no fue encontrado");
        }
        while(c.moveToNext()) {
            MuestraJugador(c);
        }
        c.close();
        db.cierra();
    }
    public void actualizaJugador(View view) {
// Actualiza un contacto
        EditText etID = findViewById(R.id.IDAgregar);
        long id = Integer.parseInt(etID.getText().toString());

        EditText etNombre = findViewById(R.id.NombreAgregar);
        String nombre = etNombre.getText().toString();

        EditText etPartidas = findViewById(R.id.PartidasAgregar);
        String partidas = etPartidas.getText().toString();

        EditText etVictorias = findViewById(R.id.VictoriasAgregar);
        String victorias = etVictorias.getText().toString();

        EditText etDerrotas = findViewById(R.id.DerrotasAgregar);
        String derrotas = etDerrotas.getText().toString();

        DisplayToast("Se actualiza el contacto " + id + " con el nombre "
                + nombre + " partidas: " + partidas + " victorias: " + victorias + " y derrotas: " + derrotas);
        db.abre();
        if (db.actualizaJugador(id, nombre, Integer.parseInt(partidas), Integer.parseInt(victorias), Integer.parseInt(derrotas))) {
            DisplayToast("Actualización exitosa");
        }
        else {
            DisplayToast("Actualización fallida");
        }
        db.cierra();
    }
    public void eliminaJugadorPorId(View view) {
// Elimina un contacto
        EditText et = findViewById(R.id.IDEliminar);
        long id = Integer.parseInt(et.getText().toString());
        DisplayToast("Se elimina el contacto (" + id + ")");
        db.abre();
        if (db.eliminaJugadorPorId(id)) {
            DisplayToast("Eliminación exitosa");
        }
        else {
            DisplayToast("Eliminación fallida");
        }
        db.cierra();
    }
    public void eliminaJugadores(View view) {
        DisplayToast("Elimina todos los Jugadores");
        db.eliminaJugadores();
        DisplayToast("Base de datos Contactos está vacía");
    }
    public void muestraJugadores(View view) {
// Recupera contactos
        db.abre();
        Cursor c = db.recuperaJugadores();
        if (c.moveToFirst()) {
            do {
                MuestraJugador(c);
            } while(c.moveToNext());
        }
        else {
            DisplayToast("Base de datos Contactos está vacía");
        }
        c.close();
        db.cierra();
    }
    public void MuestraJugador(Cursor c) {
        DisplayToast("id: " + c.getString(0) + "\n" +
                "nombre: " + c.getString(1) + "\n" +
                "partidas: " + c.getString(2) + "\n" +
                "victorias: " + c.getString(3) + "\n" +
                "derrotas: " + c.getString(4) + "\n");
    }
    private void DisplayToast(String mensaje) {
        Toast.makeText(getBaseContext(), mensaje, Toast.LENGTH_SHORT).show();
    }*/
}