package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void save(Issue issue){
        issues.add(issue);
    }

    public List<Issue> findAll() {return issues; }

    public void removeById(int id) {
        issues.removeIf(p -> p.getId() == id);
    }

    public void removeAll(Collection<Issue> collection) {
        issues.removeAll(collection);
    }

    public void saveAll(Collection<Issue> collection) {
        issues.addAll(collection);
    }
}
