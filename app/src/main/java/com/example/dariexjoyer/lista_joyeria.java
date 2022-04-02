package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class lista_joyeria extends AppCompatActivity {

        ListView lvCategorias;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista_joyeria);
            //TOMAMOS EL ID DEL USUARIO
            Intent intent = getIntent();
            //LLAMAMOS EL LISTVIEW DE LA LISTA DE CATEGORIAS
            lvCategorias=(ListView)findViewById(R.id.lvCategorias);
            //LLENAMOS EL LISTVIEW CON EL ARRAY DE CATEGORIAS
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, glob.cat);
            //LLENAMOS EL LISTVIEW CON LAS CATEGORIAS
            lvCategorias.setAdapter(adapter);
            //Crear evento click para cada categoria
            lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String catSel = (String) parent.getItemAtPosition(position);
                    //LLAMAMOS UNA NUEVA ACTIVIDAD
                    Intent intent = new Intent(lista_joyeria.this, lisproductjoyer.class);
                    //ENVIAMOS LA CATEGORIA SELECCIONADA
                    intent.putExtra("catSel", catSel);
                    startActivity(intent);
                }
            });
        }
        //crear funcion para boton Compras
        public void btnVerCompras(android.view.View view){
            Intent intent = new Intent(lista_joyeria.this, generarelreporte.class);
            startActivity(intent);
        }
    }