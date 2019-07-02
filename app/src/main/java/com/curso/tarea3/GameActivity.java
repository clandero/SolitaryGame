package com.curso.tarea3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {


    private ImageView shuffle,pica,corazon,trebol,diamante;
    private ImageView[][] cards;
    private final int CARD_ID[][] = {{R.id.card_1,R.id.card_2,R.id.card_3,R.id.card_4},
            {R.id.card_5,R.id.card_6,R.id.card_7,R.id.card_8},{R.id.card_9,R.id.card_10,R.id.card_11,R.id.card_12}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        shuffle = findViewById(R.id.shuffle);
        pica = findViewById(R.id.pica_deck);
        corazon = findViewById(R.id.corazon_deck);
        trebol = findViewById(R.id.trebol_deck);
        diamante = findViewById(R.id.diamante_deck);

        cards = new ImageView[3][4];
        for (int i = 0; i < 3;i++)
            for(int j = 0; j < 4;j++){
                cards[i][j] =this.findViewById(CARD_ID[i][j]);
                cards[i][j].setImageResource(R.drawable.back_card);
            }
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
//        }); FLIP
//        oa1.start();


        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Shuffle",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
