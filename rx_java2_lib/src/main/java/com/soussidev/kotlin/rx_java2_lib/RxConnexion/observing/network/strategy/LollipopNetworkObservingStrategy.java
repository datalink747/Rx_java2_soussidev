package com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.network.strategy;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.util.Log;

import com.soussidev.kotlin.rx_java2_lib.RxConnexion.Connectivity;
import com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.network.NetworkObservingStrategy;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;

import static com.soussidev.kotlin.rx_java2_lib.RxConnexion.ReactiveNetwork.LOG_TAG;

/**
 * Created by Soussi on 06/09/2017.
 */

@TargetApi(21) public class LollipopNetworkObservingStrategy implements NetworkObservingStrategy {
    private ConnectivityManager.NetworkCallback networkCallback;

    @Override public Observable<Connectivity> observeNetworkConnectivity(final Context context) {
        final String service = Context.CONNECTIVITY_SERVICE;
        final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(service);

        return Observable.create(new ObservableOnSubscribe<Connectivity>() {
            @Override public void subscribe(ObservableEmitter<Connectivity> subscriber) throws Exception {
                networkCallback = createNetworkCallback(subscriber, context);
                final NetworkRequest networkRequest = new NetworkRequest.Builder().build();
                manager.registerNetworkCallback(networkRequest, networkCallback);
            }
        }).doOnDispose(new Action() {
            @Override public void run() {
                tryToUnregisterCallback(manager);
            }
        }).startWith(Connectivity.create(context)).distinctUntilChanged();
    }

    private void tryToUnregisterCallback(final ConnectivityManager manager) {
        try {
            manager.unregisterNetworkCallback(networkCallback);
        } catch (Exception exception) {
            onError("could not unregister network callback", exception);
        }
    }

    @Override public void onError(final String message, final Exception exception) {
        Log.e(LOG_TAG, message, exception);
    }

    private ConnectivityManager.NetworkCallback createNetworkCallback(final ObservableEmitter<Connectivity> subscriber,
                                                                      final Context context) {
        return new ConnectivityManager.NetworkCallback() {
            @Override public void onAvailable(Network network) {
                subscriber.onNext(Connectivity.create(context));
            }

            @Override public void onLost(Network network) {
                subscriber.onNext(Connectivity.create(context));
            }
        };
    }
}

