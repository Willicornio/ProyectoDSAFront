package com.example.dsaproyectofront;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.support.v7.widget.RecyclerView;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {




    public Recycler(Context context) {
        this.lista = new ArrayList<>();
    }

    private List<UsuarioTO> lista;

    public void rellenarLista(List<UsuarioTO> todosusuarios){
        lista.addAll(todosusuarios);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        UsuarioTO usuario = lista.get(i);

        viewHolder.nombre.setText(usuario.getNombre());
        viewHolder.puntuacion.setText(String.valueOf(usuario.getPuntuacionTotal()));





    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout constraintLayout;
        private TextView nombre;
        private TextView puntuacion;


        public ViewHolder(View v){
            super(v);
            constraintLayout = v.findViewById(R.id.constraintLayout);
            nombre = v.findViewById(R.id.rankingnombre);
            puntuacion = v.findViewById(R.id.rankingpuntuacion);


        }


    }
}