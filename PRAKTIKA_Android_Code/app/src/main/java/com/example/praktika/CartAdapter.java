package com.example.praktika;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Coffee> list;
    Runnable onChange;

    public CartAdapter(List<Coffee> list, Runnable onChange) {
        this.list = list;
        this.onChange = onChange;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_coffee, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Coffee c = list.get(position);

        holder.img.setImageResource(c.image);
        holder.name.setText(c.name);
        holder.desc.setText(c.description);
        holder.price.setText(c.price); // ← теперь корректно, price это String!

        holder.btnDelete.setOnClickListener(v -> {
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());

            if (onChange != null) onChange.run(); // обновляем сумму
        });
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name, desc, price;
        ImageButton btnDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgCoffee);
            name = itemView.findViewById(R.id.tvCoffeeName);
            desc = itemView.findViewById(R.id.tvCoffeeDesc);
            price = itemView.findViewById(R.id.tvCoffeePrice);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
