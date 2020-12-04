package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

public class Principal extends AppCompatActivity implements View.OnClickListener {
Button Btn_salir;
TextView nombre;
Usuariodao dao;
int id=0;
Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        nombre =(TextView) findViewById(R.id.P_nombre);
        Btn_salir = (Button)findViewById(R.id.P_btn_salir);
        Btn_salir.setOnClickListener(this);
        dao = new Usuariodao(this);
        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        u = dao.getUsuarioById(id);
        nombre.setText(u.getNombre());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.P_btn_salir:
                Intent i = new Intent(Principal.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}