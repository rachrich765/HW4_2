package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextZipCode2;
    Button buttonSearch, buttonGTR;
    TextView textViewBirdName, textViewPersonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        editTextZipCode2 = findViewById(R.id.editTextZipCode2);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonGTR = findViewById(R.id.buttonGTR);
        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewPersonName = findViewById(R.id.textViewPersonName);

        buttonSearch.setOnClickListener(this);
        buttonGTR.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.searchMenu) {
            Toast.makeText(this, "you are already on this page.", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.reportMenu) {
            Intent searchIntent = new Intent(this, MainActivity.class);
            startActivity(searchIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonGTR) {
            Intent searchIntent = new Intent(this, MainActivity.class);
            startActivity(searchIntent);
            Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
        } else if (v == buttonSearch) {
            String bird = textViewBirdName.getText().toString();
            String person = textViewPersonName.getText().toString();
            String temp = editTextZipCode2.getText().toString();
            int zip = Integer.parseInt(temp);

             Bird b = new Bird(bird,zip,person);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Birds");



            //String key = myRef.push().getKey();
            Toast.makeText(this, b.birdname, Toast.LENGTH_SHORT).show();
            myRef.push().setValue(b);
        }


        }
    }


