package com.tinktest.sotiris.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinktest.sotiris.models.PugInfo
import com.tinktest.sotiris.repository.PugRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class GalleryViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Job() + Dispatchers.Main
    private val pugsMutableLiveData = MutableLiveData<ArrayList<PugInfo>>()
    val pugs: LiveData<ArrayList<PugInfo>> = pugsMutableLiveData

    fun getPugs() {
        Timber.d("Getting pugs and generating some info about them...")
        launch {
            val pugPhotos = PugRepository.getDoggos(20)

            if(pugPhotos != null) {
                pugsMutableLiveData.value = preparePugsWithInfo(pugPhotos.pugs)
            } else {
                //TODO: Proper error handling
                Timber.w("An error occurred")
                pugsMutableLiveData.value = null
            }
        }
    }

    private fun preparePugsWithInfo(pugPhotos: List<String>?): ArrayList<PugInfo>? {
        if (pugPhotos != null) {
            val results = arrayListOf<PugInfo>()

            pugPhotos.forEachIndexed { index, pugImage ->
                Timber.d("-> %d : %s", index, pugImage)
                val pugInfo = PugInfo("Pug " + (index + 1), "Really cute pug", pugImage)

                results.add(pugInfo)
            }

            return results
        } else {
            return null
        }
    }
}