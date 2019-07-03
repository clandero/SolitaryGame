package com.curso.tarea3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    //variables para guardar estado
    private static final int move = 0;
    private static final long time_play = 0;
    private static final int card = 0;
    private static final Baraja saved_baraja = null;
    private Handler mHandler = new Handler();

    //variables del juego
    private int esperando_mov = 0;
    private Baraja baraja;
    private int movements;
    private int pila_llamada;
    private long mStartTime = 0L;
    private int cartas_correctas;
    private long time = mStartTime;
    private long aux_time = 0;
    private TextView movimientos,cartas,tiempo;
    private ImageView shuffle,pica,corazon,trebol,diamante;
    private ImageView[] cards;
    private final int CARD_ID[] = {R.id.card_1,R.id.card_2,R.id.card_3,R.id.card_4,
            R.id.card_5,R.id.card_6,R.id.card_7,R.id.card_8,R.id.card_9,R.id.card_10,R.id.card_11,R.id.card_12};
    private final int CARD_IMAGE[][] ={{R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,
            R.drawable.c10,R.drawable.jc,R.drawable.qc,R.drawable.kc},{R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,R.drawable.d7,
            R.drawable.d8,R.drawable.d9,R.drawable.d10,R.drawable.jd,R.drawable.qd,R.drawable.kd},{R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,
            R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9,R.drawable.p10,R.drawable.jp,R.drawable.qp,R.drawable.kp},{R.drawable.t2,R.drawable.t3,
            R.drawable.t4,R.drawable.t5,R.drawable.t6,R.drawable.t7,R.drawable.t8,R.drawable.t9,R.drawable.t10,R.drawable.jt,R.drawable.qt,
            R.drawable.kt}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (savedInstanceState == null) {
            baraja = new Baraja();
            baraja.inicio();
        }

        if(mStartTime==0L){
            mStartTime= SystemClock.uptimeMillis();
            mHandler.removeCallbacks(mUpdateTimeTask);
            mHandler.postDelayed(mUpdateTimeTask,100);
        }


        //Area de contadores
        movimientos = findViewById(R.id.movimientos);
        cartas = findViewById(R.id.cartas_ubicadas);
        tiempo = findViewById(R.id.tiempo);

        //Area de decks finales
        shuffle = findViewById(R.id.shuffle);
        pica = findViewById(R.id.pica_deck);
        pica.setImageResource(R.drawable.p1);
        corazon = findViewById(R.id.corazon_deck);
        corazon.setImageResource(R.drawable.c1);
        trebol = findViewById(R.id.trebol_deck);
        trebol.setImageResource(R.drawable.t1);
        diamante = findViewById(R.id.diamante_deck);
        diamante.setImageResource(R.drawable.d1);
        cartas_correctas = 4;
        cartas.setText(Integer.toString(cartas_correctas));

        //Area de juego
        cards = new ImageView[12];

        //
        for (int i = 0; i < 12;i++) {

                cards[i] = this.findViewById(CARD_ID[i]);
                int pila = 0;
                Carta aux = baraja.get_carta_de_pila(i);
                String mono = aux.getPalo();
                if (mono.equals("corazon"))
                    pila = 0;
                if (mono.equals("diamante"))
                    pila = 1;
                if (mono.equals("pica"))
                    pila = 2;
                if (mono.equals("trebol"))
                    pila = 3;


                cards[i].setImageResource(CARD_IMAGE[pila][aux.getNumero() - 2]);
                cards[i].setOnClickListener(onClickListener);

        }
        corazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( esperando_mov == 1){

                    Carta cartaux = baraja.get_carta_de_pila(pila_llamada);
                    int pila_aux = 0;

                    boolean aux = baraja.insertar_a_base(pila_llamada,"corazon");
                    esperando_mov = 0;

                    if (aux){
                        Toast.makeText(GameActivity.this, "MOVIDA A BASE CON EXITO", Toast.LENGTH_SHORT).show();
                        corazon.setImageResource(CARD_IMAGE[pila_aux][cartaux.getNumero()- 2]);
                        paint();
                        if (baraja.juego_terminado()){

                            }
                        }

                    else{
                        Log.d("MOV INVALID","AAAAAAAAAAAAAAA");
                        Toast.makeText(GameActivity.this,"Mov no realizado",Toast.LENGTH_SHORT).show();}

                    movements++;
                }
                else
                    Toast.makeText(GameActivity.this,"Nada que hacer aun", Toast.LENGTH_SHORT).show();
            }
        });
        pica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( esperando_mov == 1){

                    Carta cartaux = baraja.get_carta_de_pila(pila_llamada);
                    int pila_aux = 2;

                    boolean aux = baraja.insertar_a_base(pila_llamada,"pica");
                    esperando_mov = 0;

                    if (aux){
                        Toast.makeText(GameActivity.this, "MOVIDA A BASE CON EXITO", Toast.LENGTH_SHORT).show();
                        pica.setImageResource(CARD_IMAGE[pila_aux][cartaux.getNumero()- 2]);
                        paint();
                    }

                    else{
                        Log.d("MOV INVALID","AAAAAAAAAAAAAAA");
                        Toast.makeText(GameActivity.this,"Mov no realizado",Toast.LENGTH_SHORT).show();}

                    movements++;
                }
                else
                    Toast.makeText(GameActivity.this,"Nada que hacer aun", Toast.LENGTH_SHORT).show();
            }
        });
        trebol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( esperando_mov == 1){

                    Carta cartaux = baraja.get_carta_de_pila(pila_llamada);
                    int pila_aux = 3;

                    boolean aux = baraja.insertar_a_base(pila_llamada,"trebol");
                    esperando_mov = 0;

                    if (aux){
                        Toast.makeText(GameActivity.this, "MOVIDA A BASE CON EXITO", Toast.LENGTH_SHORT).show();
                        trebol.setImageResource(CARD_IMAGE[pila_aux][cartaux.getNumero()- 2]);
                        paint();
                    }

                    else{
                        Log.d("MOV INVALID","AAAAAAAAAAAAAAA");
                        Toast.makeText(GameActivity.this,"Mov no realizado",Toast.LENGTH_SHORT).show();}

                    movements++;
                }
                else
                    Toast.makeText(GameActivity.this,"Nada que hacer aun", Toast.LENGTH_SHORT).show();
            }
        });
        diamante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( esperando_mov == 1){

                    Carta cartaux = baraja.get_carta_de_pila(pila_llamada);
                    int pila_aux = 1;

                    boolean aux = baraja.insertar_a_base(pila_llamada,"diamante");
                    esperando_mov = 0;

                    if (aux){
                        Toast.makeText(GameActivity.this, "MOVIDA A BASE CON EXITO", Toast.LENGTH_SHORT).show();
                        diamante.setImageResource(CARD_IMAGE[pila_aux][cartaux.getNumero()- 2]);
                        paint();
                    }

                    else{
                        Log.d("MOV INVALID","AAAAAAAAAAAAAAA");
                        Toast.makeText(GameActivity.this,"Mov no realizado",Toast.LENGTH_SHORT).show();}

                    movements++;
                }
                else
                    Toast.makeText(GameActivity.this,"Nada que hacer aun", Toast.LENGTH_SHORT).show();
            }
        });

