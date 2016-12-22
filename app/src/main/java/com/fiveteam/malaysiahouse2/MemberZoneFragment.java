package com.fiveteam.malaysiahouse2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberZoneFragment extends Fragment {
    private View rootView;
    private ScrollView loginForm, memberForm;
    private EditText etId, etPw;
    private LinearLayout fixForm;
    private ImageView memFace;
    private TextView memName;

    public MemberZoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_member_zone, container, false);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        setHasOptionsMenu(true);

        loginForm = (ScrollView) rootView.findViewById(R.id.SV_login_form);
        memberForm = (ScrollView) rootView.findViewById(R.id.SV_member_form);
        fixForm = (LinearLayout) rootView.findViewById(R.id.LL_member_fix);
        etId = (EditText) rootView.findViewById(R.id.ET_fmz_id);
        etPw = (EditText) rootView.findViewById(R.id.ET_fmz_pw);
        memFace = (ImageView) rootView.findViewById(R.id.IV_member_face);
        memName = (TextView) rootView.findViewById(R.id.TV_member_name);

        TextView tvLogin = (TextView) rootView.findViewById(R.id.TV_login_button);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkId();
            }
        });

        TextView tvLogout = (TextView) rootView.findViewById(R.id.TV_logout_button);
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        TextView tvMemberfix = (TextView) rootView.findViewById(R.id.TV_member_fix);
        tvMemberfix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fixInfo();
            }
        });

        TextView tvfmSave = (TextView) rootView.findViewById(R.id.TV_fm_save);
        tvfmSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fixSave();
            }
        });

        ImageView larrow = (ImageView) rootView.findViewById(R.id.IV_larrow);
        larrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).initFirstPage();
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.e(TAG, "onCreateOptionsMenu()");
        menu.clear();
        //inflater.inflate(R.menu.main, menu);
    }

    public void checkId(){
        String id = etId.getText().toString();
        String pw = etPw.getText().toString();

        if(id.equals("A12345") && pw.equals("A12345")){
            member_logged.member_loggin = true;
            ((MainActivity)getActivity()).updateDrawerHeader();
            loginForm.setVisibility(View.GONE);
            memberForm.setVisibility(View.VISIBLE);
            fixForm.setVisibility(View.GONE);

            memFace.setImageResource(R.drawable.sidebar_avatar);
            memName.setText(R.string.member_a12345);
        }else{
            Toast.makeText(getActivity(), "帳號或密碼輸入錯誤！", Toast.LENGTH_SHORT).show();
        }

    }

    public void logOut(){
        member_logged.member_loggin = false;
        ((MainActivity)getActivity()).updateDrawerHeader();
        loginForm.setVisibility(View.VISIBLE);
        memberForm.setVisibility(View.GONE);
        fixForm.setVisibility(View.GONE);
        memFace.setImageResource(R.drawable.sidebar_avatar_shadow);
        memName.setText(R.string.guest);
    }

    public void fixInfo(){
        member_logged.member_loggin = true;
        ((MainActivity)getActivity()).updateDrawerHeader();
        loginForm.setVisibility(View.GONE);
        memberForm.setVisibility(View.VISIBLE);
        fixForm.setVisibility(View.VISIBLE);
    }

    public void fixSave(){
        member_logged.member_loggin = true;
        ((MainActivity)getActivity()).updateDrawerHeader();
        loginForm.setVisibility(View.GONE);
        memberForm.setVisibility(View.VISIBLE);
        fixForm.setVisibility(View.GONE);
    }

}
