package com.soussidev.kotlin.rx_java2_lib.RxConnexion;

import android.net.NetworkInfo;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by Soussi on 06/09/2017.
 */

public class ConnectivityPredicate {

    private ConnectivityPredicate() {
    }

    /**
     * Filter, which returns true if at least one given state occurred
     *
     * @param states NetworkInfo.State, which can have one or more states
     * @return true if at least one given state occurred
     */
    public static Predicate<Connectivity> hasState(final NetworkInfo.State... states) {
        return new Predicate<Connectivity>() {
            @Override public boolean test(@NonNull Connectivity connectivity) throws Exception {
                for (NetworkInfo.State state : states) {
                    if (connectivity.getState() == state) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * Filter, which returns true if at least one given type occurred
     *
     * @param types int, which can have one or more types
     * @return true if at least one given type occurred
     */
    public static Predicate<Connectivity> hasType(final int... types) {
        final int[] extendedTypes = appendUnknownNetworkTypeToTypes(types);
        return new Predicate<Connectivity>() {
            @Override public boolean test(@NonNull Connectivity connectivity) throws Exception {
                for (int type : extendedTypes) {
                    if (connectivity.getType() == type) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * Returns network types from the input with additional unknown type,
     * what helps during connections filtering when device
     * is being disconnected from a specific network
     *
     * @param types of the network as an array of ints
     * @return types of the network with unknown type as an array of ints
     */
    protected static int[] appendUnknownNetworkTypeToTypes(int[] types) {
        int i = 0;
        final int[] extendedTypes = new int[types.length + 1];
        for (int type : types) {
            extendedTypes[i] = type;
            i++;
        }
        extendedTypes[i] = Connectivity.UNKNOWN_TYPE;
        return extendedTypes;
    }
}

