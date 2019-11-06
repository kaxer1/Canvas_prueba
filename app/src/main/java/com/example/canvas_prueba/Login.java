package com.example.canvas_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText txtMail;
    private EditText txtPass;
    private FirebaseAuth fireBaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fireBaseAuth = FirebaseAuth.getInstance();
        txtMail = (EditText)findViewById(R.id.txtMail);
        txtPass = (EditText) findViewById(R.id.txtPassword);
        progressDialog = new ProgressDialog(this);
    }

    public void Ingresar(View view){

        String email = txtMail.getText().toString().trim();
        String password = txtPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Se debe ingresar el email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        fireBaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Se a registrado el usuario", Toast.LENGTH_SHORT).show();
                            Intent ok = new Intent(Login.this, Tareas.class);
                            startActivity(ok);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

}
