package com.example.TicketPurchase_0418;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TicketInformationActivity extends AppCompatActivity {
    private static final int MODIFY_REQUEST_CODE = 1;
    private Button modifyButton;
    private EditText output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_information);

        TextView output = findViewById(R.id.lblOutput);
        String outputStr = getIntent().getStringExtra("outputStr");
        output.setText(outputStr);

        modifyButton = findViewById(R.id.button);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动 MainActivity 以修改数据
                Intent intent = new Intent(TicketInformationActivity.this, MainActivity.class);
                startActivityForResult(intent, MODIFY_REQUEST_CODE);
            }
        });


    }

}