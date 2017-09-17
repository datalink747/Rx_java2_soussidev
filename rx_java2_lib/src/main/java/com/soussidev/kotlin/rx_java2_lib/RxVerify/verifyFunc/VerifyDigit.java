package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import android.text.TextUtils;
import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyDigit implements Verify<EditText> {
    private static final String DEFAULT_MESSAGE = "Digits only";
    private String digitOnlyMessage;

    public VerifyDigit() {
        digitOnlyMessage = DEFAULT_MESSAGE;
    }

    public VerifyDigit(String digitOnlyMessage) {
        this.digitOnlyMessage = digitOnlyMessage;
    }

    @Override public Observable<RxVerifyResult<EditText>> verify(String text, EditText item) {
        if (TextUtils.isDigitsOnly(text)) {
            return Observable.just(RxVerifyResult.createSuccess(item));
        }
        return Observable.just(RxVerifyResult.createImproper(item, digitOnlyMessage));
    }
}
