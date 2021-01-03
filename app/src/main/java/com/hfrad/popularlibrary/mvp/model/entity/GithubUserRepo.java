package com.hfrad.popularlibrary.mvp.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GithubUserRepo {
    private List<Number> repositories = new ArrayList<>(Arrays.asList(
            new Number("0", "login1", "",""),
            new Number("1", "login1", "",""),
            new Number("1", "login1", "",""),
            new Number("1", "login1", "",""),
            new Number("1", "login1", "","")));

    public List<Number> getUsers() {
        return Collections.unmodifiableList(repositories);
    }
}
