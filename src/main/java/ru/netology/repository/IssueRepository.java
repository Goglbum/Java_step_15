package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.*;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();
    private TreeSet<Integer> tag = new TreeSet<>();

    public void save(Issue issue){
        issues.add(issue);
        if (tag.isEmpty()) { tag.add(0); }
        int tagTmp = tag.last() + 1;
        tag.add(tagTmp);
        issue.setTag(tag.last());
    }

    public List<Issue> findAll() {return issues; }

    public void removeByTag(int tag) {
        issues.removeIf(p -> p.getTag() == tag);
    }

    public void removeAll(Collection<Issue> collection) {
        issues.removeAll(collection);
    }

    public void saveAll(List<Issue> collection) {
        int sizeIssue = issues.size();
        int iterator = sizeIssue + collection.size();
        issues.addAll(collection);
        if (tag.isEmpty()) { tag.add(0); }
        int tagTmp = tag.last();
        for (int i = sizeIssue ; i < iterator; i++) {
            tagTmp++;
            tag.add(tagTmp);
            issues.get(i).setTag(tag.last());
        }
    }
}
