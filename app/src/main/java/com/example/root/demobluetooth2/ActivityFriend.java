package com.example.root.demobluetooth2;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ToggleButton;

import com.example.root.demobluetooth2.FragmentAdapter.AdapterFriend;
import com.example.root.demobluetooth2.FragmentAdapter.ItemFriend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by root on 29/04/2017.
 */

public class ActivityFriend extends Activity {
    private static BluetoothAdapter bTAdapter;
    private AdapterFriend adapter;
    private List<ItemFriend> friends;

    public static ActivityFriend newInstance(BluetoothAdapter adapter) {
        ActivityFriend fragment = new ActivityFriend();
        bTAdapter = adapter;
        return fragment;
    }

    public ActivityFriend() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        bTAdapter = BluetoothAdapter.getDefaultAdapter();
        ToggleButton scan = (ToggleButton) findViewById(R.id.scan);
        ImageView ivfriend = (ImageView) findViewById(R.id.ivfriend);
        RecyclerView mListView = (RecyclerView) findViewById(R.id.list);
        friends = new ArrayList<>();
        adapter = new AdapterFriend(this, friends, bTAdapter);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setHasFixedSize(true);
        mListView.setAdapter(adapter);
        ivfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        scan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

                if (isChecked) {
                    friends.clear();
                    ActivityFriend.this.registerReceiver(bReciever, filter);
                    bTAdapter.startDiscovery();
                } else {
                    ActivityFriend.this.unregisterReceiver(bReciever);
                    bTAdapter.cancelDiscovery();
                }
            }
        });
    }


    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.d("DEVICELIST", "Bluetooth device found\n");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Create a new device item
                ItemFriend newDevice = new ItemFriend(device.getName(), device.getAddress());
                // Add it to our adapter
                friends.add(newDevice);
                adapter.notifyDataSetChanged();
            }
        }
    };
}
