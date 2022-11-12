package com.example.mymapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    String email;
    EditText mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        Intent intent = getIntent();
        mPassword = (EditText) findViewById(R.id.editTextPasswordRegister);
        if (intent != null){
            email = intent.getStringExtra("email");

        }
    }

    public void goToNamePicActivity(View v){
        if (mPassword.getText().toString().length() > 6){
            Intent intent = new Intent(PasswordActivity.this, NameActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("password", mPassword.getText().toString());
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(this, "Password Harus Lebih Dari 6 Karakter !", Toast.LENGTH_SHORT).show();
        }
    }
}