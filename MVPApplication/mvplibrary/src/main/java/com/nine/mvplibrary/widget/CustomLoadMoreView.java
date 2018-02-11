package com.nine.mvplibrary.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.nine.mvplibrary.R;

/**
 * Created by thinkformoney on 17/10/13.
 */

public final class CustomLoadMoreView extends LoadMoreView {

    @Override public int getLayoutId() {
        return R.layout.common_load_more;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
