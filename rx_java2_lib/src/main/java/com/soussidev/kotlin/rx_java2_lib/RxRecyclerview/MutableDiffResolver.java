package com.soussidev.kotlin.rx_java2_lib.RxRecyclerview;

/**
 * Created by Soussi on 14/09/2017.
 */

public interface MutableDiffResolver {
    /**
     * @return hashed value, which will be cached and compared with current values once adapter
     * is notified about changes
     */
    int valueHash();
}
