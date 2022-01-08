package com.thirdwayv.tracker.core.base.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PermissionsHandler @Inject constructor(@ApplicationContext val context: Context) {
    fun hasPermissions(permissions: Array<String>): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

    fun requestPermissions(
        fragment: Fragment,
        permissions: Array<String>,
        allPermissionsGranted: () -> Unit,
        permissionsDenied: () -> Unit
    ): ActivityResultLauncher<Array<String>> {
        val permissionLauncher =
            fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val granted = permissions.entries.all {
                    it.value == true
                }
                if (granted) {
                    allPermissionsGranted()
                } else
                    permissionsDenied()
            }
        permissionLauncher.launch(permissions)
        return permissionLauncher
    }
}
