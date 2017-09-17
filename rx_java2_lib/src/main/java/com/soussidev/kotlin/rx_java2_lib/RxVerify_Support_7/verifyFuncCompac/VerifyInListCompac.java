package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import android.support.v7.widget.AppCompatEditText;
import android.widget.EditText;

import com.soussidev.kotlin.rx_java2_lib.RxVerify.RxVerifyResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify.Verify;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.RxVerifyCompacResult;
import com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.VerifyCompac;

import java.util.List;

import rx.Observable;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyInListCompac implements VerifyCompac<AppCompatEditText> {
    private final String errorMessage;
    private final List<String> properValues;

    public VerifyInListCompac(String errorMessage, List<String> properValues) {
        this.errorMessage = errorMessage;
        this.properValues = properValues;
    }

    @Override public Observable<RxVerifyCompacResult<AppCompatEditText>> verify(String text, AppCompatEditText item) {
        if (properValues != null && properValues.contains(text)) {
            return Observable.just(RxVerifyCompacResult.createSuccess(item));
        }

        return Observable.just(RxVerifyCompacResult.createImproper(item, errorMessage));
    }
}

