package com.soussidev.kotlin.rx_java2_lib

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.annotations.NonNull
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Created by Soussi on 27/08/2017.
 */

class RxBus_kotlin {

    private val bus = PublishSubject.create<Any>().toSerialized()

    fun allEvents(): Observable<Any> {
        return bus
    }

    fun onEvent(eventName: String): Observable<String> {
        return bus
                .filter { anObject -> eventName == anObject }
                .cast(String::class.java)
    }

    protected fun <CLASS> onEvent(theClass: Class<CLASS>): Observable<CLASS> {
        return bus
                .filter { o -> theClass.isInstance(o) || theClass == o }
                .cast(theClass)
    }

    fun post(event: Any) {
        bus.onNext(event)
    }

    private fun postAsObservable(event: Any): Observable<Any> {
        return Observable.create { e ->
            post(event)

            val _useless = Any()
            e.onNext(_useless)
            e.onComplete()
        }
    }

    operator fun <CLASS> get(theClass: Class<CLASS>): Observable<CLASS> {
        return Observable.zip(
                onEvent(theClass),
                postAsObservable(AskedEvent(theClass)),
                BiFunction { neededObject, _useless -> neededObject })
    }

    fun <CLASS> onGet(theClass: Class<CLASS>): Observable<Getter<CLASS>> {
        return onEvent(AskedEvent::class.java)//I wait for an event (askevent) of CLASS
                .filter { askedEvent -> askedEvent.askedObject == theClass }
                .map {
                    object : Getter<CLASS> {
                        //then I send to the listener a Getter (interface)
                        //when the getter is notified, the value is sent to the first subscrier
                        //who called the method `get`
                        override operator fun get(value: CLASS) {
                            post(value!!) //the value is published on the bus
                        }
                    }
                }
    }

    interface Getter<T> {
        operator fun get(value: T)
    }

    private inner class AskedEvent(val askedObject: Any)

    companion object {

        val default = RxBus_kotlin()
    }
}
