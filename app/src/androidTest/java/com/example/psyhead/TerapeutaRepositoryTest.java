package com.example.psyhead;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import com.example.psyhead.data.dao.TerapeutaDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.data.repository.TerapeutaRepository;
import com.example.psyhead.model.Terapeuta;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.Executor;

@RunWith(MockitoJUnitRunner.class)
public class TerapeutaRepositoryTest {

    @Mock
    private TerapeutaDao mockTerapeutaDao;

    @Mock
    private Application mockApplication;

    private TerapeutaRepository terapeutaRepository;

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        AppDatabase mockDatabase = mock(AppDatabase.class);
        when(mockDatabase.terapeutaDao()).thenReturn(mockTerapeutaDao);

        java.lang.reflect.Field field = AppDatabase.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        field.set(null, mockDatabase);

        java.lang.reflect.Field executorField = AppDatabase.class.getDeclaredField("databaseWriteExecutor");
        executorField.setAccessible(true);
        Executor directExecutor = Runnable::run;
        executorField.set(null, directExecutor);

        terapeutaRepository = new TerapeutaRepository(mockApplication);
    }

    @Test
    public void insertTerapeuta_callsDaoInsert() {
        Terapeuta newTerapeuta = new Terapeuta(1);
        terapeutaRepository.insert(newTerapeuta);
        verify(mockTerapeutaDao).insert(newTerapeuta);
    }

    @Test
    public void updateTerapeuta_callsDaoUpdate() {
        Terapeuta existingTerapeuta = new Terapeuta(2);
        existingTerapeuta.setId(1);
        terapeutaRepository.update(existingTerapeuta);
        verify(mockTerapeutaDao).update(existingTerapeuta);
    }

    @Test
    public void deleteTerapeuta_callsDaoDelete() {
        Terapeuta terapeutaToDelete = new Terapeuta(3);
        terapeutaToDelete.setId(2);
        terapeutaRepository.delete(terapeutaToDelete);
        verify(mockTerapeutaDao).delete(terapeutaToDelete);
    }

    @Test
    public void getTerapeutaById_callsDaoGetTerapeutaById() {
        terapeutaRepository.getTerapeutaById(1);
        verify(mockTerapeutaDao).getTerapeutaById(1);
    }

    @Test
    public void getTerapeutaByUserId_callsDaoGetTerapeutaByUserId() {
        terapeutaRepository.getTerapeutaByUserId(10);
        verify(mockTerapeutaDao).getTerapeutaByUserId(10);
    }

    @Test
    public void getAllTerapeutas_callsDaoGetAllTerapeutas() {
        terapeutaRepository.getAllTerapeutas();
        verify(mockTerapeutaDao).getAllTerapeutas();
    }
}