package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.entity.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insertAll(users: List<User>)

    @Delete
    fun delete(user: User)

}