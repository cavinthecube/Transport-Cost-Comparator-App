package com.example.transportcostcomparator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;




public class Results extends AppCompatActivity {


    DatabaseHelper d;
    Button calc;
    Button exit;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);

        calc = findViewById(R.id.calcAgnBtn);
        exit = findViewById(R.id.exitBtn2);
        results = findViewById(R.id.resultsTV);

        //creates database helper
        d = new DatabaseHelper(this);

        // gets saved transport from database
        String result = d.displayDb();
        // Displays results to text view
        results.setText(result);

        // goes back to the input page
        calc.setOnClickListener(v -> {

            Intent intent = new Intent(Results.this, Input.class);
            startActivity(intent);
        });

        // closes application
        exit.setOnClickListener(v -> {
            finishAffinity();
        });

    }

    // Adds action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    // Allows action bar options to be clickable
    // only made home option work
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(Results.this, MainActivity.class);
            startActivity(intent);
            return true;

        }
        return true;
    }
}