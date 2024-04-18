package com.example.TicketPurchase_0418;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.InputMismatchException;


public class MainActivity extends AppCompatActivity {
    private TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editnum = (EditText) findViewById(R.id.edtNumber);
                    output = (TextView) findViewById(R.id.lblOutput);
                    RadioButton boy = (RadioButton) findViewById(R.id.rdbBoy);
                    RadioButton girl = (RadioButton) findViewById(R.id.rdbGirl);
                    try{
                    int price;
                    String outputStr = "";
                    int number = Integer.parseInt(editnum.getText().toString());

                    if(boy.isChecked())
                        outputStr += "男生\n";
                    else if(girl.isChecked())
                        outputStr += "女生\n";

                    RadioGroup type = (RadioGroup) findViewById(R.id.rgType);
                    /*switch(type.getCheckedRadioButtonId()){
                        case R.id.rdbAdult:
                            outputStr += "全票\n";
                            break;
                        case R.id.rdbChild:
                            outputStr += "兒童票\n";
                            break;
                        case R.id.rdbStudent:
                            outputStr += "學生票\n";
                            break;
                    }*/
                        if (type.getCheckedRadioButtonId() == R.id.rdbAdult) {
                            price = number * 500;
                            outputStr += "全票\n金額為：" + price;
                        } else if (type.getCheckedRadioButtonId() == R.id.rdbChild) {
                            price = number * 250;
                            outputStr += "兒童票\n金額為：" + price;
                        } else {
                            price = number * 400;
                            outputStr += "學生票\n金額為：" + price;
                        }
                        output.setText(outputStr);
                        output.setTextColor(Color.BLACK);

                        Intent intent = new Intent(MainActivity.this, TicketInformationActivity.class);
                        intent.putExtra("outputStr", outputStr);
                        startActivity(intent);
                    }catch(NumberFormatException e){
                        if (editnum.getText().toString().isEmpty()){
                            output.setText("請輸入購買張數!");
                            output.setTextColor(Color.RED);
                        }
                    }

                }
            });
        }

    }