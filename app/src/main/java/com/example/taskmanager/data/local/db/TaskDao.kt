package com.example.taskmanager.data.local.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanager.model.Task

interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>
    @Insert
  fun insert(task: Task)
  @Delete
  fun delete(task: Task)

  @Update
  fun update(task: Task)
}