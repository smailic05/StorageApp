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


    fun addDogsToDatabase(dog: Dog)=viewModelScope.launch(Dispatchers.IO){
        if (!sqlTurnedOn)
            roomRepository.insertIntoDatabase(dog)
        else
            db.insert(dog)
    }
    fun getDogsFromDatabase():LiveData<List<Dog>>{
        if (!sqlTurnedOn)
            return roomRepository.getAllRepositories()
         else
            return  db.getData()
    }
    fun updateDogsInDatabase(dog: Dog){
        if (!sqlTurnedOn)
            roomRepository.updateInDatabase(dog)
        else
            db.update(dog)
    }
    fun deleteDogsInDatabase(dog: Dog){
        if (!sqlTurnedOn)
            roomRepository.deleteFromDatabase(dog)
        else
            db.delete(dog)
    }

    fun sortDataByName():LiveData<List<Dog>>{
        if (!sqlTurnedOn){
            return roomRepository.sortDataByName()
        } else {
            return db.getSortedName()
        }
    }
    fun sortDataByAge():LiveData<List<Dog>>{
        if (!sqlTurnedOn){
            return roomRepository.sortDataByAge()
        } else {
            return db.getSortedAge()
        }
    }
    fun sortDataByBreed():LiveData<List<Dog>>{
        if (!sqlTurnedOn){
            return roomRepository.sortDataByBreed()
        } else {
            return db.getSortedBreed()
        }
    }
}