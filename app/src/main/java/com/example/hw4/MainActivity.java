//I worked with Travis Wilkinson
package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextBirdName, editTextPersonName, editTextZipCode;
    Button buttonSubmit, buttonGTS;
    // DatabaseReference myRef;
    // FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        //  database = FirebaseDatabase.getInstance();
        // myRef = database.getReference();


        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextZipCode = findViewById(R.id.editTextZipCode);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonGTS = findViewById(R.id.buttonGTS);


        buttonSubmit.setOnClickListener(this);
        buttonGTS.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.reportMenu){
            Toast.makeText(this, "you are already on this page.", Toast.LENGTH_SHORT).show();

        } else if(item.getItemId() == R.id.searchMenu) {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        if(view == buttonGTS) {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
            Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
        }
        else if (view == buttonSubmit) {
            String bird = editTextBirdName.getText().toString();
            String person = editTextPersonName.getText().toString();
            String temp = editTextZipCode.getText().toString();
            int zip = Integer.parseInt(temp);

            Bird b = new Bird(bird,zip,person);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Birds");
            Toast.makeText(this, b.birdname, Toast.LENGTH_SHORT).show();
            myRef.push().setValue(b);
        }
    }
}