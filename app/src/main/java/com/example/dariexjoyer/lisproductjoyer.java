package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class lisproductjoyer extends AppCompatActivity {
    ListView lvProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisproductjoyer);
        lvProductos=(ListView)findViewById(R.id.lvProductos);
        Intent intent = getIntent();
        String catSel = intent.getStringExtra("catSel");
        //INICIALIZAMOS LA VARIABLE cat QUE POSTERIORMENTE CONTENDRA TODOS LOS PRODUCTO DE LA CATEGORIA SELECCIONADA
        String[] cat = {};;
        if(catSel.equals("anillo")){
            cat= glob.anillo;
        }
        if(catSel.equals("aretes")){
            cat= glob.aretes;
        }
        if(catSel.equals("collar")){
            cat= glob.collar;
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, cat);

        lvProductos.setAdapter(adapter);

        lvProductos.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String prodSel = (String) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), modelodetallado.class);
                intent.putExtra("catSel", catSel);
                intent.putExtra("prodSel", prodSel);
                intent.putExtra("position", String.valueOf(position));
                startActivity(intent);
            }
        });
    }
    //crear metododo para ir a ReporteVenta
    public void irReporteVenta(android.view.View view) {
        Intent intent = new Intent(this, generarelreporte.class);
        startActivity(intent);
    }
}