package com.example.psyhead;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import com.example.psyhead.data.dao.ConsultaDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.data.repository.ConsultaRepository;
import com.example.psyhead.model.Consulta;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.Executor;

@RunWith(MockitoJUnitRunner.class)
public class ConsultaRepositoryTest {

    @Mock
    private ConsultaDao mockConsultaDao;

    @Mock
    private Application mockApplication;

    private ConsultaRepository consultaRepository;

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        AppDatabase mockDatabase = mock(AppDatabase.class);
        when(mockDatabase.consultaDao()).thenReturn(mockConsultaDao);

        java.lang.reflect.Field field = AppDatabase.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        field.set(null, mockDatabase);

        java.lang.reflect.Field executorField = AppDatabase.class.getDeclaredField("databaseWriteExecutor");
        executorField.setAccessible(true);
        Executor directExecutor = Runnable::run;
        executorField.set(null, directExecutor);

        consultaRepository = new ConsultaRepository(mockApplication);
    }

    @Test
    public void insertConsulta_callsDaoInsert() {
        Consulta newConsulta = new Consulta(1, 1, "2025-06-05", "10:00", "online", "agendada", "Primeira consulta");
        consultaRepository.insert(newConsulta);
        verify(mockConsultaDao).insert(newConsulta);
    }

    @Test
    public void updateConsulta_callsDaoUpdate() {
        Consulta existingConsulta = new Consulta(1, 1, "2025-06-06", "11:00", "presencial", "agendada", "Segunda consulta");
        existingConsulta.setId(1);
        consultaRepository.update(existingConsulta);
        verify(mockConsultaDao).update(existingConsulta);
    }

    @Test
    public void deleteConsulta_callsDaoDelete() {
        Consulta consultaToDelete = new Consulta(1, 1, "2025-06-07", "12:00", "online", "cancelada", "Consulta teste");
        consultaToDelete.setId(2);
        consultaRepository.delete(consultaToDelete);
        verify(mockConsultaDao).delete(consultaToDelete);
    }

    @Test
    public void getConsultaById_callsDaoGetConsultaById() {
        consultaRepository.getConsultaById(1);
        verify(mockConsultaDao).getConsultaById(1);
    }

    @Test
    public void getConsultasByPaciente_callsDaoGetConsultasByPaciente() {
        consultaRepository.getConsultasByPaciente(10);
        verify(mockConsultaDao).getConsultasByPaciente(10);
    }

    @Test
    public void getConsultasByTerapeuta_callsDaoGetConsultasByTerapeuta() {
        consultaRepository.getConsultasByTerapeuta(20);
        verify(mockConsultaDao).getConsultasByTerapeuta(20);
    }

    @Test
    public void getAllConsultas_callsDaoGetAllConsultas() {
        consultaRepository.getAllConsultas();
        verify(mockConsultaDao).getAllConsultas();
    }
}