package pansong291.network;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Update
{
 private String strPackageName,strOldVersion,
 strHttpT,strUrl;
 private int intOldVersionCode;
 private String strUpdateMessage,
 strNewVersion,strFileSize,strDownloadUrl;
 private int intNewVersionCode;
 private Context contxt;
 private boolean boolHasNew;
 protected MyRunnable myRube;
 private Handler handler=new Handler()
 {
  @Override
  public void handleMessage(Message msg)
  {
   if(msg.what==MyRunnable.TAG_HTTP_FINISHED)
   {
	thenDo(getAllUpdate(msg.obj));
   }
  }
 };

 public Update(Context c,String u)
 {
  contxt=c;
  setCurrentVersion(contxt);
  strPackageName=contxt.getPackageName();
  strHttpT="http";strUrl=strHttpT+"://"+u;
  boolHasNew=true;
  myRube=new MyRunnable(strUrl,handler,MyRunnable.HTTP_MODEL_GET_RESOURCE_CODE);
 }
 
 private boolean getAllUpdate(Object obj)
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
   intNewVersionCode=josnObj.optInt("VersCode");
   strNewVersion=josnObj.optString("VersName");
   strUpdateMessage=josnObj.optString("NewMessage");
   strFileSize=josnObj.optString("FileSize");
   strDownloadUrl=josnObj.optString("DownUrl");
   boolHasNew=intNewVersionCode>intOldVersionCode;
  }catch(JSONException e)
  {
   Log.e("JSONObject","Exception",e);
  }
  return true;
 }

 protected abstract void thenDo(boolean success);
 
 private void setCurrentVersion(Context ac)
 {
  try
  {
   // ---get the package info---
   PackageInfo pi=ac.getPackageManager()
   .getPackageInfo(ac.getPackageName(),0);
   intOldVersionCode=pi.versionCode;
   strOldVersion=pi.versionName;
  }catch(Exception e)
  {
   intOldVersionCode=99999999;
   Log.e("VersionInfo","Exception",e);
  }
 }
 
 public boolean getHasNew()
 {
  return boolHasNew;
 }

 public String getUpdateMessage()
 {
  return strUpdateMessage;
 }

 public String getNewVersion()
 {
  return strNewVersion;
 }

 public String getOldVersion()
 {
  return strOldVersion;
 }
 
 public String getFileSize()
 {
  return strFileSize;
 }

 public String getDownloadUrl()
 {
  return strDownloadUrl;
 }
 
 
 
 
}
