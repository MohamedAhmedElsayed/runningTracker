package com.thirdwayv.tracker.features.home.domain.stepscounter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.thirdwayv.tracker.features.home.domain.usercase.GetPreviousStepsCounterUseCase
import com.thirdwayv.tracker.features.home.domain.usercase.SavePreviousStepsCounterUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * class that use TYPE_STEP_COUNTER for detecting steps
 * @param context is application context
 * @param GetPreviousStepsCounterUseCase to get previous cashed steps to count from it not from beggin
 * @param SavePreviousStepsCounterUseCase to save current step counter
 * */
class StepsCounterImp @Inject constructor(
    @ApplicationContext val context: Context,
    getPreviousStepsCounterUseCase: GetPreviousStepsCounterUseCase,
    private val savePreviousStepsCounterUseCase: SavePreviousStepsCounterUseCase
) : StepsCounter, SensorEventListener {
    private var sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    private val _stepsCounterFlow = MutableStateFlow(0f)
    private var previousTotalSteps = getPreviousStepsCounterUseCase.execute()

    override fun getStepsCounterObserver() = _stepsCounterFlow

    /**
     * start counting
     * @param registrationResult callback function take  registeredSuccessfully status as a parameter
     * */
    override fun startStepCounter(registrationResult: (registeredSuccessfully: Boolean) -> Unit) {
        if (stepSensor != null) {
            // Rate suitable for the user interface
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)

        }
        registrationResult(stepSensor != null)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.values.isEmpty()) return
            if (previousTotalSteps == 0f)
                previousTotalSteps = it.values[0]
            _stepsCounterFlow.value = it.values[0] - previousTotalSteps
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun stopCounter() {
        pauseReceivingSteps()
        savePreviousStepsCounterUseCase.execute(_stepsCounterFlow.value + previousTotalSteps)
        previousTotalSteps = 0f
    }

    override fun pauseReceivingSteps() {
        if (stepSensor != null)
            sensorManager.unregisterListener(this)
    }
}