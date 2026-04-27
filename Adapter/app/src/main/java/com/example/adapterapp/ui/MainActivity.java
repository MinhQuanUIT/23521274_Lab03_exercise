package com.example.adapterapp.ui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adapterapp.R;
import com.example.adapterapp.adapter.UserAdapter;
import com.example.adapterapp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo adapter với danh sách rỗng ban đầu
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        UserAdapter adapter = new UserAdapter(this, arrayOfUsers);

        // Gắn adapter vào ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // --- Thực hiện theo bài tập: Populating Data ---

        // 1. Thêm một item đơn lẻ vào adapter
        User newUser = new User("Nathan", "San Diego");
        adapter.add(newUser);

        // 2. Thêm một danh sách (collection) vào adapter
        // Giả lập dữ liệu từ JSON
        JSONArray jsonArray = new JSONArray();
        try {
            JSONObject user1 = new JSONObject();
            user1.put("name", "John");
            user1.put("hometown", "New York");
            
            JSONObject user2 = new JSONObject();
            user2.put("name", "Sarah");
            user2.put("hometown", "London");
            
            jsonArray.put(user1);
            jsonArray.put(user2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Chuyển đổi JSON sang danh sách User và thêm vào adapter
        ArrayList<User> newUsers = User.fromJson(jsonArray);
        adapter.addAll(newUsers);

        // 3. Xóa dữ liệu (nếu cần)
        // adapter.clear();
    }
}
