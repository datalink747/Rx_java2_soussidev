package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyUrlWeb extends VerifyPatternMatches {

    private static final String DEFAULT_MESSAGE = "Invalid Url";

    public VerifyUrlWeb() {
        super(DEFAULT_MESSAGE, Patterns.WEB_URL);
    }

    public VerifyUrlWeb(String invalidUrlMessage) {
        super(invalidUrlMessage, Patterns.WEB_URL);
    }

    public VerifyUrlWeb(String invalidUrlMessage, Pattern pattern) {
        super(invalidUrlMessage, pattern);
    }
}

