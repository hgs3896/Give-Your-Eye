package give.your.eye;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ScreenOnBroadCastReciever extends BroadcastReceiver {

	String TAG = "ScreenBroadCastReciever";
	Intent service;
	Bundle extras;

	@Override
	public void onReceive(Context ct, Intent intent) {

		String action = intent.getAction();

		service = new Intent(ct, WatchYourEyeService.class);
		extras = new Bundle();

		if (action.equals("android.intent.action.SCREEN_ON")) {

			Log.i(TAG, "On");
			try {
				extras.putBoolean("isScreenOn", true);
			} catch (Exception e) {
				Log.e(TAG, TAG + e.toString());
			}

		} else if (action.equals("android.intent.action.SCREEN_OFF")) {

			Log.i(TAG, "Off");

			try {
				extras.putBoolean("isScreenOn", false);
			} catch (Exception e) {
				Log.e(TAG, TAG + e.toString());
			}

		} else if (intent.getAction().equals(
				"android.intent.action.BOOT_COMPLETED")) {

			ComponentName cn = new ComponentName(ct.getPackageName(),
					WatchYourEyeService.class.getName());
			ComponentName svcName = ct.startService(new Intent()
					.setComponent(cn));

			if (svcName == null) {				
			}

		}

		service.putExtras(extras);
		ct.startService(service);

	}
}