//        F l i p
//        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(pica, "scaleX", 1f, 0f);
//        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(pica, "scaleX", 0f, 1f);
//        oa1.setInterpolator(new DecelerateInterpolator());
//        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
//        oa1.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                pica.setImageResource(R.drawable.media_shuffle);
//                oa2.start();
//            }
//        });
//        oa1.start();


        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Shuffle", Toast.LENGTH_SHORT).show();
                baraja.repartir();
                paint();
                movements++;
            }
        });
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(int i = 0; i < 12; i++) {
                if ((v.getId() == CARD_ID[i]) && (esperando_mov == 0)) {
                    esperando_mov = 1;
                    pila_llamada = i;
                } else if ((v.getId() == CARD_ID[i]) && (esperando_mov == 1)) {
                    esperando_mov = 0;
                    baraja.insertar_a_pila(pila_llamada, i);
                    movements++;
                }

            }
            paint();

        }
    };

    public void paint(){
        for(int i = 0;i<12;i++){
            if (!baraja.pila_vacia(i)) {
                cards[i].setVisibility(View.VISIBLE);
                int pila = 0;
                Carta aux = baraja.get_carta_de_pila(i);
                String mono = aux.getPalo();
                if (mono.equals("corazon"))
                    pila = 0;
                if (mono.equals("diamante"))
                    pila = 1;
                if (mono.equals("pica"))
                    pila = 2;
                if (mono.equals("trebol"))
                    pila = 3;
                cards[i].setImageResource(CARD_IMAGE[pila][aux.getNumero() - 2]);
            }
            else
                cards[i].setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("move",movements);
        outState.putLong("time_play",aux_time);
        outState.putInt("card",cartas_correctas);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        movements = savedInstanceState.getInt("move");
        time = savedInstanceState.getLong("time_play");
        cartas_correctas = savedInstanceState.getInt("card");
        movimientos.setText(Integer.toString(movements));
        cartas.setText(Integer.toString(cartas_correctas));
    }
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            final long start = mStartTime;
            long millis = SystemClock.uptimeMillis() - start + time;
            aux_time = millis;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            tiempo.setText("" + minutes + ":"
                    + String.format("%02d", seconds));
            mHandler.postDelayed(this, 200);
        }
    };

    @Override
    protected void onPause() {
        mHandler.removeCallbacks(mUpdateTimeTask);
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }
}
