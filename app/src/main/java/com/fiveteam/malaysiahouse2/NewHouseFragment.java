package com.fiveteam.malaysiahouse2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewHouseFragment extends Fragment {
    private View rootView;

    public NewHouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new_house, container, false);

        ImageView larrow = (ImageView) rootView.findViewById(R.id.IV_larrow);
        larrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).initFirstPage();
            }
        });

        ImageView ivNewhouse = (ImageView) rootView.findViewById(R.id.IV_newhouse_pic1) ;
        ivNewhouse.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                HouseInfoFragment frg = new  HouseInfoFragment();
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragMgr.beginTransaction();
                transaction.replace(R.id.FL_dresses, frg, frg.getClass().getName()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
