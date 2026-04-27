package com.example.adapterapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.adapterapp.R;
import com.example.adapterapp.model.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private int resource;
    private List<Item> itemList;

    public ItemAdapter(@NonNull Context context, @NonNull List<Item> objects) {
        super(context, R.layout.item_row, objects);
        this.context = context;
        this.resource = R.layout.item_row;
        this.itemList = objects;
    }

    @NonNull@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_row, parent, false);

            holder = new ViewHolder();
            holder.textTitle = convertView.findViewById(R.id.textTitle);
            holder.textDescription = convertView.findViewById(R.id.textDescription);
            holder.buttonAction = convertView.findViewById(R.id.buttonAction);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Item item = getItem(position);

        if (item != null) {
            holder.textTitle.setText(item.getTitle());
            holder.textDescription.setText(item.getDescription());

            holder.buttonAction.setOnClickListener(v -> {
                Toast.makeText(getContext(),
                        "Action clicked for: " + item.getTitle(),
                        Toast.LENGTH_SHORT).show();
            });
        }

        return convertView;
    }

    // ViewHolder pattern for performance
    static class ViewHolder {
        TextView textTitle;
        TextView textDescription;
        Button buttonAction;
    }
}
