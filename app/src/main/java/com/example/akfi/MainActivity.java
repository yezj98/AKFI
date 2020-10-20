package com.example.akfi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button buttonLogin, buttonRegister;
    String stringEmail, stringPassword;
    ProgressBar progressBar;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        buttonLogin = findViewById(R.id.button_login);
        buttonRegister = findViewById(R.id.button_register);
        progressBar = findViewById(R.id.progressBar);

        mFirebaseAuth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.INVISIBLE);

//        buttonLogin.setOnClickListener(this);
    }



    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }


//    @Override
////    public void onClick(View view) {
////        if (view == buttonLogin) {
////            stringEmail = editEmail.getText().toString().trim();
////            stringPassword = editPassword.getText().toString().trim();
////
////            progressBar.setVisibility(View.VISIBLE);
////
////            if (stringEmail.isEmpty() && stringPassword.isEmpty()){
////                editPassword.setError("Email is required");
////                editEmail.setError("Password is required");
////                Toast.makeText(MainActivity.this, "Please fill up the information", Toast.LENGTH_SHORT).show();
////            }
////            else if (TextUtils.isEmpty(stringEmail)){
////                editEmail.setError("Email is required");
////            }
////            else if (TextUtils.isEmpty(stringPassword)){
////                editPassword.setError("Password is required");
////            }
////
////            else if (!stringEmail.isEmpty() && !stringPassword.isEmpty()) {
////                if (isEmailValid(stringEmail)){
////                    Log.d("username ","" + stringEmail);
////                    Log.d("userpass ","" + stringPassword);
////                    mFirebaseAuth.signInWithEmailAndPassword(stringEmail,stringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
////                        @Override
////                        public void onComplete(@NonNull Task<AuthResult> task) {
////                            if (task.isSuccessful()){
////                                Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
////                                startActivity(new Intent(MainActivity.this, MenuActivity.class));
////                                finish();
////                            }
////                            else {
////                                editEmail.setError("");
////                                editPassword.setError("Password length must longer than 8");
////                            }
////                        }
////                    });
////                }
////                else {
////                    editEmail.setError("Invalid email format");
////                }
////            }
////
////        }
////    });


}