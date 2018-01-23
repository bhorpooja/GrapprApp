package com.codekul.grapprapp.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.codekul.grapprapp.R
import com.codekul.grapprapp.domain.Category

/**
 * Created by pooja on 11/1/18.
 */
class CategoryAdapter(val categories:ArrayList<Category>,val listener:(Int)->Unit):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItems(categories[position],position,listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent?.context).inflate(R.layout.category_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return categories.size
    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bindItems(data:Category,pos:Int,listener: (Int) -> Unit)= with(itemView){

            val textView1:TextView=itemView.findViewById(R.id.txtCategoryName)

            textView1.text=data.category

            itemView.setOnClickListener {
                listener(pos)
            }
        }

    }
}