package io.github.asephermann.plugins.checkoverlay

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import org.json.JSONArray
import java.util.*


class CheckOverlay {
    private var overlayApps = JSONArray()

    fun getOverlayApps(context: Context, blackList: List<String>): OverlayResult {
        overlayApps = JSONArray()

        val packageManager = context.packageManager
        val appList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        for (app in appList) {
            try {
                val packageInfo =
                    packageManager.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS)
                if (packageInfo.requestedPermissions != null && packageInfo.requestedPermissions.contains(
                        Manifest.permission.SYSTEM_ALERT_WINDOW
                    )
                ) {
                    try {
//                        val appOpsManager =
//                            context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
//                        val mode: Int = appOpsManager.noteOp(
//                            AppOpsManager.permissionToOp(Manifest.permission.SYSTEM_ALERT_WINDOW)!!,
//                            app.uid,
//                            app.packageName
//                        )
                        val blacklistSet = mutableSetOf<String>()
                        for (appPackage in blackList) {
                            if (appPackage.contains(":")) {
                                val packageNameParts = appPackage.split(":").toTypedArray()
                                if (packageNameParts.isNotEmpty()) {
                                    blacklistSet.add(packageNameParts[0])
                                }
                            } else {
                                blacklistSet.add(appPackage)
                            }
                        }

                        if (blacklistSet.contains(app.packageName)) {
                            overlayApps.put(app.packageName)
                        }
//                        else if (mode == AppOpsManager.MODE_ALLOWED && blacklistSet.contains(app.packageName)
//                        ) {
//                            overlayApps.put(app.packageName)
//                        }
                    } catch (e: SecurityException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }

        return OverlayResult(overlayApps.length() > 0, overlayApps)
    }
}

data class OverlayResult(val hasOverlay: Boolean, val overlayApps: JSONArray)
