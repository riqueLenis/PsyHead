package com.example.psyhead.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.psyhead.model.Paciente;
import java.util.List;

@Dao
public interface PacienteDao {
    @Insert
    long insert(Paciente paciente);
    @Update
    void update(Paciente paciente);
    @Delete
    void delete(Paciente paciente);
    @Query("SELECT * FROM pacientes WHERE id = :pacienteId")
    LiveData<Paciente> getPacienteById(int pacienteId);

    @Query("SELECT * FROM pacientes WHERE userId = :userId")
    LiveData<Paciente> getPacienteByUserId(int userId);

    @Query("SELECT * FROM pacientes")
    LiveData<List<Paciente>> getAllPacientes();
}