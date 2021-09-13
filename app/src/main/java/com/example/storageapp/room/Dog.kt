package com.example.storageapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")//add id
data class Dog(@PrimaryKey(autoGenerate = true)
               val id:Long=0,
               var name:String,
               var age:Int,
               var breed:String
               )
