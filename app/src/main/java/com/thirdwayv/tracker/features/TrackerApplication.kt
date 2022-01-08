package com.thirdwayv.tracker.features

import android.app.Application
import com.thirdwayv.tracker.core.base.utils.Constants.REALM_NAME
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration


@HiltAndroidApp
class TrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val realmDefaultConfig = RealmConfiguration.Builder().name(REALM_NAME)
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
        Realm.setDefaultConfiguration(realmDefaultConfig.build())
    }
}