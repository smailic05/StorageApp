package com.example.storageapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg dogs: Dog)

    @Delete
    fun delete(terminals: Dog)

    @Query("SELECT * FROM dogs")
    fun getAllRepositories(): LiveData<List<Dog>>

    @Query("SELECT * FROM dogs ORDER BY name ASC")
    fun sortDataByName(): LiveData<List<Dog>>

    @Query("SELECT * FROM dogs ORDER BY age ASC")
    fun sortDataByAge(): LiveData<List<Dog>>

    @Query("SELECT * FROM dogs ORDER BY breed ASC")
    fun sortDataByBreed(): LiveData<List<Dog>>
}