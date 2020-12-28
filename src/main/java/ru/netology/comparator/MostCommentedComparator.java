package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class MostCommentedComparator implements Comparator<Issue> {
    public int compare(Issue o1, Issue o2) {
        return o2.getNumberOfComments() - o1.getNumberOfComments();
    }
}
