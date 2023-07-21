package com.tinktest.sotiris.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinktest.sotiris.models.DogFunFact
import com.tinktest.sotiris.repository.DogFactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class DetailsViewModel : ViewModel() {
    private val pugsMutableLiveData = MutableLiveData<DogFunFact?>()
    val dogFunFact: MutableLiveData<DogFunFact?> = pugsMutableLiveData

    fun getDogFunFact() {
        Timber.d("Getting a fact about dogs...")
        viewModelScope.launch(Dispatchers.IO) {
            val dogFact = DogFactRepository.getDogFact()

            if(dogFact != null) {
                pugsMutableLiveData.postValue(DogFunFact(dogFact.facts[0]))
            } else {
                Timber.w("An error occurred")
                pugsMutableLiveData.postValue(null)
            }
        }
    }
}
