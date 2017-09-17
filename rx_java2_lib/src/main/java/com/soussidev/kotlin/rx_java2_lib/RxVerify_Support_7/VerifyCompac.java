package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7;

import android.widget.TextView;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public interface VerifyCompac<T extends TextView> {
    Observable<RxVerifyCompacResult<T>> verify(String text, T item);
}
