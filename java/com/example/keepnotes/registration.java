package com.example.keepnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {

    EditText editID, editUsername, editAddress, editEmail, editPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editID = findViewById(R.id.reg_ID);
        editUsername = findViewById(R.id.reg_Username);
        editAddress = findViewById(R.id.reg_Address);
        editEmail = findViewById(R.id.reg_Email);
        editPassword = findViewById(R.id.reg_Password);

        mAuth = FirebaseAuth.getInstance();
    }
    public void register(View view) {

        String id, name, address, email, password;

        id = editID.getText().toString().trim();
        name = editUsername.getText().toString().trim();
        address = editAddress.getText().toString().trim();
        email = editEmail.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(id, name, address, email);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){
                                        @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){
                                                Toast.makeText(registration.this, "Registration is Succesful.", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(registration.this, "Registration Failed. Please try again.", Toast.LENGTH_LONG).show();

                                            }

                                        }
                            });
                        } else{
                            Toast.makeText(registration.this, "Registration Failed. Please try again.", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

}