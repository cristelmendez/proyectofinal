package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class generarelreporte extends AppCompatActivity {
    ListView lvReporteVenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generarelreporte);
        String url= glob.url+"reporteVentas.php?iduser="+ glob.iduser;
        RequestQueue queue = Volley.newRequestQueue(this);
        lvReporteVenta=findViewById(R.id.lvReporteVenta);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    int tam=jsonArray.length();
                    ArrayList tabla;
                    tabla=new ArrayList();
                    float granTotal=0;
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String producto=jsonObject.getString("producto");
                        String cantidad=jsonObject.getString("cantidad");
                        String precioUnitario	=jsonObject.getString("precioUnitario");
                        String total=jsonObject.getString("total");
                        //agregar registro a la tabla
                        tabla.add(producto+" | Precio:$"+precioUnitario+" x $"+cantidad+" = $"+total);
                        granTotal+=Float.parseFloat(total);
                    }
                    tabla.add("Gran total: $"+granTotal);
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(generarelreporte.this,
                            android.R.layout.simple_list_item_1, tabla);
                    //LLENAMOS EL LISTVIEW CON LAS CATEGORIAS
                    lvReporteVenta.setAdapter(adapter);
                }
                catch (Exception e){
                    Toast.makeText(generarelreporte.this, "No hay datos ", Toast.LENGTH_SHORT).show();
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(generarelreporte.this, "No hay datos", Toast.LENGTH_LONG).show();

            }
        });
        queue.add(jsonObjectRequest);
    }
    //METODO PARA VOLVER AL MENU PRINCIPAL
    public void btnIrCategorias(android.view.View view){
        Intent intent=new Intent(this,finalizarpedido.class);
        startActivity(intent);
    }
}