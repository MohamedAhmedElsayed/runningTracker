package com.thirdwayv.tracker.core.base.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * class that handle app permissions
 * */
class PermissionsHandler @Inject constructor(@ApplicationContext val context: Context) {
    private var permissionLauncher: ActivityResultLauncher<Array<String>>? = null

    /**
     * check for permissions
     * */
    fun hasPermissions(permissions: Array<String>): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

    /**
     * register permissions callbacks
     * @param fragment that needs to take permissions
     * @param onAllPermissionsGranted that invoked when user accept all permissions
     * @param onPermissionsDenied invoked when there are any permission denied by user
     * */
    fun registerPermissionCallBacks(
        fragment: Fragment,
        onAllPermissionsGranted: () -> Unit,
        onPermissionsDenied: () -> Unit
    ) {
        permissionLauncher =
            fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val granted = permissions.entries.all {
                    it.value == true
                }
                if (granted) {
                    onAllPermissionsGranted()
                } else
                    onPermissionsDenied()
            }

    }

    /**
     * launch registered launcher
     * */
    fun launchPermissions(permissions: Array<String>) {
        permissionLauncher?.launch(permissions)

    }
}
