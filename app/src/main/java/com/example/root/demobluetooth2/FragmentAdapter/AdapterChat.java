package com.example.root.demobluetooth2.FragmentAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.demobluetooth2.ActivityChat;
import com.example.root.demobluetooth2.R;

import java.util.List;

/**
 * Created by root on 29/04/2017.
 */
public class AdapterChat extends RecyclerView.Adapter<AdapterChat.FragmentViewHorder>{
    private static final String TAG = "AdapterChat";
    private List<ItemChat> chats;
    private Context mcontext;
    private LayoutInflater mlayoutInflater;


    public AdapterChat(Context context, List<ItemChat> data) {
        mcontext = context;
        chats = data;
        mlayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public AdapterChat.FragmentViewHorder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mlayoutInflater.inflate(R.layout.item_chat, parent, false);
        return new FragmentViewHorder(itemview);
    }

    @Override
    public void onBindViewHolder(final AdapterChat.FragmentViewHorder holder, int position) {
        ItemChat item = chats.get(position);
        holder.namechat.setText(item.getNamechat());
        holder.timechat.setText(item.getTimechat());
        holder.ndchat.setText(item.getNdchat());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               String Namechat= holder.namechat.getText().toString();
                    Intent intent = new Intent(mcontext, ActivityChat.class );
                intent.putExtra("name", Namechat);
                    mcontext.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {

        return chats.size();
    }

    public class FragmentViewHorder extends RecyclerView.ViewHolder{
        TextView namechat;
        TextView timechat;
        TextView ndchat;
        public FragmentViewHorder(View itemView) {
            super(itemView);
            namechat = (TextView) itemView.findViewById(R.id.tvnamechat);
            timechat = (TextView) itemView.findViewById(R.id.timechat);
            ndchat = (TextView) itemView.findViewById(R.id.tvndchat);
        }
    }
}

