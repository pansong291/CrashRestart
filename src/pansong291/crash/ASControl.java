package pansong291.crash;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.Service;

public class ASControl
{
 private static ASControl ascl=new ASControl();
 private List<Activity>acitivityList=new ArrayList<Activity>();
 private List<Service>serviceList=new ArrayList<Service>();
 
 public void addActivity(Activity activity)
 {
  acitivityList.add(activity);
 }

 public void removeActivity(Activity activity)
 {
  acitivityList.remove(activity);
 }

 public void addService(Service service)
 {
  serviceList.add(service);
 }

 public void removeService(Service service)
 {
  serviceList.remove(service);
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
  for(Service service:serviceList)
  {
   if(null!=service)
   {
    service.stopSelf();
   }
  }
  android.os.Process.killProcess(android.os.Process.myPid());
 }

 public static ASControl getASControl()
 {
  return ascl;
 }
 
 private ASControl(){}
 
}
