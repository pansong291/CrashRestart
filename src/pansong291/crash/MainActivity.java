package pansong291.crash;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Zactivity implements OnClickListener {
private TextView hello;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hello = (TextView) findViewById(R.id.textview_hello);
		hello.setOnClickListener(this);
		hello.setText(ErrorActivity.class.getName());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textview_hello:
			Intent intent = new Intent(this, ThrowErrorActivity.class);
			startActivity(intent);
			this.finish();
			break;

		default:
			break;
		}
	}

}
