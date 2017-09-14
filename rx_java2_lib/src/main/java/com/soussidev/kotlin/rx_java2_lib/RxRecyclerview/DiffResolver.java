package com.soussidev.kotlin.rx_java2_lib.RxRecyclerview;

import android.support.annotation.NonNull;

/**
 * Created by Soussi on 14/09/2017.
 */

public interface DiffResolver<T> {

    /**
     * Method used to determine item equality
     * Determines it viewHolder should be replaced
     * @param other new item to compare with the old one
     * @return true if objects are considered equal
     */
    boolean equalsItem(@NonNull T other);

    /**
     * Method used to determine contents equality
     * Determines if viewHolder should be updated
     * This is only checked if {@link #equalsItem(Object)} returns true
     * @param other new item to compare with the old one
     * @return true if contents of these objects are equal
     */
    boolean areContentsTheSame(@NonNull T other);
}
