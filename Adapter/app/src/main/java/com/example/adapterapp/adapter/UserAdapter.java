package com.example.adapterapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapterapp.R;
import com.example.adapterapp.model.User;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView home;
        Button btButton;
    }

    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, R.layout.item_user, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_user, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.home = (TextView) convertView.findViewById(R.id.tvHome);
            viewHolder.btButton = (Button) convertView.findViewById(R.id.btButton);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data from the data object via the viewHolder object
        // into the template view.
        if (user != null) {
            viewHolder.name.setText(user.getName());
            viewHolder.home.setText(user.getHomeTown());

            // --- Exercise: Attaching Event Handlers Within Adapter (using user object in tag) ---
            // Cache user object inside the button using `setTag`
            viewHolder.btButton.setTag(user);

            // Attach the click event handler
            viewHolder.btButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Access user from within the tag
                    User user = (User) view.getTag();
                    // Do what you want here...
                    Toast.makeText(getContext(), "Clicked on: " + user.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
