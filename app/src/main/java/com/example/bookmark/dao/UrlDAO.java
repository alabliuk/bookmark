package com.example.bookmark.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bookmark.model.Url;

import java.util.ArrayList;
import java.util.List;

public class UrlDAO extends SQLiteOpenHelper {

    public UrlDAO(Context context) {
        super(context, "Bookmark", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Urls (id INTEGER PRIMARY KEY, url TEXT NOT NULL, observacao TEXT, ranking REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Urls";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Url url) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getUrl(url);
        db.insert("Urls", null, dados);
    }

    private ContentValues getUrl(Url url) {
        ContentValues data = new ContentValues();
        data.put("url", url.getUrl());
        data.put("observacao", url.getObservacao());
        data.put("ranking", url.getRanking());
        return data;
    }

    public List<Url> searchUrl() {
        String sql = "SELECT * FROM Urls;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Url> urls = new ArrayList<Url>();
        while (c.moveToNext()) {
            Url url = new Url();
            url.setId(c.getLong(c.getColumnIndex("id")));
            url.setUrl(c.getString(c.getColumnIndex("url")));
            url.setObservacao(c.getString(c.getColumnIndex("observacao")));
            url.setRanking(c.getDouble(c.getColumnIndex("ranking")));
            urls.add(url);
        }
        c.close();
        return urls;
    }

    public void delete(Url url) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {url.getId().toString()};
        db.delete("Urls", "id = ?", params);
    }

    public void alter(Url url) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getUrl(url);
        String[] params = {url.getId().toString()};
        db.update("Urls", data, "id = ?", params);
    }

}
