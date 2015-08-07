package com.stkj.swiperecyclerview;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class MyItem {
    private static final int MAX_COUNT = 40;
    int res;
    String title;

    public static MyItem newItem(String title) {
        MyItem mi = new MyItem();
        mi.res = R.mipmap.ic_launcher;
        mi.title = title;
        return mi;
    }

    @Override
    public int hashCode() {
        return title == null ? 17 : title.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyItem)) {
            return false;
        }
        MyItem m = (MyItem) o;
        return m.title.hashCode() == title.hashCode();
    }

    static final int[] IMAGES = new int[]{R.drawable.d0, R.drawable.d1, R.drawable.d4, R.drawable.d3, R.drawable.d5, R.drawable.d6};

    public static List<MyItem> getImageHardItems() {
        List<MyItem> items = new ArrayList<>();
        for (int i = 0; i < MAX_COUNT; i++) {
            MyItem item = new MyItem();
            item.res = IMAGES[i % IMAGES.length];
            item.title = Constant.HARD_TITLE[i];
            items.add(item);
        }
        return items;
    }

    public static List<MyItem> getHardItems() {
        List<MyItem> items = new ArrayList<>();
        for (int i = 0; i < MAX_COUNT; i++) {
            MyItem item = new MyItem();
            item.res = R.mipmap.ic_launcher;
            item.title = Constant.HARD_TITLE[i];
            items.add(item);
        }
        return items;
    }

    public static MyItem newImageItem(String s, int i) {
        MyItem item = new MyItem();
        item.res = IMAGES[i % IMAGES.length];
        item.title = s;
        return item;
    }

    static final int[] Colors = new int[]{
            Color.parseColor("#3F51B5"),
            Color.parseColor("#009688"),
            Color.parseColor("#9C27B0"),
            Color.parseColor("#CDDC39"),
            Color.parseColor("#607D8B"),
            Color.parseColor("#FF9800")};

    public static List<MyItem> getAnimatorHardItems() {
        List<MyItem> items = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            MyItem item = new MyItem();
            item.res = Colors[i % IMAGES.length];
            item.title = Constant.HARD_TITLE[i];
            items.add(item);
        }
        return items;
    }

    public static MyItem newColorItem(String s, int firstVisibleItemPosition) {
        MyItem item = new MyItem();
        item.res = Colors[firstVisibleItemPosition % Colors.length];
        item.title = s;
        return item;
    }
}