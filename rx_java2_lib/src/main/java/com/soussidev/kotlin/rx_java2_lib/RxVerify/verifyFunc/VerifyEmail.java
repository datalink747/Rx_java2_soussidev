package com.soussidev.kotlin.rx_java2_lib.RxVerify.verifyFunc;

import java.util.regex.Pattern;

/**
 * Created by Soussi on 15/09/2017.
 */

public class VerifyEmail extends VerifyPatternMatches {

    private static final String DEFAULT_MESSAGE = "Invalid Email";

    public VerifyEmail() {
        super(DEFAULT_MESSAGE, android.util.Patterns.EMAIL_ADDRESS);
    }

    public VerifyEmail(String invalidEmailMessage) {
        super(invalidEmailMessage, android.util.Patterns.EMAIL_ADDRESS);
    }

    public VerifyEmail(String invalidEmailMessage, Pattern pattern) {
        super(invalidEmailMessage, pattern);
    }
}

