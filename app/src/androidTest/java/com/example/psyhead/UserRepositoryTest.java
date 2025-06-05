package com.example.psyhead;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.psyhead.data.dao.UserDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.data.repository.UserRepository;
import com.example.psyhead.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
    @Mock
    private UserDao mockUserDao;

    @Mock
    private Application mockApplication;

    private UserRepository userRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AppDatabase mockDatabase = mock(AppDatabase.class);
        when(mockDatabase.userDao()).thenReturn(mockUserDao);

        try {
            java.lang.reflect.Field field = AppDatabase.class.getDeclaredField("INSTANCE");
            field.setAccessible(true);
            field.set(null, mockDatabase);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            java.lang.reflect.Field executorField = AppDatabase.class.getDeclaredField("databaseWriteExecutor");
            executorField.setAccessible(true);
            Executor directExecutor = Runnable::run;
            executorField.set(null, directExecutor);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        userRepository = new UserRepository(mockApplication);
    }

    @Test
    public void insertUser_callsDaoInsert() {
        User newUser = new User("Test User", "test@example.com", "password", "paciente");
        userRepository.insert(newUser);
        verify(mockUserDao).insert(newUser);
    }

    @Test
    public void updateUser_callsDaoUpdate() {
        User existingUser = new User("Existing User", "existing@example.com", "oldpass", "terapeuta");
        existingUser.setId(1);
        userRepository.update(existingUser);
        verify(mockUserDao).update(existingUser);
    }

    @Test
    public void deleteUser_callsDaoDelete() {
        User userToDelete = new User("Delete Me", "delete@example.com", "pass", "gestor");
        userToDelete.setId(2);
        userRepository.delete(userToDelete);
        verify(mockUserDao).delete(userToDelete);
    }

    @Test
    public void getAllUsers_returnsAllUsersFromDao() {
        User user1 = new User("User 1", "user1@test.com", "pass1", "paciente");
        User user2 = new User("User 2", "user2@test.com", "pass2", "terapeuta");
        List<User> userList = Arrays.asList(user1, user2);

        MutableLiveData<List<User>> liveDataUsers = new MutableLiveData<>();
        liveDataUsers.setValue(userList);

        when(mockUserDao.getAllUsers()).thenReturn(liveDataUsers);

        LiveData<List<User>> result = userRepository.getAllUsers();
        verify(mockUserDao).getAllUsers();
    }

    @Test
    public void getUserByCredentials_returnsUserFromDao() {
        String email = "login@example.com";
        String senha = "password";
        User foundUser = new User("Logged User", email, senha, "paciente");

        MutableLiveData<User> liveDataUser = new MutableLiveData<>();
        liveDataUser.setValue(foundUser);

        when(mockUserDao.getUserByCredentials(email, senha)).thenReturn(liveDataUser);

        LiveData<User> result = userRepository.getUserByCredentials(email, senha);
        verify(mockUserDao).getUserByCredentials(email, senha);
    }
}
