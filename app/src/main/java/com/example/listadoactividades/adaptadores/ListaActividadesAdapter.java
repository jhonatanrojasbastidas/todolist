package com.example.listadoactividades.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadoactividades.R;
import com.example.listadoactividades.VerActivity;
import com.example.listadoactividades.entidades.Actividades;

import java.util.ArrayList;

public class ListaActividadesAdapter extends RecyclerView.Adapter<ListaActividadesAdapter.ActividadViewHolder> {

    ArrayList<Actividades> listaActividades;

    public ListaActividadesAdapter(ArrayList<Actividades> listaActividades){
        this.listaActividades = listaActividades;
    }

    @NonNull
    @Override
    public ActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_actividad, null, false);
        return new ActividadViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ActividadViewHolder holder, int position) {
        holder.viewNombre.setText(listaActividades.get(position).getNombre());
        holder.viewJornada.setText(listaActividades.get(position).getJornada());
        holder.viewPrioridad.setText(listaActividades.get(position).getPrioridad());
    }

    @Override
    public int getItemCount() {
        return listaActividades.size();
    }

    public class ActividadViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewJornada, viewPrioridad;

        public ActividadViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewJornada = itemView.findViewById(R.id.viewJornada);
            viewPrioridad = itemView.findViewById(R.id.viewPrioridad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaActividades.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
