package com.finalapp.Appkeep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	 @Override
	 //When this method is run, a message will come up telling the user that it has 
	 //received the alarm settings
	 public void onReceive(Context arg0, Intent arg1) {
	  Toast.makeText(arg0, "@string/received", Toast.LENGTH_LONG).show();

	 }

	}