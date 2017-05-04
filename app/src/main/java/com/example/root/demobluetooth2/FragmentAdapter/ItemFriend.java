package com.example.root.demobluetooth2.FragmentAdapter;

/**
 * Created by root on 29/04/2017.
 */

public class ItemFriend {

    private String namefriend;
    private String ndfriend;

    public ItemFriend(String namefriend, String ndfriend) {
        this.namefriend = namefriend;
        this.ndfriend = ndfriend;
    }

    public String getNamefriend() {
        return namefriend;
    }

    public void setNamefriend(String namefriend) {
        this.namefriend = namefriend;
    }

    public String getNdfriend() {
        return ndfriend;
    }

    public void setNdfriend(String ndfriend) {
        this.ndfriend = ndfriend;
    }
}
