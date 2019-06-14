package com.example.dsaproyectofront;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuActivity extends AppCompatActivity {

    private APIJuego api;
    Button jugar;
    Button ranking;
    Button web;
    Button atras;
    UsuarioTO usuarioTO;
    TextView textusuario;
    private String idUser;

//Hola soy un comentario.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        jugar = findViewById(R.id.jugar);
        ranking = findViewById(R.id.historial);
        web = findViewById(R.id.web);
        atras = findViewById(R.id.atrás);
        textusuario = findViewById(R.id.textusuario);


        api = APIJuego.retrofit.create(APIJuego.class);


        Intent intent = getIntent();
        idUser = intent.getStringExtra("id");
        textusuario.setText(idUser);

        Toast.makeText(getApplicationContext(), idUser + "hola holita", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), idUser + "hola k tal", Toast.LENGTH_SHORT).show();

     //   adapter = getIntent();
       // String idlogin = (adapter.getStringExtra("idUser"));

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id ="1";
                Call<Mapa> call = api.dameMapa(id);
                call.enqueue(new Callback<Mapa>() {
                    @Override
                    public void onResponse(Call<Mapa> call, Response<Mapa> response) {

                        Mapa m = new Mapa();
                         m = response.body();
                        Toast.makeText(getApplicationContext(), m.getMapatodo(), Toast.LENGTH_SHORT).show();
                        Intent mIntent = new Intent(MenuActivity.this, Juego.class);
                        startActivity(mIntent);

            }

                    @Override
                    public void onFailure(Call<Mapa> call, Throwable t) {


                        Toast.makeText(getApplicationContext(), "Fallo con la petición de información", Toast.LENGTH_SHORT).show();


                    }
                });






            }
        });



        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MenuActivity.this,RankingActivity.class);
                startActivity(mIntent);

            }
        });




        web.setOnClickListener(new View.OnClickListener() {
            @Override  //Cuando clias en el boyçon se te abre la pagina web
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.JUEGO.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });





        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(mIntent);

            }
        });

    }






}
