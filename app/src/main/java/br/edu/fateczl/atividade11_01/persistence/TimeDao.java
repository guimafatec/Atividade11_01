package br.edu.fateczl.atividade11_01.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.atividade11_01.model.Jogador;
import br.edu.fateczl.atividade11_01.model.Time;

public class TimeDao implements ITimeDao, ICRUDDao<Time> {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public TimeDao(Context context) {
        this.context = context;
    }

    @Override
    public TimeDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Time time) throws SQLException {
        ContentValues contentValues = getContentValues(time);
        db.insert("time", null, contentValues);
    }

    @Override
    public int update(Time time) throws SQLException {
        ContentValues contentValues = getContentValues(time);
        return db.update("time", contentValues, "codigo = " + time.getCodigo(), null);
    }

    @Override
    public void delete(Time time) throws SQLException {
        db.delete("time", "codigo = " + time.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Time findOne(Time time) throws SQLException {
        String sql = "SELECT j.id AS cod_jog, j.nome AS nome_jog, j.nasc AS nasc_jog, j.altura AS altura_jog, " +
                "j.peso AS peso_jog, t.codigo, t.nome, t.cidade" +
                "FROM jogador j INNER JOIN time t" +
                "ON j.codigo_time = t.codigo" +
                "AND t.codigo = " + time.getCodigo();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            Jogador jogador = new Jogador();
            jogador.setId(cursor.getInt(cursor.getColumnIndex("cod_jog")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nome_jog")));
            jogador.setDataNasc(LocalDate.parse(cursor.getString(cursor.getColumnIndex("nasc_jog"))));
            jogador.setAltura(cursor.getFloat(cursor.getColumnIndex("altura_jog")));
            jogador.setPeso(cursor.getFloat(cursor.getColumnIndex("peso_jog")));

            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));

            jogador.setTime(time);

        }
        cursor.close();
        return time;
    }

    @SuppressLint("Range")
    @Override
    public List<Time> findAll() throws SQLException {
        List<Time> times = new ArrayList<>();
        String sql = "SELECT j.id AS cod_jog, j.nome AS nome_jog, j.nasc AS nasc_jog, j.altura AS altura_jog, " +
                "j.peso AS peso_jog, t.codigo, t.nome, t.cidade" +
                "FROM jogador j INNER JOIN time t" +
                "ON j.codigo_time = t.codigo";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            Jogador jogador = new Jogador();
            jogador.setId(cursor.getInt(cursor.getColumnIndex("cod_jog")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nome_jog")));
            jogador.setDataNasc(LocalDate.parse(cursor.getString(cursor.getColumnIndex("nasc_jog"))));
            jogador.setAltura(cursor.getFloat(cursor.getColumnIndex("altura_jog")));
            jogador.setPeso(cursor.getFloat(cursor.getColumnIndex("peso_jog")));

            Time time = new Time();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));

            jogador.setTime(time);
            times.add(time);
            cursor.moveToNext();
        }
        cursor.close();
        return times;
    }

    private ContentValues getContentValues(Time time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", time.getCodigo());
        contentValues.put("nome", time.getNome());
        contentValues.put("cidade", time.getCidade());
        return contentValues;
    }
}
