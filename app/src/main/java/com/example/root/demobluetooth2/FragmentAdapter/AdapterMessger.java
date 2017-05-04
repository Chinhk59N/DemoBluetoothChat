package com.example.root.demobluetooth2.FragmentAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.demobluetooth2.R;

import java.util.List;

/**
 * Created by root on 04/05/2017.
 */

public class AdapterMessger extends RecyclerView.Adapter<AdapterMessger.FragmentViewHorder> {
    private List<ItemMessger> messgers;
    private Context mcontext;
    private LayoutInflater mlayoutInflater;


    public AdapterMessger(Context context, List<ItemMessger> data) {
        mcontext = context;
        messgers = data;
        mlayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public AdapterMessger.FragmentViewHorder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mlayoutInflater.inflate(R.layout.messger, parent, false);
        return new AdapterMessger.FragmentViewHorder(itemview);
    }

    @Override
    public void onBindViewHolder(AdapterMessger.FragmentViewHorder holder, int position) {
        ItemMessger item= messgers.get(position);
        holder.messger.setText(item.getMessger());
    }

    @Override
    public int getItemCount() {
        return messgers.size();
    }

    public class FragmentViewHorder extends RecyclerView.ViewHolder{

        private TextView messger;

        public FragmentViewHorder(View itemView) {
            super(itemView);
            messger = (TextView) itemView.findViewById(R.id.messger);
        }
    }
}

