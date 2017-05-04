package com.example.root.demobluetooth2;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;

import com.example.root.demobluetooth2.FragmentAdapter.AdapterChat;
import com.example.root.demobluetooth2.FragmentAdapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private TabLayout tabLayout;
    private BluetoothAdapter BTAdapter ;
    private ViewPagerAdapter adapter;
    public static int REQUEST_BLUETOOTH = 1;
    private int[] tabIcons = {
            R.mipmap.chat
            ,R.mipmap.friend
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentManager manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager);
        pager.setAdapter(adapter);
        addControl();
        setupTabIcons();
        BTAdapter  = BluetoothAdapter.getDefaultAdapter();
        if (BTAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        if (!BTAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }

    }
    private void setupTabIcons() {
        for(int i = 0; i < 2; i++){
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }
    private void addControl() {
        FragmentManager manager = getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
    }
}
