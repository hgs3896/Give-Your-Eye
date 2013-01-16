package give.your.eye;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class FileOpenHelper {

	Context ct;
	final String fileName = "time.txt";

	public FileOpenHelper(Context ct) {
		this.ct = ct;
	}

	public void save(String msg) {
		try {

			FileOutputStream fos = ct.openFileOutput(fileName,
					Context.MODE_APPEND);

			byte[] strByte = msg.getBytes();

			fos.write(strByte);

			fos.close();
			// Toast.makeText(ct, "File Saved", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			// Toast.makeText(ct, "File Save Error", Toast.LENGTH_SHORT).show();

		}
	}

	public int[] open() {

		try {

			FileInputStream fis = ct.openFileInput(fileName);

			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);

			String readedStr = new String(buffer);
			String[] arr = readedStr.split(" ");

			int val[] = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				val[i] = Integer.parseInt(arr[i]);
			}
			

			// Toast.makeText(ct, readedStr, Toast.LENGTH_LONG).show();
			return val;
		} catch (IOException e) {
			Log.e("File Open Error:", e.getMessage());
		} catch (Exception e) {

		}

		return new int[]{0,0};
	}

	public void delete() {
		ct.deleteFile(fileName);
		save(0+" ");		
	}
}
