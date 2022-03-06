package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayAdapter<String> listHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);

        model.getHistoryList().observe(this, list -> {
            listHistoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            binding.listHistory.setAdapter(listHistoryAdapter);
        });

        binding.btnPlus.setOnClickListener(e -> {
            expressionAction("+", model, viewRoot);
        });
        binding.btnMinus.setOnClickListener(e -> {
            expressionAction("-", model, viewRoot);
        });
        binding.btnMultiply.setOnClickListener(e -> {
            expressionAction("*", model, viewRoot);
        });
        binding.btnDivide.setOnClickListener(e -> {
            expressionAction("/", model, viewRoot);
        });
    }

    private void expressionAction(String nameExpression, MainViewModel model, View viewRoot) {
        try {
            String numberA = binding.inpA.getText().toString().trim();
            String numberB = binding.inpB.getText().toString().trim();
            model.buttonExpression(nameExpression, numberA, numberB);
            listHistoryAdapter.notifyDataSetChanged();

            binding.inpA.getText().clear();
            binding.inpB.getText().clear();
        } catch (NumberFormatException ee) {
            Snackbar snackbar = Snackbar.make(viewRoot, "Please check your input number (isNotEmpty or isNotText)!", Snackbar.LENGTH_LONG);
            snackbar.show();
        } catch (Exception ee) {
            ee.printStackTrace();
            Snackbar snackbar = Snackbar.make(viewRoot, ee.getMessage() == null ? "Problem occurred, please try again!" : ee.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}