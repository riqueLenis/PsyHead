package com.example.psyhead.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.psyhead.data.dao.TerapeutaDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.model.Terapeuta;
import java.util.List;

public class TerapeutaRepository {
    private TerapeutaDao terapeutaDao;
    private LiveData<List<Terapeuta>> allTerapeutas;

    public TerapeutaRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        terapeutaDao = db.terapeutaDao();
        allTerapeutas = terapeutaDao.getAllTerapeutas();
    }
    public LiveData<List<Terapeuta>> getAllTerapeutas() {
        return allTerapeutas;
    }
    public LiveData<Terapeuta> getTerapeutaById(int terapeutaId) {
        return terapeutaDao.getTerapeutaById(terapeutaId);
    }
    public LiveData<Terapeuta> getTerapeutaByUserId(int userId) {
        return terapeutaDao.getTerapeutaByUserId(userId);
    }
    public void insert(Terapeuta terapeuta) {
        AppDatabase.databaseWriteExecutor.execute(() -> terapeutaDao.insert(terapeuta));
    }
    public void update(Terapeuta terapeuta) {
        AppDatabase.databaseWriteExecutor.execute(() -> terapeutaDao.update(terapeuta));
    }
    public void delete(Terapeuta terapeuta) {
        AppDatabase.databaseWriteExecutor.execute(() -> terapeutaDao.delete(terapeuta));
    }
}