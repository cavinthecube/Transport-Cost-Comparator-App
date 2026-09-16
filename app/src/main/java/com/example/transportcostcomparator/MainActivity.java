package com.example.transportcostcomparator;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {



    Button start;
    Button exit;
    Button listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);




        start = findViewById(R.id.startBtn);
        exit = findViewById(R.id.exitBtn);
        listen = findViewById(R.id.listenBtn);


        start.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Input.class);
            startActivity(intent);
        });

        listen.setOnClickListener(v -> {

            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.audio);
            mediaPlayer.start();

        });

        exit.setOnClickListener(v -> {
            finishAffinity();
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
