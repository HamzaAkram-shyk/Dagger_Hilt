package com.example.viewModel


import androidx.lifecycle.*
import com.example.Util.Resource
import com.example.hiltdi.model.Blog
import com.example.repository.MainRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: MainRepository,

    ) : ViewModel() {
    private var observer = MutableLiveData<Resource<List<Blog>>>()

    fun set() {
        viewModelScope.launch {
            repository.getBlog().onEach {
                observer.value = it

            }
                .launchIn(viewModelScope)
        }
    }

    fun getObserver(): LiveData<Resource<List<Blog>>> {
        return observer
    }


}