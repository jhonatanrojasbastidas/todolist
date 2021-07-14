package com.example.listadoactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadoactividades.db.DbActividades;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtJornada, txtPrioridad;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtJornada = findViewById(R.id.txtJornada);
        txtPrioridad = findViewById(R.id.txtPrioridad);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbActividades dbActividades = new DbActividades( NuevoActivity.this);
                long id = dbActividades.insertarActividad(txtNombre.getText().toString(),txtJornada.getText().toString(),txtPrioridad.getText().toString());

                if(id > 0){
                    Toast.makeText(NuevoActivity.this, "ACTIVIDAD GUARDADA", Toast.LENGTH_LONG).show();
                    limpiar();
                } else{
                    Toast.makeText(NuevoActivity.this, "ERROR, ACTIVIDAD NO GUARDADA", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar(){
        txtNombre.setText("");
        txtJornada.setText("");
        txtPrioridad.setText("");
    }
}