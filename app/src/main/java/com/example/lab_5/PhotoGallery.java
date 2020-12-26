package com.example.lab_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lab_5.api.ServiceAPI;
import com.example.lab_5.db.PhotosDB;
import com.example.lab_5.model.FlickrPhotos;
import com.example.lab_5.model.Photo;
import com.example.lab_5.view.PhotoAdapter;
import com.google.gson.Gson;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoGallery extends AppCompatActivity {

    PhotoAdapter adapter;
    RecyclerView list_view;
    PhotosDB photos_db;
    List<Photo> photos;

    // Настройки приложения
    LocalOption localOption;


    String flickr_api_key;
    // Здесь можно вставить свой токен, чтобы не писать с телефона
    //        flickr_api_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        //Получить ссылку на БД
        photos_db = PhotosDB.getDatabase(this);

        // Инициализация объекта локального хранилища
        localOption = new LocalOption(PhotoGallery.this);

        // адаптер для RecyclerView
        adapter = new PhotoAdapter(this, this);

        list_view = findViewById(R.id.rv_photos);
        GridLayoutManager layout_manager = new GridLayoutManager(this, 3);
        list_view.setLayoutManager(layout_manager);
        list_view.setAdapter(adapter);

        // Задать настройку токена
        flickr_api_key = localOption.getFlickr_api_key();

        //Запросить изображения с flickr.com

    }

    //Изменить главное меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        //Прослушивать элемент поиска
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //Сработает при отправке введенного текста
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            //Сработает при вводе текста
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    // Обработчик нажатий пунктов меню из toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Выбор действия зависит от id элемента в меню
        switch (item.getItemId()) {
            case (R.id.photo_db):

                break;
            case (R.id.photo_flickr):

                break;
            case (R.id.set_token):
                Intent intent = new Intent(PhotoGallery.this, ActivitySetToken.class);
                intent.putExtra("flickr_api_key", flickr_api_key);
                startActivityForResult(intent, 1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Вызывается из PhotoAdapter, когда происходит нажатие на фото
    public void onClick(View view) {
        int position = (int) view.getTag();
        Intent intent = new Intent(this, DetailPhoto.class);
        Gson gson = new Gson();

        intent.putExtra("photo", gson.toJson(photos.get(position)));
        intent.putExtra("position", position);

        startActivityForResult(intent, 1);
    }


}