package com.example.canvas_prueba;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText txtMail;
    private EditText txtPass;
    private FirebaseAuth fireBaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fireBaseAuth = FirebaseAuth.getInstance();
        txtMail = (EditText)findViewById(R.id.txtMail);
        txtPass = (EditText) findViewById(R.id.txtPassword);
        progressDialog = new ProgressDialog(this);
    }

    public void Registrar(View view){

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

        progressDialog.setMessage("Realizando registro en liena...");
        progressDialog.show();

        fireBaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Se a registrado el usuario", Toast.LENGTH_SHORT).show();
                            Intent ok = new Intent(MainActivity.this, Tareas.class);
                            startActivity(ok);

                        } else {
                            Toast.makeText(MainActivity.this, "Registro invalido", Toast.LENGTH_SHORT).show();
                        }

                        progressDialog.dismiss();
                    }
                });
    }

    public void Login(View v){
        Intent ok = new Intent(MainActivity.this, Login.class);
        startActivity(ok);

    }
}


