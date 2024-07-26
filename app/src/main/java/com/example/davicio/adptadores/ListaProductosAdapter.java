package com.example.davicio.adptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.davicio.entidades.Productos;
import com.example.davicio.R;

import java.util.ArrayList;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductostoViewHolder> {

    private ArrayList<Productos> listasdeproductos;

    public ListaProductosAdapter(ArrayList<Productos> listasdeproductos) {
        this.listasdeproductos = listasdeproductos;
    }

    @NonNull
    @Override
    public ProductostoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_productos, parent, false);
        return new ProductostoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductostoViewHolder holder, int position) {
        Productos producto = listasdeproductos.get(position);
        holder.viewNombreProducto.setText(producto.getNombre());
        holder.viewDescripcion.setText(producto.getDescripcion());
        holder.viewPrecio.setText(String.valueOf(producto.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return listasdeproductos.size();
    }

    public class ProductostoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombreProducto, viewDescripcion, viewPrecio;

        public ProductostoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombreProducto = itemView.findViewById(R.id.viewNombreProducto);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
        }
    }
}
