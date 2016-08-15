package com.science.drawertogglelikegmail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author 幸运Science
 * @description
 * @email chentushen.science@gmail.com,274240671@qq.com
 * @data 2016/8/15
 */
public class MenuFragment extends BaseFragment {

    public static MenuFragment newInstance(String menu) {

        Bundle args = new Bundle();
        args.putString("menu", menu);
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        TextView menu = (TextView) rootView.findViewById(R.id.menu);
        Bundle bundle = getArguments();
        menu.setText(bundle.getString("menu"));

        return rootView;
    }
}
