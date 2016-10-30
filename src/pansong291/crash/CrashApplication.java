package pansong291.crash;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import java.io.*;
import java.lang.reflect.*;
import java.text.*;
import java.util.*;
import android.util.*;

public abstract class CrashApplication extends
Application implements
Thread.UncaughtExceptionHandler
{
 public static final String ERROR_MESSAGE_TAG="error";
 private String PACKAGENAME,ACTIVITY;
 private int restartTime=500;
 private boolean showDeviceInfo=true;
 
 public abstract Class<?> getPackageActivity();
 
 public void setRestartTime(int i)
 {
  restartTime=i;
 }
 
 public void setShouldShowDeviceInfo(boolean b)
 {
  showDeviceInfo=b;
 }
 
 @Override
 public void onCreate()
 {
  super.onCreate();
  ACTIVITY=getPackageActivity().getName();
  PACKAGENAME=getPackageName();
  Thread.setDefaultUncaughtExceptionHandler(this);
 }

 @Override
 public void uncaughtException(Thread thread,Throwable ex)
 {
  String message="";
  DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");    
  message=formatter.format(new Date())+"\n"+getLogs(ex);
  if(showDeviceInfo)
   message+=collectDeviceInfo(getApplicationContext());
  Intent intent=new Intent();
  intent.setClassName(PACKAGENAME,ACTIVITY);
  intent.putExtra(ERROR_MESSAGE_TAG,message);
  PendingIntent pendingIntent=PendingIntent.getActivity(
   getApplicationContext(),0,intent,Intent.FLAG_ACTIVITY_NEW_TASK);
  AlarmManager mAlarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
  mAlarmManager.set(AlarmManager.RTC,System.currentTimeMillis()+restartTime,
   pendingIntent);
  ActivityControl.getActivityControl().finishProgrom();
 }
 
 public String getLogs(Throwable ex)
 {
  String logs="";
  ByteArrayOutputStream baos=null;
  PrintStream printStream=null;
  try{
   baos=new ByteArrayOutputStream();
   printStream=new PrintStream(baos);
   ex.printStackTrace(printStream);
   byte[]data=baos.toByteArray();
   logs=new String(data);
   Log.e(PACKAGENAME,logs);
   logs="-----Logs-----\n"+logs;
   data=null;
  }catch(Exception e)
  {
   e.printStackTrace();
  }finally
  {
   try
   {
	if(printStream!=null)
	{
	 printStream.close();
	}
	if(baos!=null)
	{
	 baos.close();
	}
   }catch(Exception e)
   {
	e.printStackTrace();
   }
  }
  return logs;
 }
 
 //收集设备参数信息
 public String collectDeviceInfo(Context ctx)
 {
  StringBuilder infos=new StringBuilder("-----DeviceInfos-----\n");
  try
  {
   PackageManager pm=ctx.getPackageManager();
   PackageInfo pi=pm.getPackageInfo(ctx.getPackageName(),PackageManager.GET_ACTIVITIES);
   if(pi!=null)
   {
	String versionName=pi.versionName==null?"null":pi.versionName;
	String versionCode=pi.versionCode+"";
	infos.append("versionName="+versionName+"\n");
	infos.append("versionCode="+versionCode+"\n");
   }
  }catch(Exception e){}
  Field[]fields=Build.class.getDeclaredFields();
  for(Field field:fields)
  {
   try{
	field.setAccessible(true);
	infos.append(field.getName()+"="+field.get(null).toString()+"\n");
   }catch(Exception e){}
  }
  return infos.toString();
 }
 
}
