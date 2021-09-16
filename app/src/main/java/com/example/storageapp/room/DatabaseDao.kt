package com.example.storageapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg dogs: Dog)

    @Delete
    fun delete(dog: Dog)

    @Update
    fun update(dog:Dog)

    @Query("SELECT * FROM dogs")
    fun getAllRepositories(): List<Dog>

    @Query("SELECT * FROM dogs ORDER BY name ASC")
    fun sortDataByName(): List<Dog>

    @Query("SELECT * FROM dogs ORDER BY age ASC")
    fun sortDataByAge(): List<Dog>

    @Query("SELECT * FROM dogs ORDER BY breed ASC")
    fun sortDataByBreed(): List<Dog>
}