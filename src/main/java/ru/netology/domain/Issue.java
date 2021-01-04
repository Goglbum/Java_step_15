package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Issue implements Comparable<Issue> {
    private int id;
    private String author;
    private List<String> label;
    private String project;
    private String milestones;
    private String assignee;
    private boolean open;
    private int daysAgoOpen;
    private int numberOfComments;
    private int updateAgo;

    @Override
    public int compareTo(Issue o) {
        return daysAgoOpen - o.daysAgoOpen;
    }

    public boolean openIssue() {
        if (open) {
            return true;
        }
        return false;
    }
}
