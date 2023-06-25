package com.dicoding.composesubmission.screen.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.composesubmission.api.ApiConfig
import com.dicoding.composesubmission.models.DataItem
import com.dicoding.composesubmission.models.ValoResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _agentList = MutableLiveData<List<DataItem>>() // Ganti ResponseBody dengan tipe data yang sesuai dengan respon endpoint
    val agentList: LiveData<List<DataItem>> = _agentList

    fun fetchAgentList() {
        val service = ApiConfig.create()
        val call = service.getAgentList()

        call.enqueue(object : Callback<ValoResponse> {
            override fun onResponse(call: Call<ValoResponse>, response: Response<ValoResponse>) {
                if (response.isSuccessful) {
                    val valoResponse = response.body()
                    _agentList.value = (valoResponse?.data ?: emptyList()) as List<DataItem>?
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<ValoResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun fetchAgentDetails(uuid: String) {
        viewModelScope.launch {
            try {
                val service = ApiConfig.create()
                val response = service.getAgentDetails("details",uuid)

                if (response.isSuccessful) {
                    val valoResponse = response.body()
                    val agentList = valoResponse?.data ?: emptyList()
                    _agentList.value = agentList as List<DataItem>?
                } else {
                    Log.e("error", "Response failed: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("error", "Request failed: ${e.message}")
            }
        }
    }


}
