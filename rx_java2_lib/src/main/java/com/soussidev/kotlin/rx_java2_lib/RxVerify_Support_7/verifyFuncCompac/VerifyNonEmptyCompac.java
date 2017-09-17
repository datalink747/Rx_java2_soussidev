package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;

import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.RxVerifyCompacResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.VerifyCompac;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyNonEmptyCompac implements VerifyCompac<AppCompatEditText> {
    private static final String DEFAULT_MESSAGE = "Cannot be empty";
    private String cannotBeEmptyMessage;

    public VerifyNonEmptyCompac() {
        cannotBeEmptyMessage = DEFAULT_MESSAGE;
    }

    public VerifyNonEmptyCompac(String cannotBeEmptyMessage) {
        this.cannotBeEmptyMessage = cannotBeEmptyMessage;
    }

    @Override public Observable<RxVerifyCompacResult<AppCompatEditText>> verify(String text, AppCompatEditText item) {
        if (isEmpty(text)) {
            return Observable.just(RxVerifyCompacResult.createImproper(item, cannotBeEmptyMessage));
        }
        return Observable.just(RxVerifyCompacResult.createSuccess(item));
    }

    private boolean isEmpty(String value) {
        return TextUtils.isEmpty(value) || value.trim().length() == 0;
    }
}

