package com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.network.strategy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Looper;
import android.util.Log;

import com.soussidev.kotlin.rx_java2_lib.RxConnexion.Connectivity;
import com.soussidev.kotlin.rx_java2_lib.RxConnexion.observing.network.NetworkObservingStrategy;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;

import static com.soussidev.kotlin.rx_java2_lib.RxConnexion.ReactiveNetwork.LOG_TAG;

/**
 * Created by Soussi on 06/09/2017.
 */

public class PreLollipopNetworkObservingStrategy implements NetworkObservingStrategy {

    @Override public Observable<Connectivity> observeNetworkConnectivity(final Context context) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        return Observable.create(new ObservableOnSubscribe<Connectivity>() {
            @Override public void subscribe(final ObservableEmitter<Connectivity> emitter)
                    throws Exception {
                final BroadcastReceiver receiver = new BroadcastReceiver() {
                    @Override public void onReceive(Context context, Intent intent) {
                        emitter.onNext(Connectivity.create(context));
                    }
                };

                context.registerReceiver(receiver, filter);

                Disposable disposable = disposeInUiThread(new Action() {
                    @Override public void run() {
                        tryToUnregisterReceiver(context, receiver);
                    }
                });
                emitter.setDisposable(disposable);
            }
        }).defaultIfEmpty(Connectivity.create());
    }

    protected void tryToUnregisterReceiver(final Context context, final BroadcastReceiver receiver) {
        try {
            context.unregisterReceiver(receiver);
        } catch (Exception exception) {
            onError("receiver was already unregistered", exception);
        }
    }

    @Override public void onError(final String message, final Exception exception) {
        Log.e(LOG_TAG, message, exception);
    }

    private Disposable disposeInUiThread(final Action action) {
        return Disposables.fromAction(new Action() {
            @Override public void run() throws Exception {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    action.run();
                } else {
                    final Scheduler.Worker inner = AndroidSchedulers.mainThread().createWorker();
                    inner.schedule(new Runnable() {
                        @Override public void run() {
                            try {
                                action.run();
                            } catch (Exception e) {
                                onError("Could not unregister receiver in UI Thread", e);
                            }
                            inner.dispose();
                        }
                    });
                }
            }
        });
    }
}

