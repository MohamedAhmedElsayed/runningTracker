package com.thirdwayv.tracker.features.home.presentation.domain.stepscounter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StepsCounterImp @Inject constructor(@ApplicationContext val context: Context) : StepsCounter,
    SensorEventListener {
    private var sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    private val _stepsCounterLiveData = MutableLiveData(0f)
    private val previousTotalSteps = 0

    override fun getStepsCounterObserver() = _stepsCounterLiveData
    fun registerForStepCounter(registrationResult: (registeredSuccessfully: Boolean) -> Unit) {
        if (stepSensor != null) {
            // Rate suitable for the user interface
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)

        }
        registrationResult(stepSensor != null)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            _stepsCounterLiveData.value = it.values[0]
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    fun unregisterListeners() {
        if (stepSensor != null)
            sensorManager.unregisterListener(this)
    }
}