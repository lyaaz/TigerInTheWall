package com.jakting.shareclean.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.jakting.shareclean.R
import java.lang.reflect.Field


fun Context.openLink(url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}

fun Context.backgroundColor(@ColorRes colorRes: Int): ColorStateList =
    ColorStateList.valueOf(ContextCompat.getColor(this, colorRes))

fun getResId(resName: String, c: Class<*>): Int {
    return try {
        val idField: Field = c.getDeclaredField(resName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}

fun Context.isDarkMode(): Boolean {
    val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return currentNightMode == Configuration.UI_MODE_NIGHT_YES
}


fun Context.getPxFromDp(dp: Int): Int {
    val scale: Float = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.getDpFromPx(px: Int): Int {
    val scale: Float = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

//
//
//fun getManageTypeTitle(tag: String): String {
//    return when (tag) {
//        "send" -> appContext.getString(R.string.send_title)
//        "send_multi" -> appContext.getString(R.string.send_multi_title)
//        "view" -> appContext.getString(R.string.view_title)
//        "text" -> appContext.getString(R.string.text_title)
//        "browser" -> appContext.getString(R.string.browser_title)
//        else -> appContext.getString(R.string.app_name)
//    }
//}
//
//
fun Context.getAppIconByPackageName(ApkTempSendActivityName: String): Drawable? {
    val drawable: Drawable? = try {
        this.packageManager?.getApplicationIcon(ApkTempSendActivityName)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        this.packageManager?.let { ContextCompat.getDrawable(this, R.mipmap.ic_launcher) }
    }
    return drawable
}

//fun setFirebase() {
//    val isUseFirebase = settingSharedPreferences.getBoolean("switch_firebase", true)
//    FirebaseAnalytics.getInstance(appContext).setAnalyticsCollectionEnabled(isUseFirebase)
//    FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(isUseFirebase)
//}

//fun dip2px(dpValue: Int): Int {
//    val dp = dpValue.toFloat()
//    val scale = appContext.resources.displayMetrics.density
//    return (dp * scale + 0.5f).toInt()
//}

