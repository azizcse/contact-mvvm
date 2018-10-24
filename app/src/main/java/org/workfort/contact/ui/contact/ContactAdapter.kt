package org.workfort.contact.ui.contact

import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.core.kbasekit.ui.base.BaseAdapter
import com.core.kbasekit.ui.base.BaseViewHolder
import org.workfort.contact.R
import org.workfort.contact.data.contact.ContactEntity
import org.workfort.contact.databinding.ItemContactBinding
import org.workfort.contact.util.helper.LogObject


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 10/11/2018 at 4:58 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 10/11/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class ContactAdapter : BaseAdapter<LogObject>() {
    override fun isEqual(leftItem: LogObject, rightItem: LogObject): Boolean {
        return false//leftItem.getNumber().equals(rightItem.getNumber())
    }

    override fun newViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<LogObject> {
        return ContactViewHolder(inflate(parent!!, R.layout.item_contact))!!
    }

    private inner class ContactViewHolder(viewDataBinding: ViewDataBinding) : BaseViewHolder<LogObject>(viewDataBinding) {
        private val itemContactBinding: ItemContactBinding
        init { itemContactBinding = viewDataBinding as ItemContactBinding }
        override fun bind(item: LogObject) {
            itemContactBinding.contact = item
        }
        override fun onClick(p0: View?) {  }

    }

}