首先导入这个jar包，也就是把它放在你工程下的libs目录里。

1.新建一个类，文件名例如MyApplication.java，然后让此类继承pansong291.crash.CrashApplication类，并实现其中的抽象方法。
可在该方法内调用setShouldShowDeviceInfo()方法来设置是否收集用户设备信息，默认为true。
可在该方法内调用setRestartTime()方法来设置在多少毫秒后重启应用，默认为500。
返回值为程序崩溃后默认跳转到的Activity类(本例中的类为ErrorActivity，注意要在AndroidManifest里声明哦)。
例如：

 @Override
 public Class<?> getPackageActivity()
 {
  // 设置500毫秒后重启应用
  setRestartTime(500);
  // 不收集用户设备信息
  setShouldShowDeviceInfo(false);
  return ErrorActivity.class;
 }


2.在AndroidManifest.xml文件里的<application>标签里添加name属性，属性值为步骤1新建的类名。
例如：

 <application
   android:name=".MyApplication"
   android:icon="@drawable/ic_launcher"
   android:label="@string/app_name" >


3.添加Activity控制器。有以下两种方式可供选择：

 方式①(推荐)：新建一个MyActivity.java文件，并继承Activity类，重写onCreate()和onDestroy()方法。
 在onCreate()里调用ASControl.getASControl().addActivity(this);
 在onDestroy()里调用ASControl.getASControl().removeActivity(this);
 最后，至关重要的一步，让所有继承了Activity类的改继承为MyActivity类。
 
 方式②：在所有的继承了Activity的类的onCreate()方法里调用ASControl.getASControl().addActivity(this);
 在所有的继承了Activity的类的onDestroy()方法里调用ASControl.getASControl().removeActivity(this);
 
4.添加Service控制器。方式参考步骤3。

5.在ErrorActivity类(步骤1提到的程序崩溃后默认跳转到的Activity)里调用this.getIntent()方法获取Intent对象，再用此Intent对象调用getStringExtra(CrashApplication.ERROR_MESSAGE_TAG)方法即可获取异常发生时间、相应log以及设备信息等。


6.试调。在某个Activity(除了步骤1提到的程序崩溃后默认跳转到的ErrorActivity)里抛出异常(如代码 int i=1/0; )，然后运行程序，看看异常是否被程序处理，若无请检查并重复以上步骤。


----------关于---------
此jar包由百度贴吧@pansong291PS提供。
如需转载、分享请保留开发者著作权。
严禁用于任何商业形式，由此产生的一切纠纷与原开发者无关。
