package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class finalizarpedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizarpedido);

    }
    //click en el boton Regresar
    public void regresar(android.view.View view){
        Intent intent = new Intent(this, lista_joyeria.class);
        startActivity(intent);
    }
}