package com.example.mpr.tutorials.tut9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.mpr.R;

public class WebViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tut9_fragment_web_view, container, false);

        WebView webView = view.findViewById(R.id.webView);

        Bundle data = getArguments();
        String url = data.getString("url");

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        Button btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
        return view;
    }
}