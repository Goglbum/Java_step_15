package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static ru.netology.predicates.IssuePredicates.filterIssues;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IssueManager {
    private IssueRepository repository;

    public void add(Issue issue) { repository.save(issue);}

    public List<Issue> findAllOpen(Comparator<Issue> comparator) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.openIssue()) {
                result.add(issue);
            }
        }
        result.sort(comparator);
        return result;
    }

    public List<Issue> findAllOpen() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.openIssue()) {
                result.add(issue);
            }
        }
        result.sort(Issue::compareTo);
        return result;
    }

    public List<Issue> findAllClose(Comparator<Issue> comparator) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (!issue.openIssue()) {
                result.add(issue);
            }
        }
        result.sort(comparator);
        return result;
    }

    public List<Issue> findAllClose() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (!issue.openIssue()) {
                result.add(issue);
            }
        }
        result.sort(Issue::compareTo);
        return result;
    }

    public List<Issue> filterByOpen(Predicate<Issue> predicate, Comparator<Issue> comparator) {
        List<Issue> issues = findAllOpen(comparator);
        return filterIssues(issues, predicate);
    }

    public List<Issue> filterByOpen(Predicate<Issue> predicate) {
        List<Issue> issues = findAllOpen();
        return filterIssues(issues, predicate);
    }

    public List<Issue> filterByClose(Predicate<Issue> predicate, Comparator<Issue> comparator) {
        List<Issue> issues = findAllClose(comparator);
        return filterIssues(issues, predicate);
    }

    public List<Issue> filterByClose(Predicate<Issue> predicate) {
        List<Issue> issues = findAllClose();
        return filterIssues(issues, predicate);
    }

    public void closeById(int id) {
        for (Issue issue : findAllOpen()) {
            if (issue.getId() == id) {
                issue.setOpen(false);
                return;
            }
        }
        throw new NotFoundException("Element with id: " + id + " not found");
    }

}
