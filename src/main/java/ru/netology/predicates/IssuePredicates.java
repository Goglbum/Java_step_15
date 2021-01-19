package ru.netology.predicates;

import ru.netology.domain.Issue;


import java.util.function.Predicate;

public class IssuePredicates {
    public Predicate<Issue> filterAuthor(String author) {
        return p -> p.getAuthor().equalsIgnoreCase(author);
    }

    public static Predicate<Issue> filterLabel(String label) {
        return p -> p.getLabel().contains(label);
    }

    public static Predicate<Issue> filterAssignee(String assignee) {
        return p -> p.getAssignee().equalsIgnoreCase(assignee);
    }
}
