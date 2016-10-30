package pansong291.crash;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

public class ActivityControl
{
 private static ActivityControl aycl=new ActivityControl();
 private List<Activity>acitivityList=new ArrayList<Activity>();
 
 public void addActivity(Activity activity)
 {
  acitivityList.add(activity);
 }

 public void removeActivity(Activity activity)
 {
  acitivityList.remove(activity);
 }

 public void finishProgrom()
 {
  for(Activity activity:acitivityList)
  {
   if(null!=activity)
   {
    activity.finish();
   }
  }
  android.os.Process.killProcess(android.os.Process.myPid());
 }

 public static ActivityControl getActivityControl()
 {
  return aycl;
 }
 
 private ActivityControl(){}
 
}
