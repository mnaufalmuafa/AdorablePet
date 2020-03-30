package com.example.adorablepet.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adorablepet.R;
import com.example.adorablepet.contoller.DetailProduk;
import com.example.adorablepet.model.Produk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {

    private ArrayList<Produk> dataList;
    private Context context;

    public ProdukAdapter(ArrayList<Produk> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_produk, parent, false);
        return new ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukViewHolder holder, final int position) {
        Picasso.get().load(dataList.get(position).linkFoto).into(holder.IVProduk);
        holder.TVProduk.setText(dataList.get(position).nama);
        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mSettings = context.getSharedPreferences("dataProduk", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putInt("id",dataList.get(position).getId());
                editor.apply();
                Intent toDetail = new Intent(context, DetailProduk.class);
                context.startActivity(toDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ProdukViewHolder extends RecyclerView.ViewHolder {
        private ImageView IVProduk;
        private TextView TVProduk;
        private ConstraintLayout cl;
        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            IVProduk = itemView.findViewById(R.id.IVProduk);
            TVProduk = itemView.findViewById(R.id.TVProduk);
            cl = itemView.findViewById(R.id.CL);
        }
    }
}
