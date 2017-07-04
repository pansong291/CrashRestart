package pansong291.network;

import android.os.Handler;
import android.os.Message;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class MyRunnable implements Runnable
{
 String strUrl;
 Handler handler;
 int intHttpModel;
 public static final int HTTP_MODEL_GET_RESOURCE_CODE=0;
 Message msg=new Message();
 public static final int TAG_HTTP_FINISHED=1;
 
 public MyRunnable(String u,Handler h,int m)
 {
  strUrl=u;handler=h;intHttpModel=m;
 }
 
 @Override
 public void run()
 {
  // TODO: Implement this method
  switch(intHttpModel)
  {
   case HTTP_MODEL_GET_RESOURCE_CODE:
	msg.obj=getHttpResourceCode();
	break;
  }
  msg.what=TAG_HTTP_FINISHED;
  handler.sendMessage(msg);
 }
 
 private String getHttpResourceCode()
 {
  String message=null;
  try
  {
   HttpGet hg=new HttpGet(strUrl);
   //数据请求超时
   HttpConnectionParams.setConnectionTimeout(hg.getParams(),3000);
   //读取数据超时
   HttpConnectionParams.setSoTimeout(hg.getParams(),3000);
   HttpClient client=new DefaultHttpClient();
   HttpResponse response=client.execute(hg);
   message=EntityUtils.toString(response.getEntity(),"utf-8");
  }
  catch (Exception e)
  {
   
  }
  return message;
 }
 
}
