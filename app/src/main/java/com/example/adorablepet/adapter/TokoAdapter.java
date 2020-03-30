package com.example.adorablepet.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adorablepet.R;

import java.util.ArrayList;

public class TokoAdapter extends RecyclerView.Adapter<TokoAdapter.TokoViewHolder> {

    private ArrayList<String> dataToko = new ArrayList<>();
    private Context mContext;

    public TokoAdapter(ArrayList<String> dataToko, Context mContext) {
        this.dataToko = dataToko;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TokoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_toko, parent, false);
        return new TokoAdapter.TokoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TokoViewHolder holder, int position) {
        holder.TVNomor.setText((position+1)+". ");
        holder.TVToko.setText(dataToko.get(position));
        if (getItemCount() == 1) {
            holder.TVNomor.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dataToko!= null ? dataToko.size() : 0;
    }

    public class TokoViewHolder extends RecyclerView.ViewHolder {
        private TextView TVNomor;
        private TextView TVToko;
        public TokoViewHolder(@NonNull View itemView) {
            super(itemView);
            TVNomor = itemView.findViewById(R.id.TVNomor);
            TVToko = itemView.findViewById(R.id.TVToko);
            Typeface fontRegular=Typeface.createFromAsset(mContext.getAssets(),"font/YuGothR.ttf");
            TVNomor.setTypeface(fontRegular);
            TVToko.setTypeface(fontRegular);
        }
    }
}
