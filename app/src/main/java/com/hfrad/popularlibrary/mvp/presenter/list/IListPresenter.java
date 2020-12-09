package com.hfrad.popularlibrary.mvp.presenter.list;

import com.hfrad.popularlibrary.mvp.view.list.IItemView;

public interface IListPresenter<V extends IItemView> {
    void onItemClick(V view);
    void bindView(V view);
    int getCount();
}
