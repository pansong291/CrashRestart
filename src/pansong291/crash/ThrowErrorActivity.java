package pansong291.crash;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.app.Activity;

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
