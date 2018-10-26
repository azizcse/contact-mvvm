package org.workfort.contact.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.workfort.contact.R
import org.workfort.contact.ui.custom.FragmentPagerItem


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 10/26/2018 at 6:06 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 10/26/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class DemoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = FragmentPagerItem.getPosition(arguments)
        val title = view.findViewById(R.id.item_title) as TextView
        title.setText(position.toString())
    }

}