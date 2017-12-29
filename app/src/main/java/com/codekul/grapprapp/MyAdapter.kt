package com.codekul.grapprapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by pooja on 28/12/17.
 */
class MyAdapter(val list:ArrayList<MyDataClass>) :RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.bindItems(list[position])
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bindItems(data : MyDataClass){
            val _txtAppName: TextView = itemView.findViewById(R.id.txtAppName)
            val _txtAppCategory: TextView = itemView.findViewById(R.id.txtAppCategory)
            val _imgAppIcon: ImageView = itemView.findViewById(R.id.imgAppIcon)

            _txtAppName.text = data.name
            _txtAppCategory.text=data.category
            _imgAppIcon.setImageResource(data.imgIcon)

            //set the onclick listener for the singlt list item
//            itemView.setOnClickListener({
//                Log.e("ItemClicked", data.name )
//            })
        }
    }
}