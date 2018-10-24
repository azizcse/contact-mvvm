package org.workfort.contact.util.helper


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 10/22/2018 at 12:24 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 10/22/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

interface CallLogObject {
    fun getNumber(): String

    fun setNumber(number: String)

    fun getType(): Int

    fun setType(type: Int)

    fun getDate(): Long

    fun setDate(date: Long)

    fun getDuration(): Int

    fun setDuration(duration: Int)

    fun getCoolDuration(): String

    fun getContactName(): String

}