package com.shukevich.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.shukevich.shoppinglist.R
import com.shukevich.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llShoplist: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llShoplist = findViewById(R.id.ll_shop_list)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
           showList(it)

        }

    }

    private fun showList(list: List<ShopItem>){
        llShoplist.removeAllViews()
        for(shopItem in list){
            val layoutId = if (shopItem.enabled){
                R.layout.item_shop_enabled
            }else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId,llShoplist, false)
            val tvName:TextView = view.findViewById(R.id.tv_name)
            val tvCount: TextView = view.findViewById(R.id.tv_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            view.setOnLongClickListener{
                viewModel.changeEnableState(shopItem)
                true
            }
            llShoplist.addView(view)
        }

    }
}