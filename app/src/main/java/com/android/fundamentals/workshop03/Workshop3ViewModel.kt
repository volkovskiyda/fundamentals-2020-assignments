package com.android.fundamentals.workshop03

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationGenerator
import kotlinx.coroutines.launch

class Workshop3ViewModel(
    private val generator: LocationGenerator
) : ViewModel() {
    
    //TODO 03: Create private property MutableLiveData<boolean>
    // and public getter LiveData<boolean> for loading state (false).
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    //TODO 04: Create private property MutableLiveData<List<Location>>
    // and public getter LiveData for locations (emptyList()).
    private val _locations = MutableLiveData(emptyList<Location>())
    val locations: LiveData<List<Location>> = _locations

    fun addNew() {
        viewModelScope.launch {
            //TODO 05: Copy logic for "addNew()" from fragment.
            // Update value of loading state's private livedata in the beginning and at the end of the coroutine.
            // Update value of locations's private livedata inside:
            // - get the previous value from mutable live data;
            // - add (.plus()) a new location to the collection;
            // - set updated collection back to the private livedata's value.
            _loading.value = true

            val newLocation = generator.generateNewLocation()
            _locations.value = _locations.value.orEmpty() + newLocation

            _loading.value = false
        }
    }
}