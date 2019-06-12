package com.example.dsaproyectofront;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Field;

public interface  APIJuego {


    @POST("usuarios/login")
    Call<UsuarioTO> login(
            @Body Auth a
    );






    @POST("usuarios/crear")
    Call<UsuarioTO> crearusuario(
            @Body Auth a);





   @GET("usuarios/todos")
   Call<LinkedList<Usuario>> misusuarios();

    @GET("juego/objeto/{id}")
    Call<Objeto> dameObjeto(
            @Path("id") String id);


    @GET ("mapa/idMapa")
    Call<Mapa> dameMapa(

            @Path("id") String id);







 public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/dsaApp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}

