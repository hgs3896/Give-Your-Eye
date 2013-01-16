package give.your.eye;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class GiveYourEyeActivity extends Activity {

	ComponentName mWatchYourEye;
	String tag = "GiveyourEye";

	Button btn;
	ToggleButton btn2;

	FileOpenHelper f;

	LinearLayout ll;
	static int count = 0;

	static GraphView gv;
	private HorizontalScrollView sv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ll = (LinearLayout) findViewById(R.id.ll);
		gv = new GraphView(this, 220, 220);
		sv = new HorizontalScrollView(this);
		sv.addView(gv);
		ll.addView(sv, 2);

		f = new FileOpenHelper(this);
		btn = (Button) findViewById(R.id.delete);
		btn2 = (ToggleButton) findViewById(R.id.button1);

		btn2.setChecked(true);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				f.delete();
				gv.invalidate();
			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (btn2.isChecked()) {
					startWatchYourEyeService();
				} else {
					stopWatchYourEyeService();
				}
			}
		});

		startWatchYourEyeService();
	}

	private void startWatchYourEyeService() {
		Log.i(tag, "start_service");
		Intent i = new Intent(this, WatchYourEyeService.class);
		mWatchYourEye = startService(i);

	}

	private void stopWatchYourEyeService() {
		if (mWatchYourEye == null) {
			Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
			return;
		}
		Log.i(tag, "stop_service");

		Intent i = new Intent();
		i.setComponent(mWatchYourEye);

		if (stopService(i))
			Toast.makeText(this, getString(R.string.destroy),
					Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, getString(R.string.already_stopped),
					Toast.LENGTH_SHORT).show();
	}

	public void onDestroy() {

		super.onDestroy();

	}

}