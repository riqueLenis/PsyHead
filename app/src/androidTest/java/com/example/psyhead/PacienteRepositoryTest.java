package com.example.psyhead;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import com.example.psyhead.data.dao.PacienteDao;
import com.example.psyhead.data.database.AppDatabase;
import com.example.psyhead.data.repository.GestorRepository;
import com.example.psyhead.data.repository.PacienteRepository;
import com.example.psyhead.model.Paciente;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.Executor;

@RunWith(MockitoJUnitRunner.class)
public class PacienteRepositoryTest {

    @Mock
    private PacienteDao mockPacienteDao;

    @Mock
    private Application mockApplication;

    private PacienteRepository pacienteRepository;

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        AppDatabase mockDatabase = mock(AppDatabase.class);
        when(mockDatabase.pacienteDao()).thenReturn(mockPacienteDao);

        java.lang.reflect.Field field = AppDatabase.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        field.set(null, mockDatabase);

        java.lang.reflect.Field executorField = AppDatabase.class.getDeclaredField("databaseWriteExecutor");
        executorField.setAccessible(true);
        Executor directExecutor = Runnable::run;
        executorField.set(null, directExecutor);

        pacienteRepository = new PacienteRepository(mockApplication);
    }

    @Test
    public void insertPaciente_callsDaoInsert() {
        Paciente newPaciente = new Paciente(1);
        pacienteRepository.insert(newPaciente);
        verify(mockPacienteDao).insert(newPaciente);
    }

    @Test
    public void updatePaciente_callsDaoUpdate() {
        Paciente existingPaciente = new Paciente(2);
        existingPaciente.setId(1);
        pacienteRepository.update(existingPaciente);
        verify(mockPacienteDao).update(existingPaciente);
    }

    @Test
    public void deletePaciente_callsDaoDelete() {
        Paciente pacienteToDelete = new Paciente(3);
        pacienteToDelete.setId(2);
        pacienteRepository.delete(pacienteToDelete);
        verify(mockPacienteDao).delete(pacienteToDelete);
    }

    @Test
    public void getPacienteById_callsDaoGetPacienteById() {
        pacienteRepository.getPacienteById(1);
        verify(mockPacienteDao).getPacienteById(1);
    }

    @Test
    public void getPacienteByUserId_callsDaoGetPacienteByUserId() {
        pacienteRepository.getPacienteByUserId(10);
        verify(mockPacienteDao).getPacienteByUserId(10);
    }

    @Test
    public void getAllPacientes_callsDaoGetAllPacientes() {
        pacienteRepository.getAllPacientes();
        verify(mockPacienteDao).getAllPacientes();
    }
}
