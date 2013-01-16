package give.your.eye;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class WatchYourEyeService extends Service {

	ScreenOnBroadCastReciever screenreciever;
	String tag = "Watchyoureyesservice";
	TimerThread t;
	FileOpenHelper f;

	int sec = 0;

	public void onCreate() {

		super.onCreate();
		Toast.makeText(getBaseContext(),
				getApplicationContext().getString(R.string.create),
				Toast.LENGTH_SHORT).show();

		screenreciever = new ScreenOnBroadCastReciever();

		t = new TimerThread();
		t.start();

		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(screenreciever, filter);
		f = new FileOpenHelper(getBaseContext());
	}

	public void onStart(Intent i, int startId) {
		super.onStart(i, startId);
		printLogs(i);
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(screenreciever);
		t = null;
		Toast.makeText(getBaseContext(),
				getApplicationContext().getString(R.string.destroy),
				Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	private void printLogs(Intent i) {

		try {

			boolean flag = i.getExtras().getBoolean("isScreenOn");

			if (flag) {

				Toast.makeText(
						getBaseContext(),
						getApplicationContext().getString(R.string.timeleft)
								+ " : " + sec + "sec", Toast.LENGTH_SHORT)
						.show();
				t.reset();

				if (t == null)
					t.start();

			} else {

				Log.d(tag, "Elapsed TIME : " + t.getSeconds() + "Sec");
				sec = t.getSeconds();
				f.save(sec + " ");
				if (sec >= 600) {
					Toast.makeText(
							getApplicationContext(),
							getApplication().getResources().getString(
									R.string.description), Toast.LENGTH_LONG)
							.show();

					LinearLayout ll = new LinearLayout(getApplicationContext());

					ImageView iv1 = new ImageView(getApplicationContext());
					ImageView iv2 = new ImageView(getApplicationContext());
					ImageView iv3 = new ImageView(getApplicationContext());
					ImageView iv4 = new ImageView(getApplicationContext());

					iv1.setImageResource(R.drawable.img1);
					iv2.setImageResource(R.drawable.img2);
					iv3.setImageResource(R.drawable.img3);
					iv4.setImageResource(R.drawable.img4);

					ll.addView(iv1, 0);
					ll.addView(iv2, 1);
					ll.addView(iv3, 2);
					ll.addView(iv4, 3);

					Toast t = new Toast(getApplicationContext());
					t.setView(ll);
					t.setDuration(Toast.LENGTH_LONG);
					t.show();
				}

			}

		} catch (Exception e) {
			Log.e(tag, "ERR" + e.toString());

		}

	}
}