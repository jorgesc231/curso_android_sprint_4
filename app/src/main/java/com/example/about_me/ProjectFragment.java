package com.example.about_me;

import static androidx.core.content.ContextCompat.getDrawable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjectFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    static final String ARG_PARAM1 = "param1";
    static final String ARG_PARAM2 = "param2";
    static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String name;
    private int picture_id;
    private String url;
    public ProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectFragment newInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            picture_id = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_project, container, false);

        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            picture_id = getArguments().getInt(ARG_PARAM2);
            url = getArguments().getString(ARG_PARAM3);

            TextView tvName = view.findViewById(R.id.tvName);
            tvName.setText(name);

            ImageView ivPicture = view.findViewById(R.id.ivPicture);
            ivPicture.setImageDrawable(getDrawable(view.getContext(), picture_id));

            // Click
            view.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                       startActivity(intent);
                }
            });
        }

        // Inflate the layout for this fragment
        return view;
    }


}