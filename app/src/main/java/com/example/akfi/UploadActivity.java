package com.example.akfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.akfi.backend.upload;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    Button back, ok;
    EditText title, info;
    ProgressBar progressBar;
    public ArrayList<String> idKeyList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        title = findViewById(R.id.edit_title);
        info = findViewById(R.id.edit_data);
        ok = findViewById(R.id.button_upload);
        back = findViewById(R.id.button_back);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        ok.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    private void up() {

        String uploadTitle = title.getText().toString().trim();
        String uploadInfo = info.getText().toString().trim();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        Date date = new Date();
        String d = simpleDateFormat.format(date);


        if (uploadTitle.isEmpty() || uploadInfo.isEmpty()) {
            Toast.makeText(this, "Please fill up the fields", Toast.LENGTH_SHORT).show();
        } else {
            upload obj = new upload(uploadTitle, uploadInfo, d);
            firebase(obj);
        }
    }

    public void firebase(upload obj) {
        progressBar.setVisibility(View.VISIBLE);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd`MM`YYYY");
        Date date = new Date();
        String d = simpleDateFormat.format(date).trim();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference().push();

        databaseReference.setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UploadActivity.this, "success", Toast.LENGTH_SHORT).show();

                title.getText().clear();
                info.getText().clear();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == ok) {
            up();
        } else if (view == back) {
            Intent intent = new Intent(UploadActivity.this, MenuActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}