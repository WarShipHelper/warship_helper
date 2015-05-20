package com.warship.helper.ui.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.warship.helper.R;
import com.warship.helper.app.WshApp;
import com.warship.helper.manager.navigation.NavigationManager;
import com.warship.helper.manager.navigation.NavigationParams;
import com.warship.helper.util.Const;

/**
 * @author qinbi@wandoujia.com (Bi Qin)
 */
public class LevelFragment extends BaseWshFragment {

  public static final int[] LEVEL_TITLE_RES_ARRS = {R.array.level1_arrs, R.array.level2_arrs
      , R.array.level3_arrs, R.array.level4_arrs, R.array.level5_arrs, R.array.level6_arrs};
  public static final int[] LEVEL_CHAPTER_IMG_IDS = {R.drawable.ic_chapter1,
      R.drawable.ic_chapter2, R.drawable.ic_chapter3, R.drawable.ic_chapter4,
      R.drawable.ic_chapter5, R.drawable.ic_chapter6};

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View contentView = inflater.inflate(R.layout.fragment_level, container, false);
    initViews(contentView);
    return contentView;
  }

  private void initViews(View contentView) {
    View backBtn = contentView.findViewById(R.id.back_btn);
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    TextView titleText = (TextView) contentView.findViewById(R.id.title_text);
    titleText.setText(R.string.level);
    ListView contentList = (ListView) contentView.findViewById(R.id.content_list);
    contentList.setAdapter(new LevelAdapter());
  }

  private static class LevelAdapter extends BaseAdapter {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_CONTENT = 1;

    private List<List<String>> levelTitles = new ArrayList<List<String>>();
    private int count;
    private List<Integer> headPositions = new ArrayList<Integer>();

    public LevelAdapter() {
      for (int levelRes : LEVEL_TITLE_RES_ARRS) {
        List<String> levelTitle = Arrays.asList(WshApp.getAppResources().getStringArray(levelRes));
        levelTitles.add(levelTitle);
      }
      count = 0;
      for (List<String> item : levelTitles) {
        headPositions.add(count);
        count = count + item.size() % 2 + item.size() / 2 + 1;
      }

    }

    @Override
    public int getCount() {
      return count;
    }

    @Override
    public Object getItem(int position) {
      // return the chapter and section.
      int size = headPositions.size();
      for (int i = 0; i < size; i++) {
        if (position >= headPositions.get(size - i - 1)) {
          int chapter = size - i;
          int section = (position - headPositions.get(chapter - 1)) * 2 - 1;
          return new Pair<Integer, Integer>(chapter, section);
        }
      }
      return new Pair<Integer, Integer>(1, 0);
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Pair<Integer, Integer> pair = (Pair<Integer, Integer>) getItem(position);
      int chapter = pair.first;
      int section = pair.second;
      if (getItemViewType(position) == TYPE_HEAD) {
        HeaderViewHolder holder;
        if (convertView == null) {
          convertView =
              WshApp.getInflater().inflate(R.layout.layout_level_header_item, parent, false);
          holder = new HeaderViewHolder();
          holder.chapterImg = (ImageView) convertView.findViewById(R.id.chapter_image);
          convertView.setTag(holder);
        } else {
          holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.chapterImg.setImageResource(LEVEL_CHAPTER_IMG_IDS[chapter - 1]);
      } else if (getItemViewType(position) == TYPE_CONTENT) {
        ContentViewHolder holder;
        if (convertView == null) {
          convertView =
              WshApp.getInflater().inflate(R.layout.layout_level_content_item, parent, false);
          holder = new ContentViewHolder();
          holder.levelText1 = (TextView) convertView.findViewById(R.id.content_text1);
          holder.levelText2 = (TextView) convertView.findViewById(R.id.content_text2);
          convertView.setTag(holder);
        } else {
          holder = (ContentViewHolder) convertView.getTag();
        }
        List<String> chapterTitles = levelTitles.get(chapter - 1);
        holder.levelText1.setText(chapterTitles.get(section - 1));
        holder.levelText1.setOnClickListener(new LevelTextClickListener(parent.getContext(),
            chapter, section));
        if (chapterTitles.size() > section) {
          holder.levelText2.setText(chapterTitles.get(section));
          holder.levelText2.setVisibility(View.VISIBLE);
          holder.levelText2.setOnClickListener(new LevelTextClickListener(parent.getContext(),
              chapter, section));
        } else {
          holder.levelText2.setVisibility(View.INVISIBLE);
        }
      }
      return convertView;
    }

    @Override
    public int getViewTypeCount() {
      return 2;
    }

    @Override
    public int getItemViewType(int position) {
      if (headPositions.contains(position)) {
        return TYPE_HEAD;
      } else {
        return TYPE_CONTENT;
      }
    }
  }

  private static class HeaderViewHolder {
    ImageView chapterImg;
  }

  private static class ContentViewHolder {
    TextView levelText1;
    TextView levelText2;
  }

  private static class LevelTextClickListener implements View.OnClickListener {

    private int chapter;
    private int section;
    private Context context;

    public LevelTextClickListener(Context context, int chapter, int section) {
      this.context = context;
      this.chapter = chapter;
      this.section = section;
    }

    @Override
    public void onClick(View v) {
      Bundle bundle = new Bundle();
      bundle.putInt(Const.Param.KEY_LEVEL_CHAPTER, chapter);
      bundle.putInt(Const.Param.KEY_LEVEL_SECTION, section);
      NavigationParams params = NavigationParams.newBuilder().setExtra(bundle).build();
      NavigationManager.navigateTo(context, NavigationManager.PageName.LEVEL_DETAIL, params);
    }
  }
}
