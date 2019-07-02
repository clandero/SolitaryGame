package com.curso.tarea3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
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

public class GameActivity extends AppCompatActivity {

    private int movements;
    private TextView movimientos,cartas,tiempo;
    private ImageView shuffle,pica,corazon,trebol,diamante;
    private ImageView[][] cards;
    private final int CARD_ID[][] = {{R.id.card_1,R.id.card_2,R.id.card_3,R.id.card_4},
            {R.id.card_5,R.id.card_6,R.id.card_7,R.id.card_8},{R.id.card_9,R.id.card_10,R.id.card_11,R.id.card_12}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Area de contadores
        movimientos = findViewById(R.id.movimientos);
        cartas = findViewById(R.id.cartas_ubicadas);
        tiempo = findViewById(R.id.tiempo);

        //Area de decks finales
        shuffle = findViewById(R.id.shuffle);
        pica = findViewById(R.id.pica_deck);
        corazon = findViewById(R.id.corazon_deck);
        trebol = findViewById(R.id.trebol_deck);
        diamante = findViewById(R.id.diamante_deck);

//        pica.setOnDragListener(new MyDragListener());

        //Area de juego
        cards = new ImageView[3][4];
        for (int i = 0; i < 3;i++)
            for(int j = 0; j < 4;j++){
                cards[i][j] =this.findViewById(CARD_ID[i][j]);
                cards[i][j].setImageResource(R.drawable.back_card);
                cards[i][j].setOnTouchListener(new MyTouchListener());
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
    private final class MyTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                movements++;
                movimientos.setText(String.valueOf(movements));
                return true;
            } else {
                return false;
            }
        }
    }

//    class MyDragListener implements View.OnDragListener {
//        Drawable enterShape = getResources().getDrawable(
//                R.drawable.back_card);
//        Drawable normalShape = getResources().getDrawable(R.drawable.back_card);
//
//        @Override
//        public boolean onDrag(View v, DragEvent event) {
//            int action = event.getAction();
//            switch (event.getAction()) {
//                case DragEvent.ACTION_DRAG_STARTED:
//                    // do nothing
//                    break;
//                case DragEvent.ACTION_DRAG_ENTERED:
//                    v.setBackground(enterShape);
//                    break;
//                case DragEvent.ACTION_DRAG_EXITED:
//                    v.setBackground(normalShape);
//                    break;
//                case DragEvent.ACTION_DROP:
//                    // Dropped, reassign View to ViewGroup
//                    View view = (View) event.getLocalState();
//                    ViewGroup owner = (ViewGroup) view.getParent();
//                    owner.removeView(view);
//                    owner.addView(view);
//                    view.setVisibility(View.VISIBLE);
//                    break;
//                case DragEvent.ACTION_DRAG_ENDED:
//                    v.setBackground(normalShape);
//                default:
//                    break;
//            }
//            return true;
//        }
//    }
}
