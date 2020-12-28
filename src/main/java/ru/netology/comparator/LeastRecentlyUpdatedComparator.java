package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class LeastRecentlyUpdatedComparator implements Comparator<Issue> {
    public int compare(Issue o1, Issue o2) {
        return o2.getUpdateAgo() - o1.getUpdateAgo();
    }
}
