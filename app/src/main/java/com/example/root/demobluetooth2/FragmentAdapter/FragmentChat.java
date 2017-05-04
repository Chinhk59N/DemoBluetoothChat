package com.example.root.demobluetooth2.FragmentAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.root.demobluetooth2.ActivityFriend;
import com.example.root.demobluetooth2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by root on 28/04/2017.
 */

public class FragmentChat extends Fragment {
    private RecyclerView recyclerView;
    private List<ItemChat> chats;
    private AdapterChat adapter;
    public FragmentChat(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvchat);
        ImageView ivchat = (ImageView)view.findViewById(R.id.ivchat);
        chats = new ArrayList<>();
        chats.add(new ItemChat("Name", "11:00 SA" , "hien thi tin nhan cuoi" ));
        chats.add(new ItemChat("Name", "11:00 SA" , "hien thi tin nhan cuoi" ));
        chats.add(new ItemChat("Name", "11:00 SA" , "hien thi tin nhan cuoi" ));
        chats.add(new ItemChat("Chinh", "11:00 SA" , "hien thi tin nhan cuoi" ));
        chats.add(new ItemChat("Chinh", "11:00 SA" , "hien thi tin nhan cuoi" ));
        chats.add(new ItemChat("Chinh", "11:00 SA" , "hien thi tin nhan cuoi" ));
        adapter = new AdapterChat(FragmentChat.this.getContext(), chats);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FragmentChat.this.getContext() , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        ivchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(FragmentChat.this.getContext(), ActivityFriend.class);
                FragmentChat.this.getContext().startActivity(intent2);
            }
        });
        return view;
    }
}
