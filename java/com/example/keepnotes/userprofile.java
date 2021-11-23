package com.example.keepnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userprofile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbReference;

    String userID;
    TextView txtDisplay1, txtDisplay2, txtDisplay3, txtDisplay4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        dbReference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        txtDisplay1 = findViewById(R.id.user_ID);
        txtDisplay2 = findViewById(R.id.user_Username);
        txtDisplay3 = findViewById(R.id.user_Address);
        txtDisplay4 = findViewById(R.id.user_Email);

        dbReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User user = snapshot.getValue(User.class);

                if(user != null){
                    String id;
                    id = user.id;

                    txtDisplay1.setText("ID Number: "+id);
                }

                if(user != null){
                    String name;
                    name = user.name;

                    txtDisplay2.setText("Name: "+name);
                }

                if(user != null){
                    String address;
                    address = user.address;

                    txtDisplay3.setText("Address: "+address);
                }

                if(user != null){
                    String email;
                    email = user.email;

                    txtDisplay4.setText("Email: "+email);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(userprofile.this, "An error occurred!", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void logout(View view) {

        mAuth.signOut();

        Intent intent = new Intent(userprofile.this, MainActivity.class);
        startActivity(intent);



    }
}