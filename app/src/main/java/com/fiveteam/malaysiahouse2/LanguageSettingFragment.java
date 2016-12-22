package com.fiveteam.malaysiahouse2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageSettingFragment extends Fragment {
    private View rootView;

    public LanguageSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_language_setting, container, false);

        ImageView larrow = (ImageView) rootView.findViewById(R.id.IV_larrow);
        larrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).initFirstPage();
            }
        });

        return rootView;
    }

}
