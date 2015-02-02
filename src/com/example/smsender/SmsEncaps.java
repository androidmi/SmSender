package com.example.smsender;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.util.Log;
import android.util_c.SmsConstants;

import com.android.internal.telephony_c.gsm.SmsMessage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by dufan on 2015/1/26.
 */
public class SmsEncaps {

    private static final String TAG = "SmsEncaps";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static byte[][] encapsDemo(String number, String body) {
        String scAddress = "+8613010114500";
        String destinationAddress = "1065510198";
        String message = "【气象局】今夜晴间多云,南转北风一二级,最低气温零下6度,相对湿度70%,明日晴间多云,北转南风二三级,最高气温3度.详查12121";
        ArrayList<String> list = SmsManager.getDefault().divideMessage(message);
        Log.i(TAG, "message length:" + list.size());
        for (String l : list) {
            Log.i(TAG, "message :"+l);
        }
        boolean statusReportRequested = false;
        Log.i(TAG, "message_1:"+message);
        SmsMessage.SubmitPdu pdu = SmsMessage.getSubmitPdu(scAddress, destinationAddress, message, statusReportRequested);
        byte[] messageByte = pdu.encodedMessage;
        ByteArrayOutputStream in = new ByteArrayOutputStream(messageByte.length);
        in.write(messageByte, 2, messageByte.length - 2);

        byte[] deliveryType = { 0x24 };
        byte[] newByte = contactArray(deliveryType, in.toByteArray());
        byte[] scts = {0x51, 0x10, 0x60, (byte) 0x91, (byte) 0x92, 0x71, 0x23};

        byte[] finalByte = contactArray(pdu.encodedScAddress, newByte);
        byte[][] m = new byte[1][];
        m[0] = finalByte;

        Intent intent = new Intent();
        intent.putExtra("pdus", m);
        intent.putExtra("format", SmsConstants.FORMAT_3GPP);
//        android.telephony.SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
//
//
//        for (android.telephony.SmsMessage sms: smsMessages) {
//            String body = sms.getMessageBody();
//            String originAddress = sms.getOriginatingAddress();
//            Log.i(TAG, originAddress + ":" + body);
//            if (message.equals(body)) {
//                Log.i(TAG, "ok");
//            }
//        }
        return m;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static byte[][] encaps(String destinationAddress, String message) {
        String scAddress = "+8613010114500";
        ArrayList<String> list = SmsManager.getDefault().divideMessage(message);
        int len = list.size();
        byte[][] m = new byte[list.size()][];
        for (int i = 0; i < len; i++) {
            boolean statusReportRequested = false;
            SmsMessage.SubmitPdu pdu = SmsMessage.getSubmitPdu(scAddress, destinationAddress, message, statusReportRequested);
            byte[] messageByte = pdu.encodedMessage;
            ByteArrayOutputStream in = new ByteArrayOutputStream(messageByte.length);
            in.write(messageByte, 2, messageByte.length - 2);

            byte[] deliveryType = { 0x24 };
            byte[] newByte = contactArray(deliveryType, in.toByteArray());
            byte[] scts = {0x51, 0x10, 0x60, (byte) 0x91, (byte) 0x92, 0x71, 0x23};

            byte[] finalByte = contactArray(pdu.encodedScAddress, newByte);
            m[0] = finalByte;
        }
        return m;
    }

    private static byte[] contactArray(byte[] mscLen, byte[] msNumber) {
        byte[] newData = new byte[mscLen.length + msNumber.length];
        System.arraycopy(mscLen, 0, newData, 0, mscLen.length);
        System.arraycopy(msNumber, 0, newData, mscLen.length, msNumber.length);
        return newData;
    }

}
