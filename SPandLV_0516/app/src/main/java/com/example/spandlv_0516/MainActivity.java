package com.example.spandlv_0516;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String[] types, main, side, drink;
    private Spinner spTypes;
    private ListView lvtype;
    private String OutputOrder="";
    private String Strmain = "";
    private String Strside = "";
    private String Strdrink = "";
    private TextView output;
    private ArrayAdapter<String> listAdapter;
    private String currentType;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.order){
            Intent intent = new Intent(MainActivity.this, OderInformationActivity.class);
            intent.putExtra("outputStr", OutputOrder);
            startActivity(intent);

        }
        else if(itemId == R.id.cancel){
            Cancel(null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.lblOutput);
        Strmain = getString(R.string.choice);
        Strside = getString(R.string.choice);
        Strdrink = getString(R.string.choice);

        types = getResources().getStringArray(R.array.types);
        spTypes = (Spinner) findViewById(R.id.SideOrder);
        ArrayAdapter<String> adpTypes = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, types);
        spTypes.setAdapter(adpTypes);

        main = getResources().getStringArray(R.array.main);
        side = getResources().getStringArray(R.array.side);
        drink = getResources().getStringArray(R.array.drink);
        spTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentType = types[position];
                updateListView(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lvtype = (ListView) findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        lvtype.setAdapter(listAdapter);
    }

    public void updateListView(int index) {
        //currentType = types[index];
        listAdapter.clear(); // 清空ListView的当前数据

        switch (index) {
            case 0: // Main Courses
               listAdapter.addAll(main);
                currentType = "main";
                show(currentType);
                break;
            case 1: // Sides
                listAdapter.addAll(side);
                currentType = "side";
                show(currentType);
                break;
            case 2: // Drinks
                listAdapter.addAll(drink);
                currentType = "drink";
                show(currentType);
                break;
        }
    }
    public void show(String currentType) {
        lvtype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (currentType) {
                    case "main":
                        String main = (String) parent.getItemAtPosition(position);
                        Strmain = main;
                        break;
                    case "side":
                        String side = (String) parent.getItemAtPosition(position);
                        Strside = side;
                        break;
                    case "drink":
                        String drink = (String) parent.getItemAtPosition(position);
                        Strdrink = drink;
                        break;
                }
                OutputOrder = getString(R.string.strmain) + Strmain +
                        getString(R.string.strside) + Strside +
                        getString(R.string.strdrink) + Strdrink;
                output.setHint(OutputOrder);
            }
        });
    }
    public void Cancel(View view) {
        Strmain = getString(R.string.choice);
        Strside = getString(R.string.choice);
        Strdrink = getString(R.string.choice);
        // 重置 TextView
        OutputOrder = getString(R.string.strmain) + Strmain +
                getString(R.string.strside) + Strside +
                getString(R.string.strdrink) + Strdrink;
        output.setHint(OutputOrder);
        spTypes.setSelection(0);
        updateListView(0); // 回到主餐類型
    }
}