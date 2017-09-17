package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7;

import android.support.v7.widget.AppCompatEditText;

import java.util.List;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyCompacResultHelper {
    public static RxVerifyCompacResult<AppCompatEditText> getFirstBadResultOrSuccess(
            List<RxVerifyCompacResult<AppCompatEditText>> results) {
        RxVerifyCompacResult<AppCompatEditText> firstBadResult = null;
        for (RxVerifyCompacResult<AppCompatEditText> result : results) {
            if (!result.isProper()) {
                firstBadResult = result;
                break;
            }
        }
        if (firstBadResult == null) {
            // if there is no bad result, then return first success
            return results.get(0);
        } else {
            return firstBadResult;
        }
    }
}
