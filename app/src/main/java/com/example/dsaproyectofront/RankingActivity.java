package com.example.dsaproyectofront;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RankingActivity extends AppCompatActivity {


    private APIJuego api;
    TextView nombre;
    TextView  puntuacion;
    public List<UsuarioTO> usuarios;
    //Button atras;

    public Recycler recycler;
    public RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recycler = new Recycler(RankingActivity.this);
        recyclerView.setAdapter(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        nombre = findViewById(R.id.nombre);
        puntuacion = findViewById(R.id.puntuacion);
        //atras = findViewById(R.id.button3);


        api = APIJuego.retrofit.create(APIJuego.class);

        getData();


        //   atras.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //    public void onClick(View v) {

        //       Intent mIntent = new Intent(RankingActivity.this,MenuActivity.class);
        //       startActivity(mIntent);

        //    }
    }
    private void getData(){

        Call<List<UsuarioTO>> call = api.misusuarios();
        call.enqueue(new Callback<List<UsuarioTO>>() {
            @Override
            public void onResponse(Call<List<UsuarioTO>> call, Response<List<UsuarioTO>> response) {
                if(response.isSuccessful()){
                     usuarios = response.body();
                    recycler.rellenarLista(usuarios);


                }else{

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RankingActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(response.message())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> finish());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                }

            }

            @Override
            public void onFailure(Call<List<UsuarioTO>> call, Throwable t) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RankingActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> finish());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });


    }
}