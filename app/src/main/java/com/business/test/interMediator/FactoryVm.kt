package com.business.test.interMediator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.business.test.viewModel.MainViewModel

class FactoryVm(private var repository: Repository):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModel::class.java)){

            return  MainViewModel(repository) as T

        }else{

        }

      throw IllegalArgumentException("non viewModelClass")
    }
}