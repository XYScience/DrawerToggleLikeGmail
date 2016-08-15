package com.science.drawertogglelikegmail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 幸运Science
 * @description
 * @email chentushen.science@gmail.com,274240671@qq.com
 * @data 2016/8/14
 */
public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void startFragment(Fragment fragment, MainActivity mainActivity, View view, String shareElement) {
        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragment.setEnterTransition(new Fade());
        fragment.setSharedElementReturnTransition(new DetailsTransition());
        setExitTransition(new Fade());

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (view != null) {
            transaction.addSharedElement(view, shareElement);
        }
        transaction.replace(R.id.content, fragment).addToBackStack(null).commit();
        mainActivity.mArrowToggle.toggle();
    }
}
