package com.example.psyhead.data.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.psyhead.data.dao.GestorDao;
import com.example.psyhead.data.dao.ConsultaDao;
import com.example.psyhead.data.dao.PacienteDao;
import com.example.psyhead.data.dao.TerapeutaDao;
import com.example.psyhead.data.dao.UserDao;
import com.example.psyhead.model.Consulta;
import com.example.psyhead.model.Terapeuta;
import com.example.psyhead.model.Gestor;
import com.example.psyhead.model.Paciente;
import com.example.psyhead.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {User.class, Paciente.class, Terapeuta.class, Gestor.class, Consulta.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract PacienteDao pacienteDao();
    public abstract TerapeutaDao terapeutaDao();
    public abstract GestorDao gestorDao();
    public abstract ConsultaDao consultaDao();
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "consulta_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}