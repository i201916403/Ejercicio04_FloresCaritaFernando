package com.cibertec.ejercicio04_florescaritafernando;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cibertec.ejercicio04_florescaritafernando.adapter.AlumnoAdapter;
import com.cibertec.ejercicio04_florescaritafernando.api.ServiceAlumnoApi;
import com.cibertec.ejercicio04_florescaritafernando.entity.Alumno;
import com.cibertec.ejercicio04_florescaritafernando.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlumnoCrudListaActivity extends AppCompatActivity {

    List<Alumno> lstDatos = new ArrayList<Alumno>();
    AlumnoAdapter adap = null;
    ListView lstView = null;
    ServiceAlumnoApi api = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_crud_lista);

        lstView  = (ListView) findViewById(R.id.idListCrudAlumno);
        adap = new AlumnoAdapter(this,R.layout.activity_alumno_item, lstDatos);
        lstView.setAdapter(adap);

        api = ConnectionRest.getConnection().create(ServiceAlumnoApi.class);

        listar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.idMenuCrudAlumno) {
            Intent intent = new Intent(this, AlumnoCrudListaActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }


    public void listar(){
        mensaje("LOG -> listado 1");

        Call<List<Alumno>> call = api.listaAlumno();
        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                mensaje("LOG -> listado 2");
                if (response.isSuccessful()){
                    mensaje("LOG -> listado 3");
                    List<Alumno> data = response.body();
                    mensaje("LOG -> " +   "size " + data.size());
                    lstDatos.clear();
                    lstDatos.addAll(data);
                    adap.notifyDataSetChanged();
                }else{
                    mensaje("ERROR -> " +   "Error en la respuesta");
                }
            }
            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                mensaje("ERROR -> " +   "Error en la respuesta");
            }
        });
    }

}