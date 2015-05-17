package com.warship.helper.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

/**
 * @author qinbi@wandoujia.com (Bi Qin)
 */
public class WshApp extends Application {

  private static Context context;
  private static LayoutInflater inflater;


  @Override
  public void onCreate() {
    context = this;
    super.onCreate();
  }

  public static Context getAppContext() {
    return context;
  }

  public static Resources getAppResources() {
    return context.getResources();
  }

  public synchronized static LayoutInflater getInflater() {
    if (inflater == null) {
      inflater = LayoutInflater.from(context);
    }
    return inflater;
  }
}
