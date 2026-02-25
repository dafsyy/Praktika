package com.example.praktika;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    ArrayList<Coffee> list;
    Context context;

    public CoffeeAdapter(ArrayList<Coffee> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void updateList(ArrayList<Coffee> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Используем context из parent — безопаснее
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coffee_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Coffee c = list.get(position);

        holder.img.setImageResource(c.getImage());
        holder.name.setText(c.getName());
        holder.price.setText(c.getPrice());

        // клик открывает DetailsActivity и передаёт данные
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("coffee_image", c.getImage());
            intent.putExtra("coffee_name", c.getName());
            intent.putExtra("coffee_desc", c.getDescription() == null ? "" : c.getDescription());
            intent.putExtra("coffee_price", c.getPrice());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgCoffee);
            name = itemView.findViewById(R.id.textCoffeeName);
            price = itemView.findViewById(R.id.textCoffeePrice);
        }
    }
}
