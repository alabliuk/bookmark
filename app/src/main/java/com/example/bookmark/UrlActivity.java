package com.example.bookmark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bookmark.dao.UrlDAO;
import com.example.bookmark.model.Url;

public class UrlActivity extends AppCompatActivity {

    private UrlHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        Intent intent = getIntent();
        Url url = (Url) intent.getSerializableExtra("url");

        helper = new UrlHelper(this);

        if (url != null) {
            helper.setUrl(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSave:
                Url url = helper.getUrl();

                UrlDAO dao = new UrlDAO(this);
                if (url.getId() != null) {
                    dao.alter(url);
                } else {
                    dao.insert(url);
                }
                dao.close();

                Toast.makeText(UrlActivity.this, "Url " + url.getUrl() + " Salva", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
