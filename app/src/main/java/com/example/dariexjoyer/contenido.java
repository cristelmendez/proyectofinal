package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class contenido extends AppCompatActivity {
    ListView balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);
        String url= general.url+"costostotales.php?iduser="+ general.iduser;
        RequestQueue queue = Volley.newRequestQueue(this);
        balance=findViewById(R.id.lvReporteVenta);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    int tam=jsonArray.length();
                    ArrayList lista;
                    lista=new ArrayList();
                    float Total=0;
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String producto=jsonObject.getString("producto");
                        String cantidad=jsonObject.getString("cantidad");
                        String preunidad=jsonObject.getString("preunidad");
                        String total=jsonObject.getString("total");

                        lista.add(producto+" | Precio:$"+preunidad+" x $"+cantidad+" = $"+total);
                        Total+=Float.parseFloat(total);
                    }
                    lista.add("Gran total: $"+Total);
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(contenido.this,
                            android.R.layout.simple_list_item_1, lista);

                    balance.setAdapter(adapter);
                }
                catch (Exception e){
                    Toast.makeText(contenido.this, "No registro datos", Toast.LENGTH_SHORT).show();
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contenido.this, "No registro datos", Toast.LENGTH_LONG).show();

            }
        });
        queue.add(jsonObjectRequest);
    }


}