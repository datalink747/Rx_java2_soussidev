package com.soussidev.kotlin.rx_java2_lib.RxVerify_Support_7.verifyFuncCompac;

import java.util.regex.Pattern;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyEmailCompac extends VerifyPatternMatchesCompac {

    private static final String DEFAULT_MESSAGE = "Invalid Email";

    public VerifyEmailCompac() {
        super(DEFAULT_MESSAGE, android.util.Patterns.EMAIL_ADDRESS);
    }

    public VerifyEmailCompac(String invalidEmailMessage) {
        super(invalidEmailMessage, android.util.Patterns.EMAIL_ADDRESS);
    }

    public VerifyEmailCompac(String invalidEmailMessage, Pattern pattern) {
        super(invalidEmailMessage, pattern);
    }
}

