package com.soussidev.kotlin.rx_java2_lib.RxRecyclerview;

import android.support.annotation.LayoutRes;

import java.util.List;

/**
 * Created by Soussi on 14/09/2017.
 */

public final class SimpleAdapterSource <T> extends AdapterSource<T> {
    @LayoutRes
    private final int layout;

    /**
     * Creates simple source for {@link ObservableAdapter} with just single view type
     * Uses 'true' flag for resolving movement in DiffUtil
     * @param data   default data set
     * @param layout viewholder layout resource
     */
    public SimpleAdapterSource(List<T> data, @LayoutRes int layout) {
        this(data, layout, false);
    }

    /**
     * Creates simple source for {@link ObservableAdapter} with just single view type
     * @param data           default data set
     * @param layout         viewholder layout resource
     * @param detectMovement false to disable movement detection during updates (for better performance)
     */
    public SimpleAdapterSource(List<T> data, @LayoutRes int layout, boolean detectMovement) {
        super(data, detectMovement);
        this.layout = layout;
    }

    @Override protected int getViewType(int pos) {
        return -1;
    }

    @Override public int getLayout(int viewType) {
        return layout;
    }
}
