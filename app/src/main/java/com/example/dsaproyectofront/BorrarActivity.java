package com.example.dsaproyectofront;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrarActivity extends AppCompatActivity {

    private APIJuego api;
    TextView texto;
    Button si;
    Button no;
    String idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        Intent intent = getIntent();
        idUser = intent.getStringExtra("id");
        texto=findViewById(R.id.preguntaborrar);
        si = findViewById(R.id.si);
        no = findViewById(R.id.no);

        api = APIJuego.retrofit.create(APIJuego.class);



        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Call<Void> usercall = api.borrar(idUser);
                usercall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> usercall, Response<Void> response) {

                        switch (response.code()) {
                            case 201:
                                Toast.makeText(getApplicationContext(), "Esperamos que vuelvas pronto :(", Toast.LENGTH_SHORT).show();
                                Intent mIntent = new Intent(BorrarActivity.this, MainActivity.class);
                                startActivity(mIntent);

                            break;

                            case 404:
                                Toast.makeText(getApplicationContext(), "No se ha podido borrar", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }




                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Fallo con la petición de información", Toast.LENGTH_SHORT).show();


                    }






            });
            }

        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(BorrarActivity.this,MenuActivity.class);
                mIntent.putExtra("id", idUser);
                startActivity(mIntent);

            }
        });
}

}
