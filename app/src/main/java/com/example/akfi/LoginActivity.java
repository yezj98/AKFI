package com.example.akfi;

import androidx.annotation.NonNull;
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

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button buttonLogin;
    ProgressBar progressBar;
    String stringeditEmail, stringeditPassword;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        buttonLogin = findViewById(R.id.button_login);
        progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringeditEmail = editEmail.getText().toString().trim();
                stringeditPassword = editPassword.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                if (stringeditEmail.isEmpty() && stringeditPassword.isEmpty()){
                    editPassword.setError("Email is required");
                    editEmail.setError("Password is required");
                    Toast.makeText(LoginActivity.this, "Please fill up the information", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(stringeditEmail)){
                    editEmail.setError("Email is required");
                }
                else if (TextUtils.isEmpty(stringeditPassword)){
                    editPassword.setError("Password is required");
                }

                else if (!stringeditEmail.isEmpty() && !stringeditPassword.isEmpty()) {
                    if (iseditEmailValid(stringeditEmail)){
                        Log.d("username ","" + stringeditEmail);
                        Log.d("userpass ","" + stringeditPassword);

                        firebaseAuth.signInWithEmailAndPassword(stringeditEmail,stringeditPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, UploadActivity.class));
                                    finish();
                                }
                                else {
                                    editEmail.setError("Incorrect email");
                                    editPassword.setError("Incorrect password");
                                }
                            }
                        });
                    }
                    else {
                        editEmail.setError("Invalid email format");
                    }
                }

            }
        });
    }

    public static boolean iseditEmailValid(String editEmail) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(editEmail);
        return matcher.matches();

    }
}