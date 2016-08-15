package com.science.drawertogglelikegmail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author 幸运Science
 * @description
 * @email chentushen.science@gmail.com,274240671@qq.com
 * @data 2016/8/14
 */
public class MainFragment extends BaseFragment {

    private MainActivity mMainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mMainActivity = ((MainActivity) getActivity());
        final ImageView img = (ImageView) rootView.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(new DetailsFragment(), mMainActivity, img, "shareImg");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(new OtherFragment(), mMainActivity, null, null);
            }
        });
    }
}
