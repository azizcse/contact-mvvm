package com.core.kbasekit.ui.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 12/13/2017 at 4:59 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Md. Imran Hossain on 4/23/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

abstract class BaseViewHolder<T>constructor(private val viewDataBinding: ViewDataBinding)
    : RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {

    abstract fun bind(item: T)


    fun setOnclick(vararg views : View){
        for (view : View in views){
            view.setOnClickListener(this)
        }
    }

    fun getViewBinding():ViewDataBinding{
        return viewDataBinding
    }
}