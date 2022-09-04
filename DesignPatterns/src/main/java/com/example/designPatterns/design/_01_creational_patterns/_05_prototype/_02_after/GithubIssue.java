package com.example.design._01_creational_patterns._05_prototype._02_after;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Data
@EqualsAndHashCode
public class GithubIssue implements Cloneable {

    private int id;

    private String title;

    private GithubRepository repository;

    public GithubIssue(GithubRepository repository) {
        this.repository = repository;
    }

    public String getUrl() {
        return String.format("https://github.com/%s/%s/issues/%d",
                repository.getUser(),
                repository.getName(),
                this.getId());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
