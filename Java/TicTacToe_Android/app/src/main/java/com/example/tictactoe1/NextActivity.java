package com.example.tictactoe1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NextActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private String scoreHistoryAc2;
    private ListView listViewHistoryAc2; // lv
    private ArrayAdapter<String> adapterAc2; // adapter
    private ArrayList<String> historyAc2 = new ArrayList<>(); // heroes
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Toast.makeText(this, "Welcome To The History Page!", Toast.LENGTH_SHORT).show();

        // Getting The Score History
        scoreHistoryAc2 = getIntent().getStringExtra("HISTORY_SCORE");

        if (scoreHistoryAc2 != null){
            // Adding it to the ArrayList
            historyAc2.add(scoreHistoryAc2);
            // Adding the ArrayList to our ListView
            // +
            // Saving State Using The FileHistoryHelper.java file
            listViewHistoryAc2 = findViewById(R.id.listView_history);
            historyAc2 = FileHistoryHelper.readData(this);
            adapterAc2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyAc2);
            listViewHistoryAc2.setAdapter(adapterAc2);
            adapterAc2.add(scoreHistoryAc2);
            FileHistoryHelper.writeData(historyAc2, this);
            listViewHistoryAc2.onSaveInstanceState();
            adapterAc2.notifyDataSetChanged();
            //in order to check if working:
            //Toast.makeText(this, "" + scoreHistoryAc2, Toast.LENGTH_SHORT).show();
        }else {
            listViewHistoryAc2 = findViewById(R.id.listView_history);
            historyAc2 = FileHistoryHelper.readData(this);
            adapterAc2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyAc2);
            listViewHistoryAc2.setAdapter(adapterAc2);
            listViewHistoryAc2.onSaveInstanceState();
            Toast.makeText(this, "No New Score Were Made(" + scoreHistoryAc2 + ")", Toast.LENGTH_SHORT).show();
        }
        // btnBack Setting
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });
        listViewHistoryAc2.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(NextActivity.this, "" + historyAc2.get(position) + " Got Removed", Toast.LENGTH_SHORT).show();
        historyAc2.remove(position);
        adapterAc2.notifyDataSetChanged();
        FileHistoryHelper.writeData(historyAc2, this);
        return  false;
    }

    public void goToMainActivity(){
        Intent goToBack = new Intent(NextActivity.this, MainActivity.class);
        startActivity(goToBack);
        finish();
    }

}



