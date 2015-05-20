package com.warship.helper.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warship.helper.R;
import com.warship.helper.util.Const;
import com.warship.helper.util.UrlUtils;

/**
 * @author qinbi@wandoujia.com (Bi Qin)
 */
public class LevelDetailFragment extends BaseWshFragment {

  private int chapter;
  private int section;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Bundle arguments = getArguments();
    chapter = arguments.getInt(Const.Param.KEY_LEVEL_CHAPTER);
    section = arguments.getInt(Const.Param.KEY_LEVEL_SECTION);
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View contentView = inflater.inflate(R.layout.fragment_level_detail, container, false);
    initViews(contentView);
    return contentView;
  }

  private void initViews(View contentView) {
    ImageView levelMapImage = (ImageView) contentView.findViewById(R.id.level_map_image);
    String url = UrlUtils.getLevelMapUrl(chapter, section);
    ImageLoader.getInstance().displayImage(url, levelMapImage);

  }
}
