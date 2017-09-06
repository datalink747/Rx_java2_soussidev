package com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.network.strategy;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.PowerManager;
import android.util.Log;

import com.soussidev.kotlin.rx_java2_lib.RxConnexion.Connectivity;
import com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.network.NetworkObservingStrategy;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.subjects.PublishSubject;

import static com.soussidev.kotlin.rx_java2_lib.RxConnexion.ReactiveNetwork.LOG_TAG;

/**
 * Created by Soussi on 06/09/2017.
 */

@TargetApi(23) public class MarshmallowNetworkObservingStrategy
        implements NetworkObservingStrategy {
    protected static final String ERROR_MSG_NETWORK_CALLBACK =
            "could not unregister network callback";
    protected static final String ERROR_MSG_RECEIVER = "could not unregister receiver";

    private ConnectivityManager.NetworkCallback networkCallback;
    private PublishSubject<Connectivity> connectivitySubject = PublishSubject.create();
    private BroadcastReceiver idleReceiver;

    @Override public Observable<Connectivity> observeNetworkConnectivity(final Context context) {
        final String service = Context.CONNECTIVITY_SERVICE;
        final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(service);
        networkCallback = createNetworkCallback(context);

        registerIdleReceiver(context);

        final NetworkRequest request =
                new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
                        .build();

        manager.registerNetworkCallback(request, networkCallback);

        return connectivitySubject.toFlowable(BackpressureStrategy.LATEST).doOnCancel(new Action() {
            @Override public void run() {
                tryToUnregisterCallback(manager);
                tryToUnregisterReceiver(context);
            }
        }).startWith(Connectivity.create(context)).distinctUntilChanged().toObservable();
    }

    protected void registerIdleReceiver(final Context context) {
        final IntentFilter filter = new IntentFilter(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED);
        idleReceiver = createBroadcastReceiver();
        context.registerReceiver(idleReceiver, filter);
    }

    @NonNull
    protected BroadcastReceiver createBroadcastReceiver() {
        return new BroadcastReceiver() {
            @Override public void onReceive(final Context context, final Intent intent) {
                if (isIdleMode(context)) {
                    onNext(Connectivity.create());
                } else {
                    onNext(Connectivity.create(context));
                }
            }
        };
    }

    protected boolean isIdleMode(final Context context) {
        final String packageName = context.getPackageName();
        final PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isIgnoringOptimizations = manager.isIgnoringBatteryOptimizations(packageName);
        return manager.isDeviceIdleMode() && !isIgnoringOptimizations;
    }

    protected void tryToUnregisterCallback(final ConnectivityManager manager) {
        try {
            manager.unregisterNetworkCallback(networkCallback);
        } catch (Exception exception) {
            onError(ERROR_MSG_NETWORK_CALLBACK, exception);
        }
    }

    protected void tryToUnregisterReceiver(Context context) {
        try {
            context.unregisterReceiver(idleReceiver);
        } catch (Exception exception) {
            onError(ERROR_MSG_RECEIVER, exception);
        }
    }

    @Override public void onError(final String message, final Exception exception) {
        Log.e(LOG_TAG, message, exception);
    }

    protected ConnectivityManager.NetworkCallback createNetworkCallback(final Context context) {
        return new ConnectivityManager.NetworkCallback() {
            @Override public void onAvailable(Network network) {
                onNext(Connectivity.create(context));
            }

            @Override public void onLost(Network network) {
                onNext(Connectivity.create(context));
            }
        };
    }

    protected void onNext(Connectivity connectivity) {
        connectivitySubject.onNext(connectivity);
    }
}

