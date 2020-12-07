package com.tinktest.sotiris.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinktest.sotiris.models.DogFunFact
import com.tinktest.sotiris.repository.DogFactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class DetailsViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Job() + Dispatchers.Main
    private val pugsMutableLiveData = MutableLiveData<DogFunFact>()
    val dogFunFact: LiveData<DogFunFact> = pugsMutableLiveData

    fun getDogFunFact() {
        Timber.d("Getting a fact about dogs...")
        launch {
            val dogFact = DogFactRepository.getDogFact()

            if(dogFact != null) {
                pugsMutableLiveData.value = DogFunFact(dogFact.facts[0])
            } else {
                Timber.w("An error occurred")
                pugsMutableLiveData.value = null
            }
        }
    }
}