package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class Module8Fragment extends Fragment {

    private TextView resultText;
    private EditText guessInput;
    private Button guessButton;
    private int randomNumber;

    private SharedPreferences preferences;
    private int backgroundColor = Color.WHITE; // Default background color
    private int textColor = Color.BLACK; // Default text color

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_module8, container, false);

        guessInput = view.findViewById(R.id.guessInput);
        guessButton = view.findViewById(R.id.guessButton);
        resultText = view.findViewById(R.id.resultText);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGuess();
            }
        });

        // Retrieve saved colors from Module 5
        preferences = requireActivity().getSharedPreferences("ColorPrefs", 0);
        backgroundColor = preferences.getInt("background_color", Color.WHITE);
        textColor = preferences.getInt("text_color", Color.BLACK);

        // Apply saved colors to UI elements in Module 8
        view.setBackgroundColor(backgroundColor);
        resultText.setTextColor(textColor);

        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        return view;
    }

    private void handleGuess() {
        String guessStr = guessInput.getText().toString();

        if (!guessStr.isEmpty()) {
            int userGuess = Integer.parseInt(guessStr);

            if (userGuess == randomNumber) {
                resultText.setText("Correct! You guessed it!");
            } else if (userGuess < randomNumber) {
                resultText.setText("Try higher!");
            } else {
                resultText.setText("Try lower!");
            }
        }
    }
}
