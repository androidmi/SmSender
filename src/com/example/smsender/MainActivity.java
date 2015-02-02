package com.example.smsender;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony_c.HexDump;
import android.util.Log;
import android.util_c.SmsConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.internal.telephony_c.gsm.SmsMessage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{
    EditText mNumber;
    EditText mBody;
    private static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        findViewById(R.id.send).setOnClickListener(this);
        mNumber = (EditText) findViewById(R.id.number);
        mBody = (EditText) findViewById(R.id.body);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        String body = mBody.getText().toString();
        String number = mNumber.getText().toString();
        byte[][] data = SmsEncaps.encaps(number, body);
        Intent intent = new Intent("android.provider.Telephony.SMS_RECEIVED");
        intent.putExtra("pdus", data);
        intent.putExtra("format", SmsConstants.FORMAT_3GPP);
//        startService(intent);
        sendBroadcast(intent);
    }
}
