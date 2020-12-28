package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class NewIssueComparator implements Comparator<Issue> {
    public int compare(Issue o1, Issue o2) {
        return o1.getDaysAgoOpen() - o2.getDaysAgoOpen();
    }
}
