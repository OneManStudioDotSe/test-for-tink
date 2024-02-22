package se.onemanstudio.test.tink.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import se.onemanstudio.test.tink.models.PugInfo
import se.onemanstudio.test.tink.repository.DogCeoRepository
import se.onemanstudio.test.tink.repository.PugRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class GalleryViewModel : ViewModel() {
    private val pugsMutableLiveData = MutableLiveData<ArrayList<PugInfo>?>()
    val pugs: MutableLiveData<ArrayList<PugInfo>?> = pugsMutableLiveData

    fun getPugs() {
        Timber.d("Getting pugs from Pug Me and generating some info about them...")
        viewModelScope.launch(Dispatchers.IO) {
            val pugPhotos = PugRepository.getDoggos(20)

            if(pugPhotos != null) {
                pugsMutableLiveData.postValue(preparePugsWithInfo(pugPhotos.pugs))
            } else {
                Timber.w("An error occurred")
                pugsMutableLiveData.postValue(null)
            }
        }
    }

    fun getPugsFromDogCeo() {
        Timber.d("Getting pugs from Dog Ceo and generating some info about them...")
        viewModelScope.launch(Dispatchers.IO) {
            val pugPhotos = DogCeoRepository.getDoggos()

            if(pugPhotos != null) {
                pugsMutableLiveData.postValue(preparePugsWithInfo(pugPhotos.message))
            } else {
                Timber.w("An error occurred")
                pugsMutableLiveData.postValue(null)
            }
        }
    }

    private fun preparePugsWithInfo(pugPhotos: List<String>?): ArrayList<PugInfo>? {
        if (pugPhotos != null) {
            val results = arrayListOf<PugInfo>()

            pugPhotos.forEachIndexed { index, pugImage ->
                //Timber.d("-> %d : %s", index, pugImage)
                val pugInfo = PugInfo("Pug " + (index + 1), "Really cute pug", pugImage)

                results.add(pugInfo)
            }

            return results
        } else {
            return null
        }
    }
}
