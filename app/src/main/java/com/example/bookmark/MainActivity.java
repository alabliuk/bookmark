package com.example.bookmark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmark.dao.UrlDAO;
import com.example.bookmark.model.Url;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUrl = findViewById(R.id.listUrl);

        listUrl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View item, int position, long id) {
                Url url = (Url) listUrl.getItemAtPosition(position);

                Intent intentGoUrlActivity = new Intent(MainActivity.this, UrlActivity.class);
                intentGoUrlActivity.putExtra("url", url);
                startActivity(intentGoUrlActivity);
            }
        });

        Button newUrl = findViewById(R.id.newUrl);
        newUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoUrlActivity = new Intent(MainActivity.this, UrlActivity.class);
                startActivity(intentGoUrlActivity);
            }
        });
        registerForContextMenu(listUrl);
    }

    private void loadList() {
        UrlDAO dao = new UrlDAO(this);
        List<Url> urls = dao.searchUrl();
        dao.close();

        ArrayAdapter<Url> adapter = new ArrayAdapter<Url>(this, android.R.layout.simple_list_item_1, urls);
        listUrl.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Url url = (Url) listUrl.getItemAtPosition(info.position);

                Intent intentGoUrlActivity = new Intent(MainActivity.this, UrlActivity.class);
                intentGoUrlActivity.putExtra("url", url);
                startActivity(intentGoUrlActivity);
                return false;
            }
        });


        MenuItem delete = menu.add("Delete");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Url url = (Url) listUrl.getItemAtPosition(info.position);

                UrlDAO dao = new UrlDAO(MainActivity.this);
                dao.delete(url);
                dao.close();

                loadList();

                Toast.makeText(MainActivity.this, "Excluindo URL:  " + url.getUrl(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
