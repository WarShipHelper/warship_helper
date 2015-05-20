package com.warship.helper.util;

/**
 * @author qinbi@wandoujia.com (Bi Qin)
 */
public class Const {

  public static class Config {
    public static final int MEM_CACHE_SIZE = 8 * 1024 * 1024;
    public static final int DISK_CACHE_SIZE = 64 * 1024 * 1024;
  }

  public static class Action {
    public static final String ACTION_APPLICATION_EXIT =
        "com.warship.helper.action.NOTIFICATION_EXIT_CLICK";
  }

  public static final class Navigation {
    public static final String KEY_ADD_TO_BACK_STACK = "add2BackStack";
    public static final String KEY_NAV_MODE = "navMode";
    public static final String KEY_LAUNCH_SOURCE = "launchSource";
  }

  public static final class Param {
    public static final String KEY_LEVEL_CHAPTER = "key_level_chapter";
    public static final String KEY_LEVEL_SECTION = "key_level_section";
  }
}
