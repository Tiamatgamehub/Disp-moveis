package com.example.mrciobotelho.bancodados;

import android.app.NotificationManager;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TelaLogin extends AppCompatActivity {
    EditText mail;
    EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        mail = (EditText)findViewById(R.id.user);
        senha = (EditText)findViewById(R.id.senha);
    }

    public void logar (View view){
        String dados = mail.getText().toString().trim();
        String password = senha.getText().toString().trim();
        DataBase db = new DataBase(this);
        String compare = "SELECT * FROM Usuarios WHERE email='"+dados+"'";
        String [][] resultado = db.doQuery(compare);
        if (resultado.length>0){
            if(password.equals(resultado[0][3])){
                Toast.makeText(this, "Usuario logado", Toast.LENGTH_LONG).show();
            }else{
                Log.d("MB", "Senha da DB: " + resultado[0][3] + " | Senha informada: " + password);
                Toast.makeText(this, "Senha incorreta", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Usuario não existe", Toast.LENGTH_LONG).show();
        }
    }

    public void notify (View v){
        NotificationCompat.Builder not = new NotificationCompat.Builder(this);
        not.setContentText("Notificação ADO 6");
        not.setContentTitle("ADO 6");
        not.setSmallIcon(R.drawable.adoo);
        NotificationManager ger = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        ger.notify(1, not.build());
    }

    public void cadastrese (View view){
        Intent troca = new Intent(this, Cadastro.class);
        startActivity(troca);
    }





}
