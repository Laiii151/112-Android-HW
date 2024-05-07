package com.example.foodimgmenu_0502;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private int[] chkIDs = {R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4, R.id.chk5};
    private int[] imgIDs = {R.id.output1, R.id.output2, R.id.output3, R.id.output4,R.id.output5};

    private TextView txvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvShow = (TextView) findViewById(R.id.showOrder);
        for(int id : imgIDs){
            ImageView img = (ImageView) findViewById(id);
        }

        for(int id : chkIDs){
            CheckBox chk = (CheckBox) findViewById(id);
            chk.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //int id = buttonView.getId();
        boolean checked = false;
        for (int i = 0; i < chkIDs.length; i++) {
            CheckBox chk = (CheckBox) findViewById(chkIDs[i]);
            if (chk.isChecked()) {
                // 如果該checkbox被選中，顯示對應的圖像
                ImageView img = (ImageView) findViewById(imgIDs[i]);
                img.setVisibility(ImageView.VISIBLE);
                checked = true;

            } else {
                // 如果該checkbox未被選中，隱藏對應的圖像
                ImageView img = (ImageView) findViewById(imgIDs[i]);
                img.setVisibility(ImageView.GONE);
            }
            if(checked){
                txvShow.setHint("你點的餐點如下");
            }else{
                txvShow.setHint("請選擇");
            }
        }
    }
}