package com.example.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<String>> historyList;

    public LiveData<List<String>> getHistoryList() {
        if (historyList == null) {
            historyList = new MutableLiveData<>();
            historyList.setValue(new ArrayList<String>());
        }
        return historyList;
    }

    public static String fmt(double d) {
        if (d == (int)d)
            return String.format("%d", (int)d);
        else
            return String.format("%s", d);
    }

    public void buttonExpression(String buttonText, String numberA, String numberB) throws Exception {
        ArrayList<String> currentList = (ArrayList<String>) historyList.getValue();
        double result = 0.0;
        switch (buttonText) {
            case "+":
                result = Double.parseDouble(numberA) + Double.parseDouble(numberB);
                currentList.add(numberA + " " + buttonText + " " + numberB + " = " + fmt(result));
                historyList.setValue(currentList);
                break;
            case "-":
                result = Double.parseDouble(numberA) - Double.parseDouble(numberB);
                currentList.add(numberA + " " + buttonText + " " + numberB + " = " + fmt(result));
                historyList.setValue(currentList);
                break;
            case "*":
                result = Double.parseDouble(numberA) * Double.parseDouble(numberB);
                currentList.add(numberA + " " + buttonText + " " + numberB + " = " + fmt(result));
                historyList.setValue(currentList);
                break;
            case "/":
                if (Double.parseDouble(numberB) == 0.0) {
                    throw new Exception("Divided by zero!");
                }
                result = Double.parseDouble(numberA) / Double.parseDouble(numberB);
                currentList.add(numberA + " " + buttonText + " " + numberB + " = " + fmt(result));
                historyList.setValue(currentList);
                break;
            default:
                break;
        }
    }
}
