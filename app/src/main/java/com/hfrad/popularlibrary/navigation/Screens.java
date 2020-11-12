package com.hfrad.popularlibrary.navigation;

import androidx.fragment.app.Fragment;


import com.hfrad.popularlibrary.ui.fragments.UserFragment;
import com.hfrad.popularlibrary.ui.fragments.UsersFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class UsersScreen extends SupportAppScreen {

        @Override
        public Fragment getFragment() {
            return UsersFragment.getInstance(0);
        }


    }

    public static class UserScreen extends SupportAppScreen {

        @Override
        public Fragment getFragment() {
            return UserFragment.getInstance(0);
        }


    }

}
