package com.example.psyhead.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.psyhead.model.Consulta;
import java.util.List;
@Dao
public interface ConsultaDao {
    @Insert
    long insert(Consulta consulta);
    @Update
    void update(Consulta consulta);
    @Delete
    void delete(Consulta consulta);
    @Query("SELECT * FROM consultas WHERE id = :consultaId")
    LiveData<Consulta> getConsultaById(int consultaId);
    @Query("SELECT * FROM consultas WHERE pacienteId = :pacienteId ORDER BY data DESC, hora DESC")
    LiveData<List<Consulta>> getConsultasByPaciente(int pacienteId);
    @Query("SELECT * FROM consultas WHERE terapeutaId = :terapeutaId ORDER BY data DESC, hora DESC")
    LiveData<List<Consulta>> getConsultasByTerapeuta(int terapeutaId);
    @Query("SELECT * FROM consultas ORDER BY data DESC, hora DESC")
    LiveData<List<Consulta>> getAllConsultas();
}