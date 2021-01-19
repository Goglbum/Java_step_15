package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Issue implements Comparable<Issue> {
    private  int tag;
    private String author;
    private List<String> label;
    private String project;
    private String milestones;
    private String assignee;
    private boolean open;
    private int daysAgoOpen;
    private int numberOfComments;
    private int updateAgo;

    public Issue(String author, List<String> label, String project, String milestones, String assignee, boolean open, int daysAgoOpen, int numberOfComments, int updateAgo) {
        this.author = author;
        this.label = label;
        this.project = project;
        this.milestones = milestones;
        this.assignee = assignee;
        this.open = open;
        this.daysAgoOpen = daysAgoOpen;
        this.numberOfComments = numberOfComments;
        this.updateAgo = updateAgo;
    }

    @Override
    public int compareTo(Issue o) {
        return daysAgoOpen - o.daysAgoOpen;
    }

    public boolean openIssue() {
        return open;
    }
}
