package com.test.myapplication.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.api.RetrofitInstance.api
import com.test.myapplication.model.AppDetails
import com.test.myapplication.model.ReviewItem
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

data class AppData(
    val appDetails: AppDetails,
    val reviewList: List<ReviewItem>
)

class DetailViewModel: ViewModel() {

    var appId: String = ""

    private val _appData = MutableStateFlow<AppData?>(null)
    val appData = _appData.asStateFlow()


    fun getData() {
        viewModelScope.launch {
            try {
                supervisorScope {
                    val infoDeferred = async { api.getAppDetails(appId) }
                    val reviewDeferred = async { api.getReviews(appId = appId) }
                    val infoResult = infoDeferred.await()
                    val reviewResult = reviewDeferred.await()

                    _appData.value = AppData(
                        infoResult,
                        reviewResult.reviews
                    )
                }
            } catch (e: Exception) {
                getData()
            }

        }
    }

}