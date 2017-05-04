package com.example.root.demobluetooth2.FragmentAdapter;

/**
 * Created by root on 29/04/2017.
 */

public class ItemChat {
    private String namechat;
    private String timechat;
    private String ndchat;


    public ItemChat(String namechat, String timechat, String ndchat) {
        this.namechat = namechat;
        this.timechat = timechat;
        this.ndchat = ndchat;
    }



    public String getNamechat() {

        return namechat;
    }

    public void setNamechat(String namechat) {

        this.namechat = namechat;
    }

    public String getTimechat() {

        return timechat;
    }

    public void setTimechat(String timechat) {

        this.timechat = timechat;
    }

    public String getNdchat() {

        return ndchat;
    }

    public void setNdchat(String ndchat) {

        this.ndchat = ndchat;
    }

}
