package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity implements View.OnClickListener {
EditText us,pass,nom,email;
Button reg,cant;
Usuariodao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        us =(EditText)findViewById(R.id.R_user);
        pass =(EditText)findViewById(R.id.R_pass);
        nom =(EditText)findViewById(R.id.R_nom);
        email =(EditText)findViewById(R.id.R_email);
        reg=(Button)findViewById(R.id.R_reg);
        cant=(Button)findViewById(R.id.R_cancelar);

        reg.setOnClickListener(this);
        cant.setOnClickListener(this);
        dao = new Usuariodao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.R_reg:
                Usuario u= new Usuario();
                u.setUsuario(us.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setPassword(pass.getText().toString());
                u.setEmail(email.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(this,"Error: campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(Registrar.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.R_cancelar:
                Intent i = new Intent(Registrar.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}