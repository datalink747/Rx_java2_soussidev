package com.soussidev.kotlin.rx_java2_lib.RxRecyclerview;

import io.reactivex.Observable;

/**
 * Created by Soussi on 14/09/2017.
 */

public interface BindableView<T> {

    /**
     * Event invoked when viewHolder is attached with data object
     *
     * @param item adapter item
     */
    void bindItem(T item);

    /**
     * Return observable events (cast to Object class) here if you want to handle clicks and
     * other events from viewHolder
     * Output of this stream can be observed through {@link ObservableAdapter#onItemEvent()}
     *
     * @return observable events
     */
    Observable<Object> onObservableEvent();
}
