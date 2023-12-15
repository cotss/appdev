package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Enable options menu in this fragment
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        // Find the view corresponding to the three dots (overflow) menu
        View menuItemView = getActivity().findViewById(R.id.menu_home); // Replace with your overflow menu ID

        // Create a PopupMenu and inflate a custom layout
        PopupMenu popupMenu = new PopupMenu(getActivity(), menuItemView);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

        // Customize the appearance of the menu items (e.g., change background color)
        for (int i = 0; i < popupMenu.getMenu().size(); i++) {
            MenuItem menuItem = popupMenu.getMenu().getItem(i);
            View popupMenuItemView = (View) popupMenu.getMenu().getItem(i).getActionView();

            // Check if popupMenuItemView is not null before setting its background color
            if (popupMenuItemView != null) {
                // Customize the popupMenuItemView background color or other properties here
                popupMenuItemView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.appbar)); // Set your desired color
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_module5) {
            switchToModule5Fragment();
            return true;
        } else if (id == R.id.menu_module8) {
            switchToModule8Fragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchToModule5Fragment() {
        if (!(requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof Module5Fragment)) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Module5Fragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void switchToModule8Fragment() {
        if (!(requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof Module8Fragment)) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Module8Fragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
