package com.ruzhan.lion.ui.transitions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.ruzhan.common.R


class LiftOff(context: Context, attrs: AttributeSet) : Transition(context, attrs) {

    private val initialElevation: Float
    private val finalElevation: Float

    private val transitionProperties = arrayOf(PROPNAME_ELEVATION)

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.LiftOff)
        initialElevation = ta.getDimension(R.styleable.LiftOff_initialElevation, 0f)
        finalElevation = ta.getDimension(R.styleable.LiftOff_finalElevation, 0f)
        ta.recycle()
    }

    override fun getTransitionProperties(): Array<String> {
        return transitionProperties
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        transitionValues.values[PROPNAME_ELEVATION] = initialElevation
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        transitionValues.values[PROPNAME_ELEVATION] = finalElevation
    }

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues,
                                endValues: TransitionValues): Animator {
        return ObjectAnimator.ofFloat(endValues.view, View.TRANSLATION_Z,
                initialElevation, finalElevation)
    }

    companion object {

        private const val PROPNAME_ELEVATION = "lion:liftoff:elevation"
    }
}
