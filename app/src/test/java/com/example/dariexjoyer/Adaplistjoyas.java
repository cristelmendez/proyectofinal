package com.example.dariexjoyer;

import android.widget.ArrayAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

    public class Adaplistjoyas  extends ArrayAdapter<String> {
        private final Activity context;
        private final String[] modelos;
        private final String[] descripcion;
        private final Integer[] id_images;


        public Adaplistjoyas(Activity context, String[] modelos, String[] precios, Integer[] id_images) {
            super(context,R.layout.item_categorias,modelos);
            this.context = context;
            this.modelos = modelos;
            this.descripcion = precios;
            this.id_images = id_images;
        }



        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View itemView = inflater.inflate(R.layout.item_categorias,null);
            TextView _modelos = (TextView) itemView.findViewById(R.id.modelos);
            TextView _descripcion = (TextView) itemView.findViewById(R.id.descripcion);
            ImageView _imagen = (ImageView) itemView.findViewById(R.id.collares);
            _modelos.setText(modelos[position]);
            _descripcion.setText(descripcion[position]);
            _imagen.setImageResource(id_images[position]);
            return itemView;
        }
    }

