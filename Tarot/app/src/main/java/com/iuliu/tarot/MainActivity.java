package com.iuliu.tarot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener,View.OnClickListener {

    ImageView one, two, three;
    List<String> cards = new ArrayList<>();
    Button btnNewGame;

    boolean oneBackShow = true, twoBackShow = true, threeBackShow = true;

    Animation toMiddle, fromMiddle;

    int flagCard = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (ImageView) findViewById(R.id.one);
        two = (ImageView) findViewById(R.id.two);
        three = (ImageView) findViewById(R.id.three);

        btnNewGame = (Button) findViewById(R.id.btnNew);
        toMiddle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_middle);
        fromMiddle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_middle);

        toMiddle.setAnimationListener(this);
        fromMiddle.setAnimationListener(this);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        btnNewGame.setOnClickListener(this);

        setUp();

    }

    private void setUp() {
        cards.clear();
        cards.add("a");
        cards.add("k");
        cards.add("p");

        Collections.shuffle(cards);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (flagCard == 0) {
            if (animation == toMiddle) {
                if (oneBackShow)
                    showCard(oneBackShow, ((ImageView) findViewById(R.id.one)), flagCard);
                else
                    ((ImageView) findViewById(R.id.one)).setImageResource(R.drawable.z00);

                ((ImageView) findViewById(R.id.one)).clearAnimation();
                ((ImageView) findViewById(R.id.one)).setAnimation(fromMiddle);
                ((ImageView) findViewById(R.id.one)).startAnimation(fromMiddle);
            } else
                oneBackShow = !oneBackShow;
        } else if (flagCard == 1) {
            if (animation == toMiddle) {
                if (twoBackShow)
                    showCard(twoBackShow, ((ImageView) findViewById(R.id.two)), flagCard);
                else
                    ((ImageView) findViewById(R.id.two)).setImageResource(R.drawable.z00);

                ((ImageView) findViewById(R.id.two)).clearAnimation();
                ((ImageView) findViewById(R.id.two)).setAnimation(fromMiddle);
                ((ImageView) findViewById(R.id.two)).startAnimation(fromMiddle);
            } else
                twoBackShow = !twoBackShow;
        } else if (flagCard == 2) {
            if (animation == toMiddle) {
                if (threeBackShow)
                    showCard(threeBackShow, ((ImageView) findViewById(R.id.three)), flagCard);
                else
                    ((ImageView) findViewById(R.id.three)).setImageResource(R.drawable.z00);

                ((ImageView) findViewById(R.id.three)).clearAnimation();
                ((ImageView) findViewById(R.id.three)).setAnimation(fromMiddle);
                ((ImageView) findViewById(R.id.three)).startAnimation(fromMiddle);
            } else
                threeBackShow = !threeBackShow;
        }
    }

    private void showCard(boolean isBackShow, ImageView imgView, int index) {
        if (isBackShow) {
            if (index == 0) {


                if (cards.get(0).equals("a"))
                    imgView.setImageResource(R.drawable.a01);
                else if (cards.get(0).equals("k"))
                    imgView.setImageResource(R.drawable.a02);
                else if (cards.get(0).equals("p"))
                    imgView.setImageResource(R.drawable.a03);

            } else if (index == 1) {


                if (cards.get(1).equals("a"))
                    imgView.setImageResource(R.drawable.a01);
                else if (cards.get(1).equals("k"))
                    imgView.setImageResource(R.drawable.a02);
                else if (cards.get(1).equals("p"))
                    imgView.setImageResource(R.drawable.a03);

            } else if (index == 2) {


                if (cards.get(2).equals("a"))
                    imgView.setImageResource(R.drawable.a01);
                else if (cards.get(2).equals("k"))
                    imgView.setImageResource(R.drawable.a02);
                else if (cards.get(2).equals("p"))
                    imgView.setImageResource(R.drawable.a03);

            }
        }

    }


    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnNew)
        {
            newGame();
        }
        else {
            view.clearAnimation();
            view.setAnimation(toMiddle);
            view.startAnimation(toMiddle);

            if (view.getId() == R.id.one)
                flagCard = 0;
            else if (view.getId() == R.id.two)
                flagCard = 1;
            else if (view.getId() == R.id.three)
                flagCard = 2;
        }

    }

    private void newGame() {
        Collections.shuffle(cards);

        Animation anim_one = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_one);
        Animation anim_two = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_two);
        Animation anim_three = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_three);

        one.startAnimation(anim_one);
        two.startAnimation(anim_two);
        three.startAnimation(anim_three);

        one.setImageResource(R.drawable.z00);
        two.setImageResource(R.drawable.z00);
        three.setImageResource(R.drawable.z00);

        oneBackShow=twoBackShow=threeBackShow=true;

    }
}
