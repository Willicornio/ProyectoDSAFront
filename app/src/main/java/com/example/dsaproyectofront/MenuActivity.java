package com.example.dsaproyectofront;

import android.app.AlertDialog;
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

//import com.dsa.museo.UnityPlayerActivity;

import com.dsa.museo.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuActivity extends AppCompatActivity {

    private Logger log = Logger.getLogger(MenuActivity.class.getName());
    private APIJuego api = APIJuego.retrofit.create(APIJuego.class);
    Button jugar;
    Button ranking;
    Button web;
    Button atras;
    Button borrar;
    UsuarioTO usuarioTO;
    TextView textusuario;
    private String idUser;
    public static String idUserUnity;
    public static List<Mapa> mapas = new ArrayList<>();
    public static List<Inventario> inventario = new ArrayList<>();
    public static List<Objeto> escudos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        jugar = findViewById(R.id.jugar);
        ranking = findViewById(R.id.historial);
        web = findViewById(R.id.web);
        atras = findViewById(R.id.atrás);
        textusuario = findViewById(R.id.textusuario);
        borrar = findViewById(R.id.borrar);


        Intent intent = getIntent();
        idUser = intent.getStringExtra("id");
        textusuario.setText(idUser);



        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MenuActivity.this,RankingActivity.class);
                startActivity(mIntent);

            }
        });


        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MenuActivity.this,BorrarActivity.class);
                mIntent.putExtra("id",idUser);
                startActivity(mIntent);

            }
        });



        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://10.0.2.2:8080/login.html");
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

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getMapas();
                getInventario();
                getEscudos();
                getUsuario();

                Intent mIntent = new Intent(MenuActivity.this, UnityPlayerActivity.class);
                startActivity(mIntent);

            }
        });

    }

    public void getMapas() {
        Call<List<Mapa>> call = api.dameMapas();
        call.enqueue(new Callback<List<Mapa>>() {
            @Override
            public void onResponse(Call<List<Mapa>> call, Response<List<Mapa>> response) {
                if (response.isSuccessful()) {
                    MenuActivity.mapas.addAll(response.body());
                    log.info(MenuActivity.mapas.get(0).toString());
                }
            }

            @Override
            public void onFailure(Call<List<Mapa>> call, Throwable t) {
                log.info("OnFailure obtener mapas");

            }
        });


    }

    public void getUsuario() {
        Call<Usuario> call = api.dameUsuario(idUser);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    idUserUnity = response.body().getId();
                    log.info("hecho");

                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                log.info("OnFailure obtener iduser");

            }
        });
    }
    public void getInventario() {
        Call<List<Inventario>> call = api.dameInventarioByID(idUser);
        call.enqueue(new Callback<List<Inventario>>() {
            @Override
            public void onResponse(Call<List<Inventario>> call, Response<List<Inventario>> response) {
                if (response.isSuccessful()) {
                    MenuActivity.inventario.addAll(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Inventario>> call, Throwable t) {
                log.info("OnFailure Obtener inventario");

            }
        });


    }

    public void getEscudos() {
        Call<List<Objeto>> call = api.dameObjetos();
        call.enqueue(new Callback<List<Objeto>>() {
            @Override
            public void onResponse(Call<List<Objeto>> call, Response<List<Objeto>> response) {
                if (response.isSuccessful()) {
                    MenuActivity.escudos.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Objeto>> call, Throwable t) {
                log.info("OnFailure Obtener escudos");

            }
        });


    }

    public int getVida() {

        int vida = 100;

        for(Inventario i: inventario){
            if(i.getActivado().equals("true")){

                log.info(i.getIdObjeto() + " esta activado");

                for(Objeto o : escudos){
                    if (o.getId().equals(i.getIdObjeto())){
                        log.info(" sumamos "+o.getVida()+" puntos");

                        vida = vida + o.getVida();
                    }
                }
            }
        }
        return vida;

    }



    public  void postPuntuacion(int puntuacion, String idUserUnity) {
        Call<UsuarioTO> call = api.modificardinero(idUserUnity,puntuacion);
        call.enqueue(new Callback<UsuarioTO>() {
            @Override
            public void onResponse(Call<UsuarioTO> call, Response<UsuarioTO> response) {
                if (response.isSuccessful()) {
                    log.info("Puntuación modificada correctamente");
                }
            }

            @Override
            public void onFailure(Call<UsuarioTO> call, Throwable t) {
                log.info("OnFailure modificar puntuación");

            }
        });


    }

    public static String dameMapa1(){

        return mapas.get(0).getMapa();

    }

    public static String dameMapa2(){

        return mapas.get(1).getMapa();

    }

    public static String dameMapa3(){

        return mapas.get(2).getMapa();

    }


    public int dameVida(){

        return getVida();

    }

    public String dameIdUser(){
        return idUserUnity;
    }

    public void reciboPuntuacion(int puntos, String idUserUnity){

        mapas.clear();
        escudos.clear();
        inventario.clear();
        idUser = idUserUnity;
        postPuntuacion(puntos, idUserUnity);


    }







}

