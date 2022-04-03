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
    TextView tvProducto,tvTotalDetalle;
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
        btnMenos = findViewById(R.id.btnMenos);
        btnMas = findViewById(R.id.btnMas);
        tvTotalDetalle = findViewById(R.id.tvTotalDetalle);
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
        float[] cat = {};;
        if(catSel.equals("anillo")){
            cat=glob.precioanillo;
        }
        if(catSel.equals("collar")){
            cat=glob.preciocollar;
        }
        if(catSel.equals("aretes")){
            cat=glob.precioaretes;
        }
        precio=cat[Integer.parseInt(position)];
        tvProducto.setText(prodSel+" $"+precio);
    }
    public void botonMas(View view)
    {
        cantidad++;
        float subTotal=precio*cantidad;
        tvTotalDetalle.setText(cantidad+" x $"+precio+": $"+String.valueOf(subTotal));
    }
    public void botonMenos(View view)
    {
        cantidad--;
        if(cantidad<=0){
            cantidad=0;
            tvTotalDetalle.setText("Sub total: ");
            Toast.makeText(this, "Debe de haber por lo menos un producto", Toast.LENGTH_SHORT).show();
        }else{
            float subTotal=precio*cantidad;
            tvTotalDetalle.setText(cantidad+" x $"+precio+": $"+String.valueOf(subTotal));
        }
    }
    public void botonCancelar(View view)
    {
        //abre la actividad ListaProductos
        Intent intent = new Intent(modelodetallado.this, lisproductjoyer.class);
        startActivity(intent);
    }
    public void botonProcesar(View view)
    {
        if (cantidad==0){
            Toast.makeText(this, "Debe de haber por lo menos un producto", Toast.LENGTH_SHORT).show();
        }
        else {
            String url= glob.url+"insertotales.php?iduser="+ glob.iduser+"&categoria="+catSel+"&producto="+prodSel+"&cantidades="+cantidad+"&preunidad="+precio+"&total="+precio*cantidad;
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                if(response.equals("0")){
                    Toast.makeText(this, "Datos no guardado", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
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