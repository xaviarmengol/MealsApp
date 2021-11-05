package com.example.mealzapp.ui.tabFilter

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mealzapp.ui.utils.hasNetwork
import java.lang.ref.WeakReference


class TabFilterViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TabFilterViewModel(application) as T
}

class TabFilterViewModel (val app : Application) : AndroidViewModel(app) {

    val hasConection = mutableStateOf(false)

    init {
        refreshConectionState()
    }

    fun refreshConectionState () {
        //val context = WeakReference(app.applicationContext)
        //hasConection.value = context.get()?.let { hasNetwork(it) }?:false

        hasConection.value = hasNetwork(app.applicationContext)
    }

}