package com.example.psyhead.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.psyhead.model.Terapeuta;
import java.util.List;

@Dao
public interface TerapeutaDao {
    @Insert
    long insert(Terapeuta terapeuta);
    @Update
    void update(Terapeuta terapeuta);
    @Delete
    void delete(Terapeuta terapeuta);
    @Query("SELECT * FROM terapeutas WHERE id = :terapeutaId")
    LiveData<Terapeuta> getTerapeutaById(int terapeutaId);
    @Query("SELECT * FROM terapeutas WHERE userId = :userId")
    LiveData<Terapeuta> getTerapeutaByUserId(int userId);
    @Query("SELECT * FROM terapeutas")
    LiveData<List<Terapeuta>> getAllTerapeutas();
}