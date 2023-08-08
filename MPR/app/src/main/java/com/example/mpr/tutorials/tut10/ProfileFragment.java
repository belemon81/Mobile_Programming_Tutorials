package com.example.mpr.tutorials.tut10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.os.HandlerCompat;
import androidx.fragment.app.Fragment;

import com.example.mpr.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tut10_fragment_profile, container, false);

        Bundle data = getArguments();
        int id = data.getInt("id");

        // load member's info & bind
        // Executors.newFixedThreadPool(4);
        ExecutorService executor = Constants.executorService;
        Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
        executor.execute(() -> {
            // load json - member's info
            String json = loadJson("https://jsonplaceholder.typicode.com/users/" + id);
            handler.post(() -> {
                if (json == null) {
                    Toast.makeText(getContext(), "Oops! Failed to load user. ", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    JSONObject root = new JSONObject(json);
                    String name = root.getString("name");
                    String email = root.getString("email");
//                    JSONObject address = root.getJSONObject("address");
//                    String city = address.getString("city");
                    TextView nameView = view.findViewById(R.id.name);
                    TextView emailView = view.findViewById(R.id.email);
                    nameView.setText(name);
                    emailView.setText(email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        });
        executor.execute(() -> {
            // load json - image
            Bitmap image = downloadImage("https://robohash.org/" + id + "?set=set2");
            handler.post(() -> {
                if (image == null) {
                    Toast.makeText(getContext(), "Oops! Failed to load image. ", Toast.LENGTH_SHORT).show();
                    return;
                }
                ImageView imageView = view.findViewById(R.id.avatar);
                imageView.setImageBitmap(image);
            });
        });
        return view;
    }

    public String loadJson(String link) {
        URL url;
        HttpURLConnection urlConnection;
        try {
            url = new URL(link);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream is = urlConnection.getInputStream();
            Scanner sc = new Scanner(is);
            StringBuilder result = new StringBuilder();
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                result.append(line);
            }
            return result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap downloadImage(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}