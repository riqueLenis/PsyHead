package com.example.psyhead.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.psyhead.data.dao.GestorDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.model.Gestor;
import java.util.List;

public class GestorRepository {
    private GestorDao gestorDao;
    private LiveData<List<Gestor>> allGestores;

    public GestorRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        gestorDao = db.gestorDao();
        allGestores = gestorDao.getAllGestores();
    }
    public LiveData<List<Gestor>> getAllGestores() {
        return allGestores;
    }
    public LiveData<Gestor> getGestorById(int gestorId) {
        return gestorDao.getGestorById(gestorId);
    }
    public LiveData<Gestor> getGestorByUserId(int userId) {
        return gestorDao.getGestorByUserId(userId);
    }
    public void insert(Gestor gestor) {
        AppDatabase.databaseWriteExecutor.execute(() -> gestorDao.insert(gestor));
    }
    public void update(Gestor gestor) {
        AppDatabase.databaseWriteExecutor.execute(() -> gestorDao.update(gestor));
    }
    public void delete(Gestor gestor) {
        AppDatabase.databaseWriteExecutor.execute(() -> gestorDao.delete(gestor));
    }
}