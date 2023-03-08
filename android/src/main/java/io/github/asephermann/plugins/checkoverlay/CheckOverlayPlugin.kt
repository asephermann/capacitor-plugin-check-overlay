package io.github.asephermann.plugins.checkoverlay

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin

@CapacitorPlugin(name = "CheckOverlay")
class CheckOverlayPlugin : Plugin() {

    private val implementation = CheckOverlay()

    @PluginMethod
    fun checkOverlayApps(call: PluginCall) {
        val blackList = call.getArray("blackList")
        val context: Context = bridge.activity.applicationContext

        // Get a list of apps that are currently displaying overlays
        val overlayApps = implementation.getOverlayApps(context, blackList.toList())
        if (overlayApps.overlayApps.length() > 0) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Screen overlay detected")
//            builder.setMessage("To change this permission setting, you first have to turn off the screen overlay from Settings > Apps.")
            builder.setMessage("There are some overlay apps installed in this device, due to security reason you are not allowed to proceed.")
            builder.setPositiveButton("OK") { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.parse(String.format("package:%s", overlayApps.overlayApps[0]))
                    activity.startActivity(intent)
                }
            }
            builder.setCancelable(false)
            builder.show()
        }

        // Return the list of apps to the Capacitor app
        val result = JSObject()
        result.put("hasOverlay", overlayApps.hasOverlay)
        result.put("overlayApps", overlayApps.overlayApps)
        result.put(
            "message",
            if (overlayApps.hasOverlay) "Overlay app detected" else "No overlay app detected"
        )
        call.resolve(result)
    }
}