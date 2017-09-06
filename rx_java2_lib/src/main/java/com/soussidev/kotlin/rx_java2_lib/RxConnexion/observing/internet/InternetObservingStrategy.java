package com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.internet;

import com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.internet.error.ErrorHandler;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Soussi on 06/09/2017.
 */

public interface InternetObservingStrategy {
    String getDefaultPingHost();

    Observable<Boolean> observeInternetConnectivity(int initialIntervalInMs,
                                                    int intervalInMs, String host, int port, int timeoutInMs,
                                                    ErrorHandler errorHandler);

    Single<Boolean> checkInternetConnectivity(String host, int port,
                                              int timeoutInMs, ErrorHandler errorHandler);
}
