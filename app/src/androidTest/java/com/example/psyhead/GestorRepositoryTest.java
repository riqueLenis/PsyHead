package com.example.psyhead;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import com.example.psyhead.data.dao.GestorDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.data.repository.GestorRepository;
import com.example.psyhead.model.Gestor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.Executor;

@RunWith(MockitoJUnitRunner.class)
public class GestorRepositoryTest {

    @Mock
    private GestorDao mockGestorDao;

    @Mock
    private Application mockApplication;

    private GestorRepository gestorRepository;

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        AppDatabase mockDatabase = mock(AppDatabase.class);
        when(mockDatabase.gestorDao()).thenReturn(mockGestorDao);

        java.lang.reflect.Field field = AppDatabase.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        field.set(null, mockDatabase);

        java.lang.reflect.Field executorField = AppDatabase.class.getDeclaredField("databaseWriteExecutor");
        executorField.setAccessible(true);
        Executor directExecutor = Runnable::run;
        executorField.set(null, directExecutor);

        gestorRepository = new GestorRepository(mockApplication);
    }

    @Test
    public void insertGestor_callsDaoInsert() {
        Gestor newGestor = new Gestor(1);
        gestorRepository.insert(newGestor);
        verify(mockGestorDao).insert(newGestor);
    }

    @Test
    public void updateGestor_callsDaoUpdate() {
        Gestor existingGestor = new Gestor(2);
        existingGestor.setId(1);
        gestorRepository.update(existingGestor);
        verify(mockGestorDao).update(existingGestor);
    }

    @Test
    public void deleteGestor_callsDaoDelete() {
        Gestor gestorToDelete = new Gestor(3);
        gestorToDelete.setId(2);
        gestorRepository.delete(gestorToDelete);
        verify(mockGestorDao).delete(gestorToDelete);
    }

    @Test
    public void getGestorById_callsDaoGetGestorById() {
        gestorRepository.getGestorById(1);
        verify(mockGestorDao).getGestorById(1);
    }

    @Test
    public void getGestorByUserId_callsDaoGetGestorByUserId() {
        gestorRepository.getGestorByUserId(10);
        verify(mockGestorDao).getGestorByUserId(10);
    }

    @Test
    public void getAllGestores_callsDaoGetAllGestores() {
        gestorRepository.getAllGestores();
        verify(mockGestorDao).getAllGestores();
    }
}