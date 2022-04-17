package com.shiryaev.pavel.bsbo_08_19.practice7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = findViewById(R.id.textViewInfo);
        firebaseAuth = FirebaseAuth.getInstance();
        Button logOut = findViewById(R.id.buttonExit);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                SecondActivity.this.startActivity(intent);
                SecondActivity.this.finish();
            }
        });
        tv.setText("ID: " + firebaseAuth.getCurrentUser().getUid()
                + "\nEmail: " + firebaseAuth.getCurrentUser().getEmail());
    }
}