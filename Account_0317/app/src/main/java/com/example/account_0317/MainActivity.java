package com.example.account_0317;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void button_Click(View view){
        EditText edtAccount = (EditText) findViewById(R.id.edtAccount);
        EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        TextView txvShow = (TextView) findViewById(R.id.txvShow);
        String account = "A111223022";
        String password = "Applepie151";
        if(edtAccount.getText().toString().equals(account) &&
                edtPassword.getText().toString().equals(password)){
            txvShow.setText("登入成功");
        }
        else if(edtAccount.getText().toString().equals(account) &&
                !edtPassword.getText().toString().equals(password)){
            txvShow.setText("密碼錯誤");
            edtPassword.setText("");
        }
        else if(!edtAccount.getText().toString().equals(account) &&
                edtPassword.getText().toString().equals(password)){
            txvShow.setText("帳號錯誤");
            edtAccount.setText("");
        }
        else{
            txvShow.setText("查無此資料");
            edtAccount.setText("");
            edtPassword.setText("");
        }
    }

}