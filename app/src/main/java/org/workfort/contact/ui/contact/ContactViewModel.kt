package org.workfort.contact.ui.contact

import android.Manifest
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.workfort.contact.data.contact.ContactEntity
import org.workfort.contact.data.contact.ContactRepository
import org.workfort.contact.ui.base.BaseViewModel
import org.workfort.contact.util.PermissionUtil
import org.workfort.contact.util.helper.LogManager
import org.workfort.contact.util.helper.LogObject
import org.workfort.contact.util.runOnIoThread
import org.workfort.contact.util.runOnUiThread
import java.util.*

/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 10/10/2018 at 5:24 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 10/10/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class ContactViewModel internal constructor( private val contactRepository: ContactRepository) : BaseViewModel() {

    private var liveDateOfLogObject = MutableLiveData<List<LogObject>>()

    fun getAllUsers(): LiveData<List<ContactEntity>> {
        return LiveDataReactiveStreams.fromPublisher(contactRepository.getContacts())
    }

    fun getCallLogs():LiveData<List<LogObject>>{
        return liveDateOfLogObject
    }

    fun saveContact() {
        val number = UUID.randomUUID().toString()
        val contactEntity = ContactEntity("Aziz", number)
        getDisposable().add(contactRepository.saveContact(contactEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun deleteItem(contactEntity: ContactEntity?) {
        if (contactEntity == null) return

        getDisposable().add(contactRepository.deleteItem(contactEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun loadCallLogs(context: Context){
        val logManager = LogManager(context)
        runOnIoThread {
            val logList = logManager.getLogs(LogManager.ALL_CALLS)
            runOnIoThread {liveDateOfLogObject.postValue(logList)}
        }
    }



}


