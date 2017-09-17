package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import android.text.TextUtils;
import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyLength implements Verify<EditText> {
    private static final String DEFAULT_MESSAGE = "Bad length";

    private String lengthMessage;
    private int properLength;

    public VerifyLength(int properLength) {
        this(properLength, DEFAULT_MESSAGE);
    }

    public VerifyLength(int properLength, String lengthMessage) {
        this.lengthMessage = lengthMessage;
        this.properLength = properLength;
    }

    @Override public Observable<RxVerifyResult<EditText>> verify(String text, EditText item) {
        if (TextUtils.isEmpty(text)) {
            return Observable.just(RxVerifyResult.createImproper(item, lengthMessage));
        }

        if (text.trim().length() == properLength) {
            return Observable.just(RxVerifyResult.createSuccess(item));
        }

        return Observable.just(RxVerifyResult.createImproper(item, lengthMessage));
    }
}

