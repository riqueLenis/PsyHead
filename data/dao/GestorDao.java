package com.example.psyhead.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.psyhead.model.Gestor;
import java.util.List;
@Dao
public interface GestorDao {
    @Insert
    long insert(Gestor gestor);
    @Update
    void update(Gestor gestor);
    @Delete
    void delete(Gestor gestor);
    @Query("SELECT * FROM gestores WHERE id = :gestorId")
    LiveData<Gestor> getGestorById(int gestorId);
    @Query("SELECT * FROM gestores WHERE userId = :userId")
    LiveData<Gestor> getGestorByUserId(int userId);
    @Query("SELECT * FROM gestores")
    LiveData<List<Gestor>> getAllGestores();
}