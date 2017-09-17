package com.soussidev.kotlin.rx_java2_lib.RxVerify;

import android.widget.TextView;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public interface Verify<T extends TextView> {
    Observable<RxVerifyResult<T>> verify(String text, T item);
}
