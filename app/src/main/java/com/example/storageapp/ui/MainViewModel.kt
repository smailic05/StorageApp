package com.example.storageapp.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import bag.dev.rs_task_4_db.data.sqlite.SqliteDatabaseHelper
import com.example.storageapp.room.Dog
import com.example.storageapp.room.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    val db: SqliteDatabaseHelper
    ): ViewModel() {
    var sqlTurnedOn = false

    var arrayOfDogs=MutableLiveData<List<Dog>>()

    fun addDogsToDatabase(dog: Dog)=viewModelScope.launch(Dispatchers.IO){
        if (!sqlTurnedOn)
            roomRepository.insertIntoDatabase(dog)
        else
            db.insert(dog)
    }
    fun getDogsFromDatabase(){
        if (!sqlTurnedOn)
            arrayOfDogs.postValue(roomRepository.getAllRepositories())
         else
            arrayOfDogs.postValue(db.getData().value)
    }
    fun updateDogsInDatabase(dog: Dog){
        if (!sqlTurnedOn)
            viewModelScope.launch(Dispatchers.IO) {
                roomRepository.updateInDatabase(dog)
        }
        else
            db.update(dog)
    }
    fun deleteDogsInDatabase(dog: Dog){
        if (!sqlTurnedOn)
            viewModelScope.launch(Dispatchers.IO) {
                roomRepository.deleteFromDatabase(dog)
            }
        else
            db.delete(dog)
    }

    fun sortDataByName(){
        if (!sqlTurnedOn){
            viewModelScope.launch(Dispatchers.IO){
                arrayOfDogs.postValue(roomRepository.sortDataByName())
            }
        } else {
            arrayOfDogs.postValue(db.getSortedName().value)
        }
    }
    fun sortDataByAge(){
        if (!sqlTurnedOn){
            viewModelScope.launch(Dispatchers.IO){
                arrayOfDogs.postValue(roomRepository.sortDataByAge())
            }
        } else {
            arrayOfDogs.postValue(db.getSortedAge().value)
        }
    }
    fun sortDataByBreed(){
        if (!sqlTurnedOn){
            viewModelScope.launch(Dispatchers.IO){
                arrayOfDogs.postValue(roomRepository.sortDataByBreed())
            }
        } else {
            arrayOfDogs.postValue(db.getSortedBreed().value)
        }
    }
}