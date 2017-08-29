package com.soussidev.kotlin.rx_java2_lib

import android.content.Context
import android.content.SharedPreferences

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer

/**
 * Created by Soussi on 27/08/2017.
 */

class RxSharedPreferences_kotlin
//region init

(private val sharedPreferences: SharedPreferences) {

    constructor(context: Context) : this(context.getSharedPreferences(KEY, Context.MODE_PRIVATE)) {}

    //endregion

    //region getters

    fun getBoolean(key: String, defaultValue: Boolean): Observable<Boolean> {
        return Observable.create { e ->
            e.onNext(sharedPreferences.getBoolean(key, defaultValue))
            e.onComplete()
        }
    }

    fun getString(key: String, defaultValue: String): Observable<String> {
        return Observable.create { e ->
            e.onNext(sharedPreferences.getString(key, defaultValue))
            e.onComplete()
        }
    }

    fun getString(key: String, defaultValue: Set<String>): Observable<Set<String>> {
        return Observable.create { e ->
            e.onNext(sharedPreferences.getStringSet(key, defaultValue))
            e.onComplete()
        }
    }

    fun getFloat(key: String, defaultValue: Float): Observable<Float> {
        return Observable.create { e ->
            e.onNext(sharedPreferences.getFloat(key, defaultValue))
            e.onComplete()
        }
    }

    fun getLong(key: String, defaultValue: Long): Observable<Long> {
        return Observable.create { e ->
            e.onNext(sharedPreferences.getLong(key, defaultValue))
            e.onComplete()
        }
    }

    fun getInt(key: String, defaultValue: Int): Observable<Int> {
        return Observable.create { e ->
            e.onNext(sharedPreferences.getInt(key, defaultValue))
            e.onComplete()
        }
    }

    val all: Observable<Map<String, *>>
        get() = Observable.create { e ->
            e.onNext(sharedPreferences.all)
            e.onComplete()
        }

    //endregion

    //region setters

    fun putString(key: String, value: String): Observable<String> {
        return Observable.just(value)
                .doOnNext { v -> sharedPreferences.edit().putString(key, v).apply() }
    }

    fun putBoolean(key: String, value: Boolean?): Observable<Boolean> {
        return Observable.just(value!!)
                .doOnNext(object : Consumer<Boolean> {
                    override fun accept(t: Boolean) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    @Throws(Exception::class)
                    fun accept(@NonNull v: Boolean?) {
                        sharedPreferences.edit().putBoolean(key, v!!).apply()
                    }
                })
    }

    fun putStringSet(key: String, value: Set<String>): Observable<Set<String>> {
        return Observable.just(value)
                .doOnNext { v -> sharedPreferences.edit().putStringSet(key, v).apply() }
    }

    fun putLong(key: String, value: Long?): Observable<Long> {
        return Observable.just(value!!)
                .doOnNext(object : Consumer<Long> {
                    override fun accept(t: Long) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    @Throws(Exception::class)
                    fun accept(@NonNull v: Long?) {
                        sharedPreferences.edit().putLong(key, v!!).apply()
                    }
                })
    }

    fun putFloat(key: String, value: Float?): Observable<Float> {
        return Observable.just(value!!)
                .doOnNext(object : Consumer<Float> {
                    override fun accept(t: Float) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    @Throws(Exception::class)
                    fun accept(@NonNull v: Float?) {
                        sharedPreferences.edit().putFloat(key, v!!).apply()
                    }
                })
    }

    fun putInteger(key: String, value: Int?): Observable<Int> {
        return Observable.just(value!!)
                .doOnNext(object : Consumer<Int> {
                    override fun accept(t: Int) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    @Throws(Exception::class)
                    fun accept(@NonNull v: Int?) {
                        sharedPreferences.edit().putInt(key, v!!).apply()
                    }
                })
    }

    companion object {

        private val KEY = "RxSharedPreferences_kotlin"

        fun with(context: Context): RxSharedPreferences_kotlin {
            return RxSharedPreferences_kotlin(context)
        }

        fun with(sharedPreferences: SharedPreferences): RxSharedPreferences_kotlin {
            return RxSharedPreferences_kotlin(sharedPreferences)
        }
    }

    //endregion
}
