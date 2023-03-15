package c.m.pwdo2.common.util

import android.content.Context
import androidx.annotation.StringRes
import c.m.pwdo2.R

sealed class UIText {
    data class DynamicString(val value: String) : UIText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any,
    ) : UIText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }

    companion object {
        fun unknownError(): UIText {
            return StringResource(R.string.unknown_error)
        }
    }
}