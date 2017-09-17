package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import android.text.TextUtils;
import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyNonEmpty implements Verify<EditText> {
    private static final String DEFAULT_MESSAGE = "Cannot be empty";
    private String cannotBeEmptyMessage;

    public VerifyNonEmpty() {
        cannotBeEmptyMessage = DEFAULT_MESSAGE;
    }

    public VerifyNonEmpty(String cannotBeEmptyMessage) {
        this.cannotBeEmptyMessage = cannotBeEmptyMessage;
    }

    @Override public Observable<RxVerifyResult<EditText>> verify(String text, EditText item) {
        if (isEmpty(text)) {
            return Observable.just(RxVerifyResult.createImproper(item, cannotBeEmptyMessage));
        }
        return Observable.just(RxVerifyResult.createSuccess(item));
    }

    private boolean isEmpty(String value) {
        return TextUtils.isEmpty(value) || value.trim().length() == 0;
    }
}

