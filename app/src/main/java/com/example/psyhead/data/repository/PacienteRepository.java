package com.example.psyhead.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.psyhead.data.dao.PacienteDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.model.Paciente;
import java.util.List;

public class PacienteRepository {
    private PacienteDao pacienteDao;
    private LiveData<List<Paciente>> allPacientes;

    public PacienteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        pacienteDao = db.pacienteDao();
        allPacientes = pacienteDao.getAllPacientes();
    }
    public LiveData<List<Paciente>> getAllPacientes() {
        return allPacientes;
    }
    public LiveData<Paciente> getPacienteById(int pacienteId) {
        return pacienteDao.getPacienteById(pacienteId);
    }
    public LiveData<Paciente> getPacienteByUserId(int userId) {
        return pacienteDao.getPacienteByUserId(userId);
    }
    public void insert(Paciente paciente) {
        AppDatabase.databaseWriteExecutor.execute(() -> pacienteDao.insert(paciente));
    }
    public void update(Paciente paciente) {
        AppDatabase.databaseWriteExecutor.execute(() -> pacienteDao.update(paciente));
    }
    public void delete(Paciente paciente) {
        AppDatabase.databaseWriteExecutor.execute(() -> pacienteDao.delete(paciente));
    }
}