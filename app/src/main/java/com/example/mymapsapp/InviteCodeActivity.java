package com.example.mymapsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;

public class InviteCodeActivity extends AppCompatActivity {

    String name,email,password,isSharing,code, date;
    Uri imageUri;
    TextView textCode;
    ProgressDialog dialog;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);
        textCode = (TextView)findViewById(R.id.inviteCodeText);
        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        Intent intent = getIntent();

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        if (intent != null){
            name = intent.getStringExtra("name");
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("password");
            date = intent.getStringExtra("date");
            isSharing = intent.getStringExtra("isSharing");
            code = intent.getStringExtra("code");
            imageUri = intent.getParcelableExtra("imageUri");
        }
        textCode.setText(code);

    }

    public void registerUser(View v){
        dialog.setMessage("Mohon Tunggu, Akun Anda Sedan Dibuat");
        dialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //Insert Value Ke Realtime Database
                            createUser createUser = new createUser(name, email, password, code, "false", "na", "na","na");

                            firebaseUser = firebaseAuth.getCurrentUser();
                            userID = firebaseUser.getUid();

                            reference.child(userID).setValue(createUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                dialog.dismiss();
                                                Toast.makeText(InviteCodeActivity.this, "User Berhasil Terdaftar !", Toast.LENGTH_SHORT).show();
                                                finish();
                                                Intent intent = new Intent(InviteCodeActivity.this, NavigationActivity.class);
                                                startActivity(intent);
                                            } else {
                                                dialog.dismiss();
                                                Toast.makeText(InviteCodeActivity.this, "User Gagal Terdaftar !", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                    }
                });

    }
}