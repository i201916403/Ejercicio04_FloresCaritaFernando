package com.cibertec.ejercicio04_florescaritafernando.api;


import com.cibertec.ejercicio04_florescaritafernando.entity.Alumno;

;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceAlumnoApi {

    @POST("alumno")
    public abstract Call<Alumno> insertaAlumno(@Body Alumno obj);

    @GET("alumno")
    public abstract  Call<List<Alumno>> listaAlumno();

}
