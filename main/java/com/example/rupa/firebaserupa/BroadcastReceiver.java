package com.example.rupa.firebaserupa;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Rupa Barua on 4/4/2016.
 */
public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        CharSequence text= intent.getCharSequenceExtra("message");
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
