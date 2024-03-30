package com.example.bmi_0328;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvShow = (TextView)findViewById(R.id.txvShow);
        txvShow.setTextSize(36);
        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnCalc.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edtHeight = (EditText) findViewById(R.id.edtHeight);
        EditText edtWeight = (EditText) findViewById(R.id.edtWeight);
        try {
            if (v.getId() == R.id.btnCalc) {
                double height = Double.parseDouble(edtHeight.getText().toString());
                double Weight = Double.parseDouble(edtWeight.getText().toString());
                double bmi = Weight / Math.pow(height / 100.0, 2);
                if (bmi >= 24)
                    txvShow.setTextColor(Color.RED);
                else if (bmi < 18.5)
                    txvShow.setTextColor(Color.BLUE);
                else
                    txvShow.setTextColor(Color.GREEN);

                txvShow.setText(String.format("%.2f", bmi));
            } else {
                edtHeight.setText("");
                edtWeight.setText("");
                txvShow.setText("");
            }
        } catch (NumberFormatException e) {
            if (edtHeight.getText().toString().isEmpty() || !isNumeric(edtHeight.getText().toString())) {
                if (edtWeight.getText().toString().isEmpty() || !isNumeric(edtWeight.getText().toString())) {
                    txvShow.setText("身高體重格式錯誤");
                    edtHeight.setText("");
                    edtWeight.setText("");
                } else {
                    edtHeight.setText("");
                    txvShow.setText("身高格式錯誤");
                }
            } else {
                txvShow.setText("體重格式錯誤");
                edtWeight.setText("");
            }

            txvShow.setTextColor(Color.RED);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

