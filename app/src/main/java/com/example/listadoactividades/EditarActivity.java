package com.example.listadoactividades;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listadoactividades.db.DbActividades;
import com.example.listadoactividades.entidades.Actividades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity  extends AppCompatActivity {

    EditText txtNombre, txtJornada, txtPrioridad;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    boolean correcto = false;

    Actividades actividad;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtJornada = findViewById(R.id.txtJornada);
        txtPrioridad = findViewById(R.id.txtPrioridad);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if(savedInstanceState ==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbActividades dbActividades = new DbActividades(EditarActivity.this);
        actividad = dbActividades.verActividades(id);

        if(actividad != null){
            txtNombre.setText(actividad.getNombre());
            txtJornada.setText(actividad.getJornada());
            txtPrioridad.setText(actividad.getPrioridad());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("")&& !txtJornada.getText().toString().equals("")){
                    correcto = dbActividades.editarActividad(id, txtNombre.getText().toString(), txtJornada.getText().toString(),txtPrioridad.getText().toString());
                    if (correcto){
                        Toast.makeText(EditarActivity.this, "Actividad modificada", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else {
                        Toast.makeText(EditarActivity.this, "Error al modificar Actividad", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(EditarActivity.this, "Debe llenar todos los campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
