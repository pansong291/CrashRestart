package pansong291.crash.ui;
import pansong291.crash.CrashApplication;

public class MyApplication extends CrashApplication
{
 @Override
 public Class<?> getPackageActivity()
 {
  setRestartTime(0);
  setShouldShowDeviceInfo(false);
  return ErrorActivity.class;
 }

}
