package org.workfort.contact.util.helper

import android.content.Context
import android.provider.CallLog
import java.text.DecimalFormat


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 10/22/2018 at 11:53 AM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 10/22/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

open class LogManager {
    companion object {
        val INCOMING: Int = CallLog.Calls.INCOMING_TYPE;
        val OUTGOING: Int = CallLog.Calls.OUTGOING_TYPE;
        val MISSED: Int = CallLog.Calls.MISSED_TYPE;
        val TOTAL: Int = 579

        val INCOMING_CALLS: Int = 672
        val OUTGOING_CALLS: Int = 609
        val MISSED_CALLS: Int = 874
        val ALL_CALLS: Int = 814
        val READ_CALL_LOG: Int = 47
    }

    private var context: Context? = null

    constructor(context: Context) {
        this.context = context
    }

    fun getOutgoingDuration(): Int {
        var sum = 0

        val cursor = context!!.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE, null, null)

        val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)

        while (cursor.moveToNext()) {
            val callDuration = cursor.getString(duration)
            sum += Integer.parseInt(callDuration)
        }

        cursor.close()

        return sum
    }

    fun getIncomingDuration(): Int {
        var sum = 0

        val cursor = context!!.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                CallLog.Calls.TYPE + " = " + CallLog.Calls.INCOMING_TYPE, null, null)

        val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)

        while (cursor.moveToNext()) {
            val callDuration = cursor.getString(duration)
            sum += Integer.parseInt(callDuration)
        }

        cursor.close()

        return sum
    }

    fun getTotalDuration(): Int {
        var sum = 0

        val cursor = context!!.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null)

        val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)

        while (cursor.moveToNext()) {
            val callDuration = cursor.getString(duration)
            sum += Integer.parseInt(callDuration)
        }

        cursor.close()

        return sum
    }

    fun getCoolDuration(type: Int): String {
        val sum: Float

        when (type) {
            INCOMING -> sum = getIncomingDuration() as Float
            OUTGOING -> sum = getOutgoingDuration() as Float
            TOTAL -> sum = getTotalDuration() as Float
            else -> sum = 0f
        }

        var duration = ""
        val result: String

        if (sum >= 0 && sum < 3600) {

            result = (sum / 60).toString()
            val decimal = result.substring(0, result.lastIndexOf("."))
            val point = "0" + result.substring(result.lastIndexOf("."))

            val minutes = Integer.parseInt(decimal)
            val seconds = java.lang.Float.parseFloat(point) * 60

            val formatter = DecimalFormat("#")
            duration = minutes.toString() + " min " + formatter.format(seconds) + " secs"

        } else if (sum >= 3600) {

            result = (sum / 3600).toString()
            val decimal = result.substring(0, result.lastIndexOf("."))
            val point = "0" + result.substring(result.lastIndexOf("."))

            val hours = Integer.parseInt(decimal)
            val minutes = java.lang.Float.parseFloat(point) * 60

            val formatter = DecimalFormat("#")
            duration = hours.toString() + " hrs " + formatter.format(minutes) + " min"

        }

        return duration
    }

    fun getLogs(callType: Int): List<LogObject> {
        val logs = ArrayList<LogObject>()

        var selection: String?

        when (callType) {
            INCOMING_CALLS -> selection = CallLog.Calls.TYPE + " = " + CallLog.Calls.INCOMING_TYPE
            OUTGOING_CALLS -> selection = CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE
            MISSED_CALLS -> selection = CallLog.Calls.TYPE + " = " + CallLog.Calls.MISSED_TYPE
            ALL_CALLS -> {
                selection = null
                selection = null
            }
            else -> selection = null
        }

        val cursor = context!!.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, selection, null, null)
        val number = cursor.getColumnIndex(CallLog.Calls.NUMBER)
        val type = cursor.getColumnIndex(CallLog.Calls.TYPE)
        val date = cursor.getColumnIndex(CallLog.Calls.DATE)
        val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)

        while (cursor.moveToNext()) {
            val log = LogObject(context!!)

            log.setNumber(cursor.getString(number))
            log.setType(cursor.getInt(type))
            log.setDuration(cursor.getInt(duration))
            log.setDate(cursor.getLong(date))

            logs.add(log)
        }

        cursor.close()


        return logs
    }


}