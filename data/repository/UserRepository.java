package com.example.psyhead.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.psyhead.data.dao.UserDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.model.User;
import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public LiveData<User> getUserById(int userId) {
        return userDao.getUserById(userId);
    }
    public LiveData<User> getUserByCredentials(String email, String senha) {
        return userDao.getUserByCredentials(email, senha);
    }
    public LiveData<List<User>> getUsersByType(String tipoUsuario) {
        return userDao.getUsersByType(tipoUsuario);
    }
    public void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }
    public void update(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.update(user));
    }
    public void delete(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.delete(user));
    }
}