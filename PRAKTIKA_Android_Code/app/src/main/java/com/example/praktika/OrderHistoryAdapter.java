package com.example.praktika;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    List<OrderModel> orders;

    public OrderHistoryAdapter(List<OrderModel> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        OrderModel o = orders.get(pos);

        // конвертируем timestamp → нормальная дата
        long time = Long.parseLong(o.getDate());
        String formatted = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date(time));

        h.tvDate.setText(formatted);
        h.tvCount.setText("Items: " + o.getItems().size());
        h.tvTotal.setText("Total: $" + o.getTotal());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvCount, tvTotal;

        public ViewHolder(@NonNull View v) {
            super(v);
            tvDate = v.findViewById(R.id.tvDate);
            tvCount = v.findViewById(R.id.tvCount);
            tvTotal = v.findViewById(R.id.tvTotal);
        }
    }
}
