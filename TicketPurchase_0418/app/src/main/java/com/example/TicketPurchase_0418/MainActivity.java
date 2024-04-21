package com.example.TicketPurchase_0418;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, TextWatcher {
    private TextView output;
    private EditText edtnumber;
    private String outputStr = "";
    private String outputGen = "";
    private String outputType = "";
    private String outputPrice = "";
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

        Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Str = "";
                    try {
                        String text = edtnumber.getText().toString();
                        if (!text.isEmpty()) {
                            if (rg.getCheckedRadioButtonId() == -1) {
                                Str += "請選擇性別!\n";
                            }
                            else if(type.getCheckedRadioButtonId() == -1) {
                                Str += "請選擇票種!\n";
                            }
                            else{
                                // 如果 EditText 和 RadioButton 都有資料，執行跳轉操作
                                Intent intent = new Intent(MainActivity.this, TicketInformationActivity.class);
                                intent.putExtra("outputStr", outputStr);
                                startActivity(intent);
                            }
                        }
                        else if(text == null){
                            if (rg.getCheckedRadioButtonId() == -1) {
                                Str += "請選擇性別!\n";
                                if(type.getCheckedRadioButtonId() == -1)
                                    Str += "請選擇票種!\n";
                            }
                            else if(type.getCheckedRadioButtonId() == -1)
                                Str += "請選擇票種!\n";
                            throw new NumberFormatException();
                        }
                        output.setText(Str);
                        output.setTextColor(Color.RED);
                    } catch (NumberFormatException e) {
                        Str += "請輸入購買張數!";
                        output.setText(Str);
                        output.setTextColor(Color.RED);
                    }
                }
            });
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
        String outputnum = edtnumber.getText().toString();
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
            if(type.getCheckedRadioButtonId() == -1){
                outputType="";
            }

            else if(radioGroup.getId() == R.id.rgType) {
                if (checkedId == R.id.rdbAdult) {
                    outputPrice = "\n金額：" + (500 * Integer.parseInt(outputnum));
                } else if (checkedId == R.id.rdbChild) {
                    outputPrice = "\n金額：" + (250 * Integer.parseInt(outputnum));
                } else {
                    outputPrice = "\n金額：" + (400 * Integer.parseInt(outputnum));
                }
            }
            outputnum = "張數：" + outputnum;
        }
        else{
            outputPrice = "";
            outputnum = "";
        }

        outputStr = outputGen + outputType + outputnum + outputPrice;
        output.setText(outputStr);
        output.setTextColor(Color.BLACK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}