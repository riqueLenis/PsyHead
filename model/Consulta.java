package com.example.psyhead.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "consultas",
        foreignKeys = {
                @ForeignKey(entity = Paciente.class,
                        parentColumns = "id",
                        childColumns = "pacienteId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Terapeuta.class,
                        parentColumns = "id",
                        childColumns = "terapeutaId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Consulta {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int pacienteId;
    public int terapeutaId;

    public String data;
    public String hora;
    public String tipo;
    public String status;
    public String observacoes;

    public Consulta(int pacienteId, int terapeutaId, String data, String hora, String tipo, String status, String observacoes) {
        this.pacienteId = pacienteId;
        this.terapeutaId = terapeutaId;
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.status = status;
        this.observacoes = observacoes;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getTerapeutaId() {
        return terapeutaId;
    }

    public void setTerapeutaId(int terapeutaId) {
        this.terapeutaId = terapeutaId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}