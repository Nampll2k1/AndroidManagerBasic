package com.example.se1620_he150191_nguyenhungthanh2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BirdAdapter extends RecyclerView.Adapter<BirdAdapter.BirdHolder> {

    List<Bird> birdList;

    public BirdAdapter(List<Bird> addressList) {
        this.birdList = addressList;
    }

    @NonNull
    @Override
    public BirdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bird, parent, false);
        return new BirdHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BirdHolder holder, int position) {
        Bird address = birdList.get(position);
        String result ="{\n\tid: " + address.getId() + ",\n\tName: " + address.getName()
                + ",\n\tRegion: " + address.getRegion() + ",\n\tSpecies: " + address.getSpecies() + ",\n}\n";
        holder.tv_bird.setText(result);
    }

    @Override
    public int getItemCount() {
        return birdList.size();
    }

    public class BirdHolder extends RecyclerView.ViewHolder {
        TextView tv_bird;

        public BirdHolder(@NonNull View itemView) {
            super(itemView);
            tv_bird = itemView.findViewById(R.id.tv_bird);
        }
    }
}
