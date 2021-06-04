package com.cibertec.ejercicio04_florescaritafernando.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cibertec.ejercicio04_florescaritafernando.entity.Alumno;
import com.cibertec.ejercicio04_florescaritafernando.R;

import java.util.List;

public class AlumnoAdapter extends ArrayAdapter<Alumno> {

    private Context context;
    private List<Alumno> lista;

    public AlumnoAdapter(@NonNull Context context, int resource, @NonNull List<Alumno> lista) {
        super(context, resource, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_alumno_item, parent, false);
        Alumno obj = lista.get(position);

        TextView txtNombre = (TextView) row.findViewById(R.id.idItemNombreAlumno);
        txtNombre.setText("Nombres y apellidos: "+obj.getNombres()+", "+obj.getApellidos());
        return row;
    }
}
