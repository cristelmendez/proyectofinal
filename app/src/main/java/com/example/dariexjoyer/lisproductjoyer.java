package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class lisproductjoyer extends AppCompatActivity {
    ListView articulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisproductjoyer);
        articulos=(ListView)findViewById(R.id.articulos);
        Intent intent = getIntent();
        String elegir = intent.getStringExtra("elegir");
        //INICIALIZAMOS LA VARIABLE cat QUE POSTERIORMENTE CONTENDRA TODOS LOS PRODUCTO DE LA CATEGORIA SELECCIONADA
        String[] articulos = {};;
        if(elegir.equals("anillo")){
            articulos= general.anillo;
        }
        if(elegir.equals("aretes")){
            articulos= general.aretes;
        }
        if(elegir.equals("collar")){
            articulos= general.collar;
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, articulos);


    }
}