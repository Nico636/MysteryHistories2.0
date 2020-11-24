package com.damani.mysteryhistories20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.des_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.des_abajo);

        TextView tx = findViewById(R.id.textView);
        ImageView logo = findViewById(R.id.imageView);

        tx.setAnimation(animacion2);
        logo.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this
                        , Inicio.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}