package give.your.eye;

import android.util.Log;

public class TimerThread extends Thread {
	private int sec = 0;

	public void run() {
		super.run();

		while (true) {
			try {
				sec++;
				Log.i("TIME", "TIME : " + sec + "sec");
				Thread.sleep(1000);
			} catch (Exception e) {

			}
		}

	}

	public int getSeconds() {
		return sec;
	}

	public int getMinutes() {
		return sec / 60;
	}

	public int getHours() {
		return sec / 3600;
	}

	public void reset() {
		sec = 0;
	}

}
