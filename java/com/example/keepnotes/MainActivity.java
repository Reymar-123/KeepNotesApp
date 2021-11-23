package com.example.keepnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.userEmail);
        editPassword = findViewById(R.id.userPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    public void goToReg(View view) {

        Intent intent = new Intent (MainActivity.this, registration.class);
        startActivity(intent);
    }

    public void login(View view) {

        String email, password;

        email = editEmail.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Intent intent = new Intent(getApplicationContext(), userprofile.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}