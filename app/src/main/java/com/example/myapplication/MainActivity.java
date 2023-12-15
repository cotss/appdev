package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int num1 = 5;
    private int num2 = 10;

    private RecyclerView recyclerView;
    private ModuleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "My name is Chester G. Miranda"); // Log your name when the activity is created

        // Your existing code...
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ModuleAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        switchFragment(new HomeFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Add 2 to num1 and num2 every time the application is resumed
        num1 += 2;
        num2 += 2;

        Log.d(TAG, "num1: " + num1 + ", num2: " + num2); // Log updated values of num1 and num2
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Add 2 to num1 and num2 every time the application is paused
        num1 += 2;
        num2 += 2;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            switchFragment(new HomeFragment());
            return true;
        } else if (id == R.id.menu_module5) {
            switchFragment(new Module5Fragment());
            return true;
        } else if (id == R.id.menu_module8) {
            switchFragment(new Module8Fragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
