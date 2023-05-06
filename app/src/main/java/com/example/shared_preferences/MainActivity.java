package com.example.shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtEmail,edtPass;
    TextView textRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.Login);
        edtEmail = findViewById(R.id.username);
        edtPass=findViewById(R.id.password);
        textRegister = findViewById(R.id.textRegister);
        final AlertDialog.Builder pDialog=new AlertDialog.Builder(this);

        SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
        String emailsp=prefs.getString("email","");
        String passwordsp=prefs.getString("password","");
        if(emailsp.equals("") && passwordsp.equals("")){
            pDialog.setTitle("Bienvenue");
            pDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });

        }else{
            Intent toHomeAcitivty=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(toHomeAcitivty);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtEmail.getText().toString().equals("iheb") && edtPass.getText().toString().equals("123")){
                    SharedPreferences sharedPref=getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPref.edit();
                    editor.putString("email",edtEmail.getText().toString());
                    editor.putString("password",edtPass.getText().toString());
                    editor.commit();
                    pDialog.setTitle("Connexion avec succés");
                    pDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            Intent toHomeActivity= new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(toHomeActivity);
                            finish();
                        }
                    });
                    pDialog.show();
                }else{
                //    Toast.makeText(this, "Vérifier vos paraméttres d'acces",Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Vérifier vos paraméttres d'acces", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}