package pansong291.crash;
import android.app.*;

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
