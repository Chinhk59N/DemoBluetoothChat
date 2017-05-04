package com.example.root.demobluetooth2.FragmentAdapter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.demobluetooth2.ActivityChat;
import com.example.root.demobluetooth2.ActivityFriend;
import com.example.root.demobluetooth2.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by root on 29/04/2017.
 */

public class AdapterFriend extends RecyclerView.Adapter<AdapterFriend.FragmentViewHorder>{
        private static final String TAG = "AdapterChat";
        private List<ItemFriend> friends;
        private Context mcontext;
        private LayoutInflater mlayoutInflater;

    public AdapterFriend(Context context, List<ItemFriend> data, BluetoothAdapter adapter) {
            mcontext = context;
            friends = data;
            mlayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public AdapterFriend.FragmentViewHorder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemview = mlayoutInflater.inflate(R.layout.item_friend, parent, false);
            return new FragmentViewHorder(itemview);
        }

        @Override
        public void onBindViewHolder(final AdapterFriend.FragmentViewHorder holder, int position) {
            ItemFriend item = friends.get(position);
            holder.namefriend.setText(item.getNamefriend());
            holder.ndfriend.setText(item.getNdfriend());
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    class ConnectThread extends Thread{
                        private BluetoothSocket bTSocket;

                        public boolean connect(BluetoothDevice bTDevice, UUID mUUID) {
                            BluetoothSocket temp = null;
                            try {
                                temp = bTDevice.createRfcommSocketToServiceRecord(mUUID);
                            } catch (IOException e) {
                                Log.d("CONNECTTHREAD","Could not create RFCOMM socket:" + e.toString());
                                return false;
                            }
                            try {
                                bTSocket.connect();
                            } catch(IOException e) {
                                Log.d("CONNECTTHREAD","Could not connect: " + e.toString());
                                try {
                                    bTSocket.close();
                                } catch(IOException close) {
                                    Log.d("CONNECTTHREAD", "Could not close connection:" + e.toString());
                                    return false;
                                }
                            }
                            return true;
                        }

                        public boolean cancel() {
                            try {
                                bTSocket.close();
                            } catch(IOException e) {
                                Log.d("CONNECTTHREAD","Could not close connection:" + e.toString());
                                return false;
                            }
                            return true;
                        }
                    }
                     class ManageConnectThread extends Thread {

                        public ManageConnectThread() { }

                        public void sendData(BluetoothSocket socket, int data) throws IOException{
                            ByteArrayOutputStream output = new ByteArrayOutputStream(4);
                            output.write(data);
                            OutputStream outputStream = socket.getOutputStream();
                            outputStream.write(output.toByteArray());
                        }

                        public int receiveData(BluetoothSocket socket) throws IOException{
                            byte[] buffer = new byte[4];
                            ByteArrayInputStream input = new ByteArrayInputStream(buffer);
                            InputStream inputStream = socket.getInputStream();
                            inputStream.read(buffer);
                            return input.read();
                        }
                    }
                    String Namefriend= holder.namefriend.getText().toString();
                    Intent intent = new Intent(mcontext, ActivityChat.class );
                    intent.putExtra("name", Namefriend);
                    mcontext.startActivity(intent);

                }
            });


        }

        @Override
        public int getItemCount() {

            return friends.size();
        }

        public class FragmentViewHorder extends RecyclerView.ViewHolder{
            TextView namefriend;
            TextView ndfriend;
            public FragmentViewHorder(View itemView) {
                super(itemView);
                namefriend = (TextView) itemView.findViewById(R.id.tvnamefriend);
                ndfriend = (TextView) itemView.findViewById(R.id.tvndfriend);
            }
        }
}
