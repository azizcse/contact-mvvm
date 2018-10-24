package org.workfort.contact.util.helper

import android.provider.ContactsContract
import android.net.Uri.withAppendedPath
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import java.text.DecimalFormat


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 10/22/2018 at 12:25 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 10/22/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class LogObject : CallLogObject {
    private var number: String? = null
    private var date: Long = 0
    private var duration: Int = 0
    private var type:Int = 0
    private val context: Context

  constructor(context: Context){
      this.context = context
  }

    override fun getNumber(): String {
        return number!!
    }

    override fun setNumber(number: String) {
        this.number = number
    }

    override fun getType(): Int {
        return type
    }

    override fun setType(type: Int) {
        this.type = type
    }

    override fun getDate(): Long {
        return date
    }

    override fun setDate(date: Long) {
        this.date = date
    }

    override fun getDuration(): Int {
        return duration
    }

    override fun setDuration(duration: Int) {
        this.duration = duration
    }

    override fun getCoolDuration(): String {
        return getCoolDuration(getDuration().toFloat())
    }

    override fun getContactName(): String {
        return if (getNumber() != null) {
            findNameByNumber(getNumber())
        } else {
            null!!
        }
    }

    private fun findNameByNumber(phoneNumber: String): String {
        val cr = context.contentResolver

        val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber))

        val cursor = cr.query(uri, arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME), null, arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME), null)
                ?: return null!!

        var contactName: String? = null

        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME))
        }

        if (!cursor.isClosed) {
            cursor.close()
        }

        return contactName ?: phoneNumber
    }

    private fun getCoolDuration(sum: Float): String {

        var duration = ""
        val result: String

        if (sum >= 0 && sum < 3600) {

            result = (sum / 60).toString()
            val decimal = result.substring(0, result.lastIndexOf("."))
            val point = "0" + result.substring(result.lastIndexOf("."))

            val minutes = Integer.parseInt(decimal)
            val seconds = java.lang.Float.parseFloat(point) * 60

            val formatter = DecimalFormat("#")
            duration = minutes.toString() + " min " + formatter.format(seconds.toDouble()) + " secs"

        } else if (sum >= 3600) {

            result = (sum / 3600).toString()
            val decimal = result.substring(0, result.lastIndexOf("."))
            val point = "0" + result.substring(result.lastIndexOf("."))

            val hours = Integer.parseInt(decimal)
            val minutes = java.lang.Float.parseFloat(point) * 60

            val formatter = DecimalFormat("#")
            duration = hours.toString() + " hrs " + formatter.format(minutes.toDouble()) + " min"

        }

        return duration
    }
}