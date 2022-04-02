package com.example.dariexjoyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
TextView  txnr;
EditText txtcorreo =null;
EditText password =null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txnr = (TextView) findViewById(R.id.txnr);
        txtcorreo = (EditText) findViewById(R.id.correo);
        password = (EditText) findViewById(R.id.password);


        txnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Registro.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }
    //click en el boton login
    public void login(View view){
        String correo = txtcorreo.getText().toString();
        String url= glob.url+"usuarcreado.php?correo="+txtcorreo.getText()+"&pass="+password.getText();
        if(correo.equals("")  || !validatecorreo(correo)){
            //mostrar mensaje de error
            Toast.makeText(this, "El correo es incorrecto", Toast.LENGTH_SHORT).show();
        }
        else{
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                if(response.equals("0")){
                    Toast.makeText(this, "Usuario NO existe", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Usuario existe", Toast.LENGTH_SHORT).show();
                    glob.iduser = response;
                    Intent intent = new Intent(this, lista_joyeria.class);
                    startActivity(intent);
                }
            }, error -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            });
            queue.add(stringRequest);
        }
    }

    public boolean validatecorreo(String email) {
        return email.contains("@");
    }
}


