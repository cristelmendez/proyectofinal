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

            Intent intent = getIntent();

            lvCategorias=(ListView)findViewById(R.id.lvCategorias);

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, general.cat);

            lvCategorias.setAdapter(adapter);

            lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String catSel = (String) parent.getItemAtPosition(position);

                    Intent intent = new Intent(lista_joyeria.this, lisproductjoyer.class);

                    intent.putExtra("catSel", catSel);
                    startActivity(intent);
                }
            });
        }

    }