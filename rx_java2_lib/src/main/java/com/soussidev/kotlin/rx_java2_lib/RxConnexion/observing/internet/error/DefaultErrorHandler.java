package com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.internet.error;

import android.util.Log;

import static com.soussidev.kotlin.rx_java2_lib.RxConnexion.ReactiveNetwork.LOG_TAG;


/**
 * Created by Soussi on 06/09/2017.
 */

public class DefaultErrorHandler implements ErrorHandler {
    @Override public void handleError(final Exception exception, final String message) {
        Log.e(LOG_TAG, message, exception);
    }
}
