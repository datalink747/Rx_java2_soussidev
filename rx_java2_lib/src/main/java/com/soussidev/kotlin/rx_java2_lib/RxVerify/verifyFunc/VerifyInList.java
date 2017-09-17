package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;

import java.util.List;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyInList implements Verify<EditText> {
    private final String errorMessage;
    private final List<String> properValues;

    public VerifyInList(String errorMessage, List<String> properValues) {
        this.errorMessage = errorMessage;
        this.properValues = properValues;
    }

    @Override public Observable<RxVerifyResult<EditText>> verify(String text, EditText item) {
        if (properValues != null && properValues.contains(text)) {
            return Observable.just(RxVerifyResult.createSuccess(item));
        }

        return Observable.just(RxVerifyResult.createImproper(item, errorMessage));
    }
}

