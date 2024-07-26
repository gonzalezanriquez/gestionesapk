package com.example.davicio.adptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.davicio.R;
import com.example.davicio.entidades.Usuarios;

import java.util.ArrayList;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuariotoViewHolder> {

    ArrayList<Usuarios> listasdeusuarios;

    public ListaUsuariosAdapter(ArrayList<Usuarios> listasdeusuarios){
        this.listasdeusuarios=listasdeusuarios;
    }

    @NonNull
    @Override
    public ListaUsuariosAdapter.UsuariotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuario,null,false);
        return  new UsuariotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaUsuariosAdapter.UsuariotoViewHolder holder, int position) {

        holder.viewNombre.setText(listasdeusuarios.get(position).getNombre());
        holder.viewApellido.setText(listasdeusuarios.get(position).getApellido());
        holder.viewMail.setText(listasdeusuarios.get(position).getMail());

    }

    @Override
    public int getItemCount() {
        return listasdeusuarios.size();
    }

    public class UsuariotoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewApellido,viewMail;
        public UsuariotoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre= itemView.findViewById(R.id.viewNombre);
            viewApellido= itemView.findViewById(R.id.viewApellido);
            viewMail= itemView.findViewById(R.id.viewMail);



        }
    }
}
