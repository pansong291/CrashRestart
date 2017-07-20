package pansong291.crash.ui;

import android.os.Bundle;
import android.widget.TextView;
import pansong291.crash.CrashApplication;

public class ThrowErrorActivity extends Zactivity
{
 private TextView error;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_throwerror);
		CrashApplication application = (CrashApplication) this.getApplication();
		String packageName = application.getPackageName();
		System.out.println("packageName==="+packageName);
		error = (TextView) findViewById(R.id.error_textview);
	}

}
