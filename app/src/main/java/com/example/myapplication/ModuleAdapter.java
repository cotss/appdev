package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {

    private final List<String> modules;
    private final Context context;

    public ModuleAdapter(Context context) {
        this.context = context;
        this.modules = new ArrayList<>();
        modules.add("Home");
        modules.add("Module 5");
        modules.add("Module 8");
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_module, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        String module = modules.get(position);
        holder.moduleTitle.setText(module);

        holder.itemView.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) context;
            switch (position) {
                case 0:
                    activity.switchFragment(new HomeFragment());
                    break;
                case 1:
                    activity.switchFragment(new Module5Fragment());
                    break;
                case 2:
                    activity.switchFragment(new Module8Fragment());
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    static class ModuleViewHolder extends RecyclerView.ViewHolder {
        TextView moduleTitle;

        ModuleViewHolder(View itemView) {
            super(itemView);
            moduleTitle = itemView.findViewById(R.id.moduleTitle);
        }
    }
}

