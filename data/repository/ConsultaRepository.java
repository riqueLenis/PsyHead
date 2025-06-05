package com.example.psyhead.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.psyhead.data.dao.ConsultaDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.model.Consulta;
import java.util.List;

public class ConsultaRepository {
    private ConsultaDao consultaDao;
    private LiveData<List<Consulta>> allConsultas;

    public ConsultaRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        consultaDao = db.consultaDao();
        allConsultas = consultaDao.getAllConsultas();
    }
    public LiveData<List<Consulta>> getAllConsultas() {
        return allConsultas;
    }
    public LiveData<Consulta> getConsultaById(int consultaId) {
        return consultaDao.getConsultaById(consultaId);
    }
    public LiveData<List<Consulta>> getConsultasByPaciente(int pacienteId) {
        return consultaDao.getConsultasByPaciente(pacienteId);
    }
    public LiveData<List<Consulta>> getConsultasByTerapeuta(int terapeutaId) {
        return consultaDao.getConsultasByTerapeuta(terapeutaId);
    }
    public void insert(Consulta consulta) {
        AppDatabase.databaseWriteExecutor.execute(() -> consultaDao.insert(consulta));
    }
    public void update(Consulta consulta) {
        AppDatabase.databaseWriteExecutor.execute(() -> consultaDao.update(consulta));
    }
    public void delete(Consulta consulta) {
        AppDatabase.databaseWriteExecutor.execute(() -> consultaDao.delete(consulta));
    }
}