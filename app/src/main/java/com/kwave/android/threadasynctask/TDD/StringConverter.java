package com.kwave.android.threadasynctask.TDD;

import android.content.Context;

/**
 * Created by kwave on 2017-06-14.
 */

public class StringConverter {
    Context mContext;

    public StringConverter(Context mContext) {
        this.mContext = mContext;
        httpConnect();
    }

    public String getString(int id){
        httpConnect();
        return mContext.getString(id);
    }

    private void httpConnect() {
        // http통신하는 코드 300줄
        mContext.getSystemService(Context.NETWORK_STATS_SERVICE);
    }

    public String serialize(boolean value){
//        return "false";
            return String.format("%b",value);
    }

}
