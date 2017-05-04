package com.example.root.demobluetooth2.FragmentAdapter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.root.demobluetooth2.ActivityFriend;
import com.example.root.demobluetooth2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by root on 28/04/2017.
 */

public class FragmentFriend extends Fragment {
    private static BluetoothAdapter bTAdapter;
    private RelativeLayout rlfriend;
    private RecyclerView recyclerView;
    private List<ItemFriend> friends;
    private AdapterFriend adapter;
    public FragmentFriend(){

    }
    public static FragmentFriend newInstance(BluetoothAdapter adapter) {
        FragmentFriend fragment = new FragmentFriend();
        bTAdapter = adapter;
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvfriend);
        rlfriend = (RelativeLayout)view.findViewById(R.id.addfriend);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FragmentFriend.this.getContext() , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

       rlfriend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               doactivityfriend();
            }
        });

      return view;
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       friends = new ArrayList<ItemFriend>();
       bTAdapter = BluetoothAdapter.getDefaultAdapter();
       Set<BluetoothDevice> pairedDevices = bTAdapter.getBondedDevices();
       if (pairedDevices.size() > 0) {
           for (BluetoothDevice device : pairedDevices) {
               ItemFriend newDevice = new ItemFriend(device.getName(), device.getAddress());
               friends.add(newDevice);
           }
       }
       if (friends.size() == 0) {
           friends.add(new ItemFriend("No Devices", "false"));
       }
       adapter = new AdapterFriend(FragmentFriend.this.getContext(), friends, bTAdapter);
   }

    @Nullable
    public void doactivityfriend(){
       Intent intent = new Intent(FragmentFriend.this.getContext(), ActivityFriend.class );
       FragmentFriend.this.getContext().startActivity(intent);
   }
}
