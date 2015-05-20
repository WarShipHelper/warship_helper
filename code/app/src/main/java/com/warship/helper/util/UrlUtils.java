package com.warship.helper.util;

/**
 * @author qinbi@wandoujia.com (Bi Qin)
 */
public class UrlUtils {

  private UrlUtils() {}

  public static String getLevelMapUrl(int chapter, int section) {
    return "http://warship-helper.oss-cn-beijing.aliyuncs.com/level/" + chapter + "_" + section
        + ".png";
  }

}
