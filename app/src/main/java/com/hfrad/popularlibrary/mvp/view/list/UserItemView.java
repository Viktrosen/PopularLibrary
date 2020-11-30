package com.hfrad.popularlibrary.mvp.view.list;

import com.hfrad.popularlibrary.mvp.view.list.IItemView;

public interface UserItemView extends IItemView {
    void setLogin(String text);
    void loadAvatar(String url);
}
