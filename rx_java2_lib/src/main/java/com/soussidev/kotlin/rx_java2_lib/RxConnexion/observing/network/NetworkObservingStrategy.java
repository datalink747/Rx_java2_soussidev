package com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.network;

import android.content.Context;

import com.soussidev.kotlin.rx_java2_lib.RxConnexion.Connectivity;

import io.reactivex.Observable;

/**
 * Created by Soussi on 06/09/2017.
 */

public interface NetworkObservingStrategy {
    Observable<Connectivity> observeNetworkConnectivity(Context context);

    void onError(String message, Exception exception);
}
