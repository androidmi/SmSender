package com.example.smsender;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;

public class MmsReceiver extends BroadcastReceiver {
    public MmsReceiver() {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Telephony.Sms.Intents.getMessagesFromIntent(intent);
    }
}
