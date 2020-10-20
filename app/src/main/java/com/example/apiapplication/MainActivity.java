package com.example.apiapplication;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<POST>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<POST>>() {
            @Override
            public void onResponse(Call<List<POST>> call, Response<List<POST>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                }
                List<POST> posts = response.body();

                for (POST post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "Type: " + post.getType() + "\n";
                    content += "Setup: "+ post.getSetup()+"\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<POST>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}

