package pansong291.crash;
import android.os.*;
import android.widget.*;
import android.app.*;

public class ErrorActivity extends Activity
{
 TextView merrorlogtxt;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_error);
  merrorlogtxt=(TextView)findViewById(R.id.activityerrorTextView1);
  merrorlogtxt.setText(getIntent().getStringExtra(CrashApplication.ERROR_MESSAGE_TAG));
 }

 @Override
 public void onBackPressed()
 {
  super.onBackPressed();
  System.exit(0);
 }
 
 
 
}
