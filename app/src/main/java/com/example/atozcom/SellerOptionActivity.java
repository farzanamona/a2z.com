package com.example.atozcom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class SellerOptionActivity extends AppCompatActivity {
    String[] sellerOptionItem;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_option);

        sellerOptionItem = new String[]{"Software and Website", "Electronics", "Vehicles", "Property", "Educational Equipments", "Food Item", "Birds Or Animals", "Doctors Equipments", "Business"};

        ArrayAdapter<String> adapter;

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sellerOptionItem);

        gridView = findViewById(R.id.sellerOptionItem_id);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Seleced item is:" + sellerOptionItem[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
