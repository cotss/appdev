package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Module5Fragment extends Fragment {

    private int defaultBackgroundColor; // Store the default background color
    private int textColor = Color.WHITE; // Default text color
    private SharedPreferences preferences;
    private int colorIndex = 0; // Index to cycle through colors
    private final int[] backgroundColors = {
            Color.parseColor("#009688"), Color.parseColor("#FF5722"), Color.parseColor("#2196F3"),
            Color.parseColor("#673AB7"), Color.parseColor("#FFC107"), Color.parseColor("#4CAF50")
    }; // Array of background colors to cycle through

    private final int[] textColors = {
            Color.parseColor("#FF5722"), Color.parseColor("#2196F3"), Color.parseColor("#673AB7"),
            Color.parseColor("#FFC107"), Color.parseColor("#4CAF50"), Color.parseColor("#009688")
    }; // Array of text colors to cycle through

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_module5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Store the default background color
        defaultBackgroundColor = getResources().getColor(R.color.default_background_color); // Replace with your default color resource

        preferences = requireActivity().getSharedPreferences("ColorPrefs", 0);
        int backgroundColor = preferences.getInt("background_color", defaultBackgroundColor);
        textColor = preferences.getInt("text_color", Color.WHITE);

        // Apply colors to UI elements in Module 5
        view.setBackgroundColor(backgroundColor);

        Button colorPickerButton = view.findViewById(R.id.colorPickerButton);
        Button revertButton = view.findViewById(R.id.revertButton);

        TextView modNumTextView = view.findViewById(R.id.modNum); // TextView whose text color needs to be changed

        colorPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = backgroundColors[colorIndex % backgroundColors.length]; // Get the background color from the array based on the index
                int textColor = textColors[colorIndex % textColors.length]; // Get the text color from the array based on the index
                view.setBackgroundColor(backgroundColor);
                modNumTextView.setTextColor(textColor); // Set the text color of modNumTextView
                saveColorPreferences(backgroundColor, textColor);
                colorIndex++;
            }
        });

        revertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundColor(defaultBackgroundColor);
                modNumTextView.setTextColor(Color.WHITE); // Revert text color to black
                saveColorPreferences(defaultBackgroundColor, Color.WHITE);
            }
        });
    }

    private void saveColorPreferences(int backgroundColor, int textColor) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("background_color", backgroundColor);
        editor.putInt("text_color", textColor);
        editor.apply();
    }
}
