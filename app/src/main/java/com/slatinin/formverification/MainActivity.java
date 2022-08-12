package com.slatinin.formverification;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.slatinin.formverification.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private String[] array = {"Rv first", "Rv second", "Rv third", "Rv forth", "Rv fifth", "Rv sixth", "Rv seventh", "Rv eighth", "Rv ninth", "Rv tenth"};

    private ValidationManager validationManager = new ValidationManager();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new RvAdapter(array, validationManager));
        binding.submit.setOnClickListener(view -> {
           if ( validationManager.validate(binding.scrollV)){
               Toast.makeText(this, "Validation passed", Toast.LENGTH_SHORT).show();
           }else {
               Toast.makeText(this, "Validation failed", Toast.LENGTH_SHORT).show();
           }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        validationManager.addFieldToBeValidated(new EmptyVerification(binding.firstField), 0);
        validationManager.addFieldToBeValidated(new EmptyVerification(binding.secondField), 1);
        validationManager.addFieldToBeValidated(new EmptyVerification(binding.thirdField), 2);
        validationManager.addFieldToBeValidated(new EmptyVerification(binding.forthField), 3);
        validationManager.addFieldToBeValidated(new EmptyVerification(binding.lastField), 1000);
    }
}