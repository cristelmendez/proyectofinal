package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registro extends AppCompatActivity {
    EditText nombre, correo, contrasena, edad;
    Button registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = (EditText) findViewById(R.id.edit1);
        correo = (EditText) findViewById(R.id.editcore);
        contrasena = (EditText) findViewById(R.id.edit3);
        edad = (EditText) findViewById(R.id.edit4);
        registro = (Button) findViewById(R.id.btng);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
    }
}