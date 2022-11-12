package com.example.mymapsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmail;
    Button mNext;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail = (EditText) findViewById(R.id.editTextEmailAddressRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }

    public void goToPasswordActivity(View v){
        dialog.setMessage("Mengecek Alamat Email");
        dialog.show();
        firebaseAuth.fetchSignInMethodsForEmail(mEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (mEmail.getText().toString().isEmpty()) {
                            Toast.makeText(RegisterActivity.this, "Email Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                        } else {
                            if (task.isSuccessful()){
                                dialog.dismiss();
                                boolean check = !task.getResult().getSignInMethods().isEmpty();

                                if (!check){
                                    Intent intent = new Intent(RegisterActivity.this, PasswordActivity.class);
                                    intent.putExtra("email", mEmail.getText().toString());
                                    startActivity(intent);
                                    finish();

                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(RegisterActivity.this, "Email Sudah Terdaftar !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
    }
}