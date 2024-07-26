package com.example.davicio.adptadores;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.davicio.R;
import com.example.davicio.entidades.Sucursales;
import com.example.davicio.mapaActivity;

import java.util.ArrayList;

public class ListaSucursalesAdapter extends RecyclerView.Adapter<ListaSucursalesAdapter.SucursalestoViewHolder> {

    private ArrayList<Sucursales> listasdesucursales;

    public ListaSucursalesAdapter(ArrayList<Sucursales> listasdesucursales) {
        this.listasdesucursales = listasdesucursales;
    }


    @NonNull
    @Override
    public SucursalestoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_sucursales, parent, false);
        return new SucursalestoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SucursalestoViewHolder holder, int position) {
        final Sucursales sucursales = listasdesucursales.get(position);
        holder.viewNombreSucursal.setText(sucursales.getNombre());
        holder.viewDireccion.setText(sucursales.getDireccion());
        holder.viewTelefono.setText(String.valueOf(sucursales.getTelefono()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("nombre", sucursales.getNombre());
                bundle.putString("direccion", sucursales.getDireccion());

                Intent intent = new Intent(view.getContext(), mapaActivity.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listasdesucursales.size();
    }

    public class SucursalestoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombreSucursal, viewDireccion, viewTelefono;

        public SucursalestoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombreSucursal = itemView.findViewById(R.id.viewNombreSucursal);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
        }
    }
}
