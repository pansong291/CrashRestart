package pansong291.crash;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class Zactivity extends Activity
{

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  ActivityControl.getActivityControl().addActivity(this);
 }

 @Override
 protected void onDestroy()
 {
  super.onDestroy();
  ActivityControl.getActivityControl().removeActivity(this);
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
