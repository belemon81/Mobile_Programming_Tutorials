package com.example.mpr.tutorials.tut9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mpr.R;

public class BookmarksFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tut9_fragment_bookmarks, container, false);
        LinearLayout zingmp3 = view.findViewById(R.id.zingmp3);
        LinearLayout baomoi = view.findViewById(R.id.baomoi);
        LinearLayout pccovid = view.findViewById(R.id.pccovid);
        LinearLayout vnexpress = view.findViewById(R.id.vnexpress);
        zingmp3.setOnClickListener(view1 -> handleClick(view1));
        baomoi.setOnClickListener(view2 -> handleClick(view2));
        pccovid.setOnClickListener(view3 -> handleClick(view3));
        vnexpress.setOnClickListener(view4 -> handleClick(view4));
        return view;
    }

    public void handleClick(View view) {
        String url = " ";
        switch (view.getId()) {
            case R.id.zingmp3:
                url = "https://mp3.zing.vn/";
                break;
            case R.id.baomoi:
                url = "https://baomoi.com/";
                break;
            case R.id.pccovid:
                url = "https://pccovid.gov.vn/";
                break;
            case R.id.vnexpress:
                url = "https://vnexpress.net/";
                break;
        }

        //pass data into fragment
        WebViewFragment webViewFragment = new WebViewFragment();
        // getActivity()
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Bundle data = new Bundle();
        data.putString("url", url);
        webViewFragment.setArguments(data);
        manager.beginTransaction()
                .replace(R.id.fragmentContainer, webViewFragment)
                .addToBackStack("home")
                .commit();
    }
}