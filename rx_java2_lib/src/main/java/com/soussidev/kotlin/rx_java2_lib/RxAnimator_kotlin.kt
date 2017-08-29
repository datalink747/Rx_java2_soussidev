package com.soussidev.kotlin.rx_java2_lib

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.support.annotation.IntDef
import android.util.Log
import android.util.Property
import android.view.View

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer

import android.animation.ValueAnimator.RESTART
import android.animation.ValueAnimator.REVERSE

/**
 * Created by Soussi on 27/08/2017.
 */

object RxAnimator_kotlin {

    private val LOG = BuildConfig.DEBUG

    fun ofFloat(view: View, method: String, vararg values: Float): RxAnimatorObservable {
        return RxAnimatorObservable(view, method, values)
    }

    fun ofFloat(view: View, viewProperty: Property<*, *>, vararg values: Float): RxAnimatorObservable {
        return RxAnimatorObservable(view, viewProperty, values)
    }

    fun ofFloat(vararg values: Float): RxAnimatorObservable {
        return RxAnimatorObservable(values)
    }

    fun ofInt(view: View, method: String, vararg values: Int): RxAnimatorObservable {
        return RxAnimatorObservable(view, method, values)
    }

    fun ofInt(view: View, viewProperty: Property<*, *>, vararg values: Int): RxAnimatorObservable {
        return RxAnimatorObservable(view, viewProperty, values)
    }

    fun ofInt(vararg values: Int): RxAnimatorObservable {
        return RxAnimatorObservable(values)
    }

    enum class Event {
        START,
        END,
        CANCEL,
        REPEAT
    }

    @IntDef(RESTART.toLong(), REVERSE.toLong())
    @Retention(RetentionPolicy.SOURCE)
    annotation class RepeatMode

    class RxAnimatorObservable : Observable<Any> {

        private var view: View? = null
        private var method: String? = null
        private var viewProperty: Property<*, *>? = null
        private var valuesFloat: FloatArray? = null
        private var valuesInt: IntArray? = null

        private var event = Event.END

