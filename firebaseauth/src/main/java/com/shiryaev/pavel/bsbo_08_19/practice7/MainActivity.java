package com.shiryaev.pavel.bsbo_08_19.practice7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText loginEditText, passwordEditText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        Button logIn = findViewById(R.id.buttonLogIn);
        Button registration = findViewById(R.id.buttonRegistration);
        loginEditText = findViewById(R.id.login);
        passwordEditText = findViewById(R.id.password);

        firebaseAuth.signOut();

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(loginEditText.getText().toString().isEmpty()
                        || passwordEditText.getText().toString().isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(loginEditText.getText().toString(),
                            passwordEditText.getText().toString())
                            .addOnSuccessListener((AuthResult task) -> {
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(intent);
                                finish();
                            });
                } else {
                    Toast.makeText(MainActivity.this, "Поля пустые", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(loginEditText.getText().toString().isEmpty()
                        || passwordEditText.getText().toString().isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(loginEditText.getText().toString(),
                            passwordEditText.getText().toString()).addOnSuccessListener((AuthResult task) -> {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        finish();
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}