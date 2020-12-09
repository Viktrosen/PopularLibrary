package com.hfrad.popularlibrary.navigation;

import androidx.fragment.app.Fragment;

import com.hfrad.popularlibrary.mvp.model.entity.GithubRepository;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.ui.fragments.RepositoryFragment;
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
        private final GithubUser user;

        public UserScreen(GithubUser user) {
            this.user = user;
        }

        @Override
        public Fragment getFragment() {
            return UserFragment.newInstance(user);
        }
    }

    public static class RepositoryScreen extends SupportAppScreen {
        private final GithubRepository repository;

        public RepositoryScreen(GithubRepository repo) {
            this.repository = repo;
        }

        @Override
        public Fragment getFragment() {
            return RepositoryFragment.newInstance(repository);
        }
    }

}
