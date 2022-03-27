package com.shukevich.shoppinglist.preaentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shukevich.shoppinglist.data.ShopListRepositoryImpl  // не должно быть дата слоя
import com.shukevich.shoppinglist.domain.DeleteShopItemUseCase
import com.shukevich.shoppinglist.domain.EditShopItemUseCase
import com.shukevich.shoppinglist.domain.GetShopListUseCase
import com.shukevich.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel(){

    private val repository = ShopListRepositoryImpl // неправильно, презентейшен слой зависит от дата слоя, так нельзя

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private  val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()



    fun deleteShopItem(shopItem: ShopItem){          // удаляем итем
        deleteShopItemUseCase.deleteShopItem(shopItem)     // обнавляем лист
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}