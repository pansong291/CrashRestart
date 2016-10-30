package pansong291.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import pansong291.network.MyRunnable;

public abstract class Proclamation
{
 private String strPackageName,
 strHttpT,strUrl,
 strProclamationMessage;
 private Long lonShowTime;
 private Context contxt;
 private boolean boolForceUpdata;
 protected MyRunnable myRube;
 private Handler handler=new Handler()
 {
  @Override
  public void handleMessage(Message msg)
  {
   if(msg.what==MyRunnable.TAG_HTTP_FINISHED)
   {
	thenDo(getAllProclamation(msg.obj));
   }
  }
 };

 public Proclamation(Context c,String u)
 {
  contxt=c;
  strPackageName=contxt.getPackageName();
  strHttpT="http";strUrl=strHttpT+"://"+u;
  boolForceUpdata=false;
  myRube=new MyRunnable(strUrl,handler,MyRunnable.HTTP_MODEL_GET_RESOURCE_CODE);
 }

 private boolean getAllProclamation(Object obj)
 {
  if(obj==null)return false;
  String s=obj.toString();
  //如果包名被修改，这里会抛出异常，相当于做了个包名验证
  s=s.substring(
   s.indexOf(strPackageName+"{")+strPackageName.length(),
   s.indexOf("}"+strPackageName)+1);
  try
  {
   JSONObject josnObj=new JSONObject(s);
   boolForceUpdata=josnObj.optBoolean("ForceUpdata");
   lonShowTime=josnObj.optLong("ShowTime");
   strProclamationMessage=josnObj.optString("ProclamationMessage");
  }catch(JSONException e)
  {
   Log.e("JSONObject","Exception",e);
  }
  return true;
 }
 
 protected abstract void thenDo(boolean success);
 
 public String getProclamationMessage()
 {
  return strProclamationMessage;
 }

 public Long getLonShowTime()
 {
  return lonShowTime;
 }

 public boolean getForceUpdata()
 {
  return boolForceUpdata;
 }
 
}
