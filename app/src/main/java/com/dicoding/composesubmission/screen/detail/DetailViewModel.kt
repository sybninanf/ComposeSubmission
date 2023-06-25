package com.dicoding.composesubmission.screen.detail
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.dicoding.composesubmission.api.ApiConfig
//import com.dicoding.composesubmission.models.CharacterResponse
//import com.dicoding.composesubmission.models.ValoResponse
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class DetailViewModel : ViewModel() {
//    private val apiService = ApiConfig().createApiService()
//    val characterLiveDataList = MutableLiveData<List<ValoResponse>>()
//
//    fun getCharacters(id: Int) {
//        val call = apiService.getOne(id)
//        call.enqueue(object : Callback<ValoResponse> {
//            override fun onResponse(call: Call<ValoResponse>, response: Response<ValoResponse>) {
//                if (response.isSuccessful) {
//                    val characterResponse = response.body()
//                    characterResponse?.let { character ->
//                        characterLiveDataList.value = listOf(character)
//                    }
//                } else {
//                    // Handle unsuccessful response
//                }
//            }
//
//            override fun onFailure(call: Call<ValoResponse>, t: Throwable) {
//                // Handle failure here
//            }
//        })
//    }
//}
