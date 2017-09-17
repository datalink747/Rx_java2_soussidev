package com.soussidev.kotlin.rx_java2_lib.RxVerify;

import android.widget.EditText;

import java.util.List;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyResultHelper {
    public static RxVerifyResult<EditText> getFirstBadResultOrSuccess(
            List<RxVerifyResult<EditText>> results) {
        RxVerifyResult<EditText> firstBadResult = null;
        for (RxVerifyResult<EditText> result : results) {
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
