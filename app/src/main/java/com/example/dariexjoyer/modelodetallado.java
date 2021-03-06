package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class modelodetallado extends AppCompatActivity {
    //inicializar imageView
    ImageView imgProducto;
    TextView tvProducto;
    Button btnMenos, btnMas;
    float precio=0;
    int cantidad=0;
    String catSel;
    String prodSel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelodetallado);
        Intent intent = getIntent();
        prodSel = intent.getStringExtra("prodSel");
        catSel = intent.getStringExtra("catSel");
        String position = intent.getStringExtra("position");
        imgProducto = findViewById(R.id.imgProducto);
        tvProducto = findViewById(R.id.tvProducto);
        btnMas = findViewById(R.id.btnagregar);
        if (prodSel.contains("aretesoro")) {
            imgProducto.setImageResource(R.mipmap.aretesoro);
        }
        if (prodSel.contains("herraduras")) {
            imgProducto.setImageResource(R.mipmap.herraduras);
        }
        if (prodSel.contains("corazones")) {
            imgProducto.setImageResource(R.mipmap.corazones);
        }
        if (prodSel.contains("estrella")) {
            imgProducto.setImageResource(R.mipmap.estrella);
        }
        if (prodSel.contains("estrellasyluna")) {
            imgProducto.setImageResource(R.mipmap.estrellasyluna);
        }
        if (prodSel.contains("collarsirena")) {
            imgProducto.setImageResource(R.mipmap.collarsirena);
        }
        if (prodSel.contains("trespiedras")) {
            imgProducto.setImageResource(R.mipmap.trespiedras);
        }
        if (prodSel.contains("safiroderosas")) {
            imgProducto.setImageResource(R.mipmap.safiroderosas);
        }
        if (prodSel.contains("cuarsorosa")) {
            imgProducto.setImageResource(R.mipmap.cuarsorosa);
        }
        //INICIALIZAMOS LA VARIABLE cat QUE POSTERIORMENTE CONTENDRA TODOS LOS PRODUCTO DE LA CATEGORIA SELECCIONADA
        float[] articulos = {};;
        if(catSel.equals("anillo")){
            articulos=general.precioanillo;
        }
        if(catSel.equals("collar")){
            articulos=general.preciocollar;
        }
        if(catSel.equals("aretes")){
            articulos=general.precioaretes;
        }
        precio=articulos[Integer.parseInt(position)];
        tvProducto.setText(prodSel+" $"+precio);
    }
    public void agregar(View view)
    {
        cantidad++;
        float subTotal=precio*cantidad;
        Intent intent = new Intent(modelodetallado.this, generarelreporte.class);
        startActivity(intent);
    }

    public void botonProcesar(View view)
    {
        if (cantidad==0){
            Toast.makeText(this, "seleccione al menos un articulo de la joyeria ", Toast.LENGTH_SHORT).show();
        }
        else {
            String url= general.url+"insertotales.php?iduser="+ general.iduser+"&categoria="+catSel+"&producto="+prodSel+"&cantidades="+cantidad+"&preunidad="+precio+"&total="+precio*cantidad;
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                if(response.equals("0")){
                    Toast.makeText(this, "Datos no guardado", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "se guardo correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, generarelreporte.class);
                    startActivity(intent);
                }
            }, error -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            });
            queue.add(stringRequest);
        }
    }
}