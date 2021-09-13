package com.example.storageapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bag.dev.rs_task_4_db.data.sqlite.SqliteDatabaseHelper
import com.example.storageapp.room.Dog
import com.example.storageapp.room.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val db: SqliteDatabaseHelper
    ): ViewModel() {

    var arrayOfDogs:LiveData<List<Dog>>
    init {
        arrayOfDogs=roomRepository.getAllRepositories()
    }


    var sqlTurnedOn = false

    fun addDogsToDatabase(dog: Dog)=viewModelScope.launch(Dispatchers.IO){
        if (!sqlTurnedOn)
            roomRepository.insertIntoDatabase(dog)
        else
            db.insert(dog)

    }
    fun getDogsFromDatabase()=viewModelScope.launch(Dispatchers.IO){
        if (!sqlTurnedOn)
            arrayOfDogs = roomRepository.getAllRepositories()
         else
            arrayOfDogs = db.getData()
    }

    fun sortDataByName()=viewModelScope.launch(Dispatchers.IO){
        if (!sqlTurnedOn){
            arrayOfDogs=roomRepository.sortDataByName()
        } else {
            arrayOfDogs=db.getSortedName()
        }

    }
    fun sortDataByAge()=viewModelScope.launch(Dispatchers.IO){
        if (!sqlTurnedOn){
            arrayOfDogs=roomRepository.sortDataByAge()
        } else {
            arrayOfDogs=db.getSortedAge()
        }

    }
    fun sortDataByBreed()=viewModelScope.launch(Dispatchers.IO){
        if (!sqlTurnedOn){
            arrayOfDogs=roomRepository.sortDataByBreed()
        } else {
            arrayOfDogs=db.getSortedBreed()
        }

    }
}