package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.dao.UserDao
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.entity.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}