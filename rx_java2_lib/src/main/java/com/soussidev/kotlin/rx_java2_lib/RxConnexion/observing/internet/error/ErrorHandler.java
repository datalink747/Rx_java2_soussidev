package com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.internet.error;

/**
 * Created by Soussi on 06/09/2017.
 */

public interface ErrorHandler {
    void handleError(final Exception exception, final String message);
}
