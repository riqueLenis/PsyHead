package com.example.psyhead.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.psyhead.model.User;
import java.util.List;
@Dao
public interface UserDao {
    @Insert
    long insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);
    @Query("SELECT * FROM users WHERE id = :userId")
    LiveData<User> getUserById(int userId);
    @Query("SELECT * FROM users WHERE email = :email AND senha = :senha")
    LiveData<User> getUserByCredentials(String email, String senha);
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();
    @Query("SELECT * FROM users WHERE tipo_usuario = :tipoUsuario")
    LiveData<List<User>> getUsersByType(String tipoUsuario);
}