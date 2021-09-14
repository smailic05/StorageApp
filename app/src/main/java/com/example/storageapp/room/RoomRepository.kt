package com.example.storageapp.room

import com.example.storageapp.ApplicationStorageApp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepository @Inject constructor(private val dao: DatabaseDao) {

    fun insertIntoDatabase( arr: Dog)=dao.insert(arr)
    fun deleteFromDatabase(item: Dog) = dao.delete(item)
    fun updateInDatabase(dog: Dog)=dao.update(dog)
    fun getAllRepositories() = dao.getAllRepositories()
    fun sortDataByName() = dao.sortDataByName()
    fun sortDataByAge() = dao.sortDataByAge()
    fun sortDataByBreed() = dao.sortDataByBreed()
}