package com.example.TicketPurchase_0418;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicketInformationActivity extends AppCompatActivity {

    public static final int RESULT_OK = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_information);

        TextView output = findViewById(R.id.lblOutput);
        String outputStr = getIntent().getStringExtra("outputStr");
        output.setText(outputStr);

        Button modifyButton = findViewById(R.id.button);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("OutputStr", outputStr);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }

}