package com.example.test_0418;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.InputMismatchException;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, TextWatcher{
    private TextView output;
    private EditText edtnumber;

    private String outputStr = "";
    private String outputGen = "";
    private String outputType = "";
    private String outputPrice = "";
    private String outputnum = "";
    private Button button;
    private RadioGroup rg;

    private RadioGroup type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.rgGender);
        type = (RadioGroup) findViewById(R.id.rgType);
        // 註冊傾聽者物件
        rg.setOnCheckedChangeListener(this);
        type.setOnCheckedChangeListener(this);
        edtnumber = (EditText) findViewById(R.id.edtNumber);
        edtnumber.addTextChangedListener(this);
        //num = Integer.parseInt(edtnumber.getText().toString());
        output = (TextView) findViewById(R.id.lblOutput);
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        TxvShow(rg, rg.getCheckedRadioButtonId());
        TxvShow(type, type.getCheckedRadioButtonId());
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        TxvShow(radioGroup, checkedId);
    }

    public void TxvShow(RadioGroup radioGroup, int checkedId){
        //int num = Integer.parseInt(edtnumber.getText().toString());
        outputnum = edtnumber.getText().toString();
        // 判斷選擇的 RadioButton
        if (radioGroup.getId() == R.id.rgGender) {
            if (checkedId == R.id.rdbBoy) {
                outputGen = "男性\n";
            } else if (checkedId == R.id.rdbGirl) {
                outputGen = "女性\n";
            }
        }
        else if(radioGroup.getId() == R.id.rgType) {
            if (checkedId == R.id.rdbAdult) {
                outputType="成人票\n";
            } else if (checkedId == R.id.rdbChild) {
                outputType="兒童票\n";
            } else {
                outputType="學生票\n";
            }
        }

        if(! outputnum.isEmpty()){
            if(radioGroup.getId() == R.id.rgType) {
                if (checkedId == R.id.rdbAdult) {
                    outputPrice = "\n金額：" + String.valueOf(500 * Integer.parseInt(outputnum));
                } else if (checkedId == R.id.rdbChild) {
                    outputPrice = "\n金額：" + String.valueOf(250 * Integer.parseInt(outputnum));
                } else {
                    outputPrice = "\n金額：" + String.valueOf(400 * Integer.parseInt(outputnum));
                }
            }
            outputnum = "張數：" + outputnum;
        }
        else{
            outputPrice = "";
            outputnum = "";
        }

        outputStr = outputGen + outputType + outputnum + outputPrice;
        output.setText(outputStr) ;
    }
}