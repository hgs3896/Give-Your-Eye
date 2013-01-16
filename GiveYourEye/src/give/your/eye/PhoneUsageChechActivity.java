package give.your.eye;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PhoneUsageChechActivity extends ListActivity {

	Context ct;
	int[] arr;

	ArrayAdapter<String> arrAdapter;

	public PhoneUsageChechActivity(Context ct, int[] arr) {
		this.ct = ct;
		this.arr = arr;
		String[] str = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			str[i] = String.format("%d", arr[i]);
		}

		arrAdapter = new ArrayAdapter<String>(ct,
				android.R.layout.simple_list_item_2, str);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list);
		setListAdapter(arrAdapter);

	}

}
