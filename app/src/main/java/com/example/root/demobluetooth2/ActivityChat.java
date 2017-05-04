package com.example.root.demobluetooth2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.demobluetooth2.FragmentAdapter.AdapterMessger;
import com.example.root.demobluetooth2.FragmentAdapter.ItemMessger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 29/04/2017.
 */

public class ActivityChat extends Activity {
    private RecyclerView rcmessger;
    private List<ItemMessger> messgers;
    private AdapterMessger adapterMessger;
    private EditText ndchat;
    private String Namechat;
    private TextView name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
         name = (TextView)findViewById(R.id.tvchat);
        ndchat = (EditText)findViewById(R.id.etndchat);
        rcmessger = (RecyclerView)findViewById(R.id.rcmessger);
        ImageView image = (ImageView)findViewById(R.id.ivchat);
        ImageView send = (ImageView)findViewById(R.id.send);
        Intent intent = getIntent();
        Namechat = intent.getStringExtra("name");
        name.setText(Namechat);
        messgers = new ArrayList<ItemMessger>();
        adapterMessger = new AdapterMessger(ActivityChat.this, messgers);
        rcmessger.setLayoutManager(new LinearLayoutManager(ActivityChat.this));
        rcmessger.setHasFixedSize(true);
        rcmessger.setAdapter(adapterMessger);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemMessger messger= new ItemMessger(ndchat.getText().toString());
                messgers.add(messger);
                adapterMessger.notifyDataSetChanged();
            }
        });
    }

}
