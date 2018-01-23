package com.codekul.grapprapp.adapter


import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.codekul.grapprapp.fragment.AppsFragment

/**
 * Created by pooja on 11/1/18.
 */
class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): AppsFragment {
        var bundle : Bundle = Bundle()
        bundle.putInt("position",position)

        var appsFragment: AppsFragment = AppsFragment()
        appsFragment.arguments = bundle

        return appsFragment
    }

    override fun getCount(): Int {
        return 10
    }
}