package com.codekul.grapprapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.codekul.grapprapp.AppListActivity
import com.codekul.grapprapp.R
import com.codekul.grapprapp.adapter.CategoryAdapter
import com.codekul.grapprapp.domain.Category
import kotlinx.android.synthetic.main.home_layout.*


/**
 * Created by pooja on 30/12/17.
 */
class HomeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.home_layout,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.title="CashBolo"
        setCategories()
    }

    fun setCategories() {

            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

            val items = ArrayList<Category>()
            items.add(Category("Install Apps"))
            items.add(Category("Watch Videos"))
            items.add(Category("Write Reviews"))
            items.add(Category("Complete Survey"))
            items.add(Category("Join WhatsApp Groups"))

            val adapter = CategoryAdapter(items){
                val intent = Intent(context,AppListActivity::class.java)
                startActivity(intent)
            }
            //adding the adapter to recyclerview
            recyclerView.adapter = adapter

    }

}