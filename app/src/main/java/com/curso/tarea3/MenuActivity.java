package com.curso.tarea3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;

public class MenuActivity extends AppCompatActivity {

    Button new_game_as;
    Button new_anonymous_game;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        new_game_as = findViewById(R.id.play_game_as);
        new_anonymous_game = findViewById(R.id.play_anonymous);
        input = findViewById(R.id.playername);
        // SI DA UN NOMBRE DE USUARIO, EN EL JUEGO, CONSULTAR A LA BASE DE DATOS:
            //SI EXISTE, ENTONCES RECUPERE SUS DATOS
            //SI NO, CREE EL NUEVO USUARIO
        // SI QUIERE JUGAR COMO ANONIMO, PASA COMO PARAMETRO EL STRING 'anonimo'
            // SI EL STRING RECIBIDO POR EL JUEGO ES 'anonimo', ENTONCES NO HAGA NADA EN LA BASE DE DATOS

        new_game_as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre_jugador = input.getText().toString();
                Log.d("PLAYER_NAME",nombre_jugador);
                //HACER EL INTENT PARA IR A LA ACTIVIDAD DONDE ESTE EL JUEGO
                //Intent intent = new Intent(this, MainActivity.class);
                //startActivity(intent);
                //LLAMAR A ALGUNA FUNCION DONDE EL JUEGO INICIE LA PARTIDA COMO EL USUARIO
                //MainActivity.startGame(nombre_jugador);
            }
        });
        new_anonymous_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre_jugador = "anonimo";
                Log.d("PLAYER_NAME",nombre_jugador);
                //HACER EL INTENT PARA IR A LA ACTIVIDAD DONDE ESTE EL JUEGO
                //Intent intent = new Intent(this, MainActivity.class);
                //startActivity(intent);
                //LLAMAR A ALGUNA FUNCION DONDE EL JUEGO INICIE LA PARTIDA COMO EL USUARIO
                //MainActivity.startGame(nombre_jugador);
            }
        });
    }
}
