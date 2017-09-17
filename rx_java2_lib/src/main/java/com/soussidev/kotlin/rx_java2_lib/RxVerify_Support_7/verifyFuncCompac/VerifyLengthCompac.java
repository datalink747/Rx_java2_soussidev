package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;

import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.RxVerifyCompacResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.VerifyCompac;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyLengthCompac implements VerifyCompac<AppCompatEditText> {
    private static final String DEFAULT_MESSAGE = "Bad length";

    private String lengthMessage;
    private int properLength;

    public VerifyLengthCompac(int properLength) {
        this(properLength, DEFAULT_MESSAGE);
    }

    public VerifyLengthCompac(int properLength, String lengthMessage) {
        this.lengthMessage = lengthMessage;
        this.properLength = properLength;
    }

    @Override public Observable<RxVerifyCompacResult<AppCompatEditText>> verify(String text, AppCompatEditText item) {
        if (TextUtils.isEmpty(text)) {
            return Observable.just(RxVerifyCompacResult.createImproper(item, lengthMessage));
        }

        if (text.trim().length() == properLength) {
            return Observable.just(RxVerifyCompacResult.createSuccess(item));
        }

        return Observable.just(RxVerifyCompacResult.createImproper(item, lengthMessage));
    }
}