        private var duration: Long? = null
        private var startDelay: Long? = null
        private var timeInterpolator: TimeInterpolator? = null
        private var evaluator: TypeEvaluator<*>? = null
        private var repeatCount: Int? = null
        @RepeatMode
        private var repeatMode: Int? = null
        private var animatorUpdateListeners: MutableList<ValueAnimator.AnimatorUpdateListener>? = null
        private var animatorListeners: MutableList<Animator.AnimatorListener>? = null
        /**
         * The source consumable Observable.
         */
        private val source = ObservableSource<Any> { observer ->
            val valueAnimator: ValueAnimator
            if (view != null) {
                valueAnimator = ObjectAnimator()
                valueAnimator.setTarget(view)
                if (method != null) {
                    valueAnimator.propertyName = method!!
                } else if (viewProperty != null) {
                    valueAnimator.setProperty(viewProperty!!)
                }
            } else {
                valueAnimator = ValueAnimator()
            }

            if (valuesInt != null) {
                valueAnimator.setIntValues(*valuesInt!!)
            } else if (valuesFloat != null) {
                valueAnimator.setFloatValues(*valuesFloat!!)
            }

            if (duration != null) {
                valueAnimator.duration = duration!!
            }
            if (startDelay != null) {
                valueAnimator.startDelay = startDelay!!
            }
            if (timeInterpolator != null) {
                valueAnimator.interpolator = timeInterpolator
            }
            if (evaluator != null) {
                valueAnimator.setEvaluator(evaluator)
            }
            if (repeatCount != null) {
                valueAnimator.repeatCount = repeatCount!!
            }
            if (repeatMode != null) {
                valueAnimator.repeatMode = repeatMode!!
            }
            if (animatorUpdateListeners != null) {
                for (animatorUpdateListener in animatorUpdateListeners!!) {
                    valueAnimator.addUpdateListener(animatorUpdateListener)
                }
            }
            valueAnimator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    log("onAnimationStart")
                    if (animatorListeners != null) {
                        for (listener in animatorListeners!!) {
                            listener.onAnimationStart(animation)
                        }
                    }
                    if (event == Event.START) {
                        observer.onNext(animation)
                        observer.onComplete()
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    log("onAnimationEnd")
                    if (animatorListeners != null) {
                        for (listener in animatorListeners!!) {
                            listener.onAnimationEnd(animation)
                        }
                    }
                    if (event == Event.END) {
                        log("onAnimationEnd / onNext")
                        observer.onNext(animation)
                        observer.onComplete()
                    }
                }

                override fun onAnimationCancel(animation: Animator) {
                    if (animatorListeners != null) {
                        for (listener in animatorListeners!!) {
                            listener.onAnimationCancel(animation)
                        }
                    }
                    if (event == Event.CANCEL) {
                        observer.onNext(animation)
                        observer.onComplete()
                    }
                }

                override fun onAnimationRepeat(animation: Animator) {
                    if (animatorListeners != null) {
                        for (listener in animatorListeners!!) {
                            listener.onAnimationRepeat(animation)
                        }
                    }
                    if (event == Event.REPEAT) {
                        observer.onNext(animation)
                        observer.onComplete()
                    }
                }
            })
            valueAnimator.start()
        }

        internal constructor(valuesInt: IntArray) {
            log("new int[]")
            this.valuesInt = valuesInt
        }

        internal constructor(view: View, method: String, valuesInt: IntArray) : this(valuesInt) {
            this.view = view
            this.method = method
        }

        internal constructor(view: View, viewProperty: Property<*, *>, valuesInt: IntArray) : this(valuesInt) {
            this.view = view
            this.viewProperty = viewProperty
        }

        internal constructor(valuesFloat: FloatArray) {
            log("new float[]")
            this.valuesFloat = valuesFloat
        }

        internal constructor(view: View, method: String, valuesFloat: FloatArray) : this(valuesFloat) {
            this.view = view
            this.method = method
        }

        internal constructor(view: View, viewProperty: Property<*, *>, valuesFloat: FloatArray) : this(valuesFloat) {
            this.view = view
            this.viewProperty = viewProperty
        }

        fun animationDuration(duration: Long): RxAnimatorObservable {
            this.duration = duration
            return this
        }

        fun animationStartDelay(startDelay: Long): RxAnimatorObservable {
            this.startDelay = startDelay
            return this
        }


        fun animationInterpolator(timeInterpolator: TimeInterpolator): RxAnimatorObservable {
            this.timeInterpolator = timeInterpolator
            return this
        }

        fun animationEvaluator(evaluator: TypeEvaluator<*>): RxAnimatorObservable {
            this.evaluator = evaluator
            return this
        }

        fun animationTriggerEvent(event: Event): RxAnimatorObservable {
            this.event = event
            return this
        }

        fun animationRepeatCount(repeatCount: Int): RxAnimatorObservable {
            this.repeatCount = repeatCount
            return this
        }

        fun animationRepeatMode(@RepeatMode repeatMode: Int): RxAnimatorObservable {
            this.repeatMode = repeatMode
            return this
        }

        protected override fun subscribeActual(observer: Observer<in Any>?) {
            if (observer != null) {
                source.subscribe(observer)
            }
        }

        fun animationAddUpdateListener(animatorUpdateListener: ValueAnimator.AnimatorUpdateListener): RxAnimatorObservable {
            if (this.animatorUpdateListeners == null) {
                this.animatorUpdateListeners = ArrayList<ValueAnimator.AnimatorUpdateListener>()
            }
            this.animatorUpdateListeners!!.add(animatorUpdateListener)
            return this
        }

        fun animationAddListener(animatorListener: Animator.AnimatorListener): RxAnimatorObservable {
            if (this.animatorListeners == null) {
                this.animatorListeners = ArrayList<Animator.AnimatorListener>()
            }
            this.animatorListeners!!.add(animatorListener)
            return this
        }
    }

    private fun log(text: String) {
        if (LOG) {
            Log.d("RxAnimator_kotlin", text)
        }
    }


}
