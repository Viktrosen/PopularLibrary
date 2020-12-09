package com.hfrad.popularlibrary.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface UserView extends MvpView {
    void init();
    void updateList();
    void release();
}
