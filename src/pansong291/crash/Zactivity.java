package pansong291.crash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Zactivity extends Activity
{

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  ASControl.getASControl().addActivity(this);
 }

 @Override
 protected void onDestroy()
 {
  super.onDestroy();
  ASControl.getASControl().removeActivity(this);
 }

 public void toast(String str,int i)
 {
  Toast.makeText(this,str,i).show();
 }
 public void toast(String str)
 {
  toast(str,0);
 }

}
