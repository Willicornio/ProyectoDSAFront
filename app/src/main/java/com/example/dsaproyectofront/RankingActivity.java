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
    public List<Usuario> data;
    Button atras;

    public Recycler recycler;
    public RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recycler = new Recycler(this);
        recyclerView.setAdapter(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        nombre = findViewById(R.id.nombre);
        puntuacion = findViewById(R.id.puntuacion);
        atras = findViewById(R.id.atras2);




        api = APIJuego.retrofit.create(APIJuego.class);

        getData();



        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(RankingActivity.this,MenuActivity.class);
                startActivity(mIntent);

            }
        });

    }
    private void getData(){

        Call<LinkedList<Usuario>> misusuarios = api.misusuarios();
        misusuarios.enqueue(new Callback<LinkedList<Usuario>>() {
            @Override
            public void onResponse(Call<LinkedList<Usuario>> call, Response<LinkedList<Usuario>> response) {
                if(response.isSuccessful()){
                    LinkedList<Usuario> usuarios = response.body();
                    recycler.rellenarLista(usuarios);
                    progressDialog.hide();

                }else{
                    progressDialog.hide();
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
            public void onFailure(Call<LinkedList<Usuario>> call, Throwable t) {

                progressDialog.hide();
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