package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;
import ru.netology.predicates.IssuePredicates;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


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

    public static List<Issue> filterIssues(List<Issue> issues, Predicate<Issue> predicate) {
        return issues.stream().filter(predicate).collect(Collectors.<Issue>toList());
    }

    public List<Issue> filterAuthorByOpen(String author, Comparator<Issue> comparator) {
        List<Issue> issues = findAllOpen(comparator);
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterAuthor(author));
    }

    public List<Issue> filterAuthorByOpen(String author) {
        List<Issue> issues = findAllOpen();
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterAuthor(author));
    }

    public List<Issue> filterLabelByOpen(String author, Comparator<Issue> comparator) {
        List<Issue> issues = findAllOpen(comparator);
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterLabel(author));
    }

    public List<Issue> filterLabelByOpen(String label) {
        List<Issue> issues = findAllOpen();
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterLabel(label));
    }

    public List<Issue> filterAssigneeByOpen(String assignee, Comparator<Issue> comparator) {
        List<Issue> issues = findAllOpen(comparator);
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterAssignee(assignee));
    }

    public List<Issue> filterAssigneeByOpen(String assignee) {
        List<Issue> issues = findAllOpen();
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterAssignee(assignee));
    }

    public List<Issue> filterAuthorByClose(String author, Comparator<Issue> comparator) {
        List<Issue> issues = findAllClose(comparator);
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterAuthor(author));
    }

    public List<Issue> filterAuthorByClose(String author) {
        List<Issue> issues = findAllClose();
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterAuthor(author));
    }

    public List<Issue> filterLabelByClose(String author, Comparator<Issue> comparator) {
        List<Issue> issues = findAllClose(comparator);
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterLabel(author));
    }

    public List<Issue> filterLabelByClose(String label) {
        List<Issue> issues = findAllClose();
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterLabel(label));
    }

    public List<Issue> filterAssigneeByClose(String assignee, Comparator<Issue> comparator) {
        List<Issue> issues = findAllClose(comparator);
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterAssignee(assignee));
    }

    public List<Issue> filterAssigneeByClose(String assignee) {
        List<Issue> issues = findAllClose();
        IssuePredicates predicates = new IssuePredicates();
        return filterIssues(issues, predicates.filterAssignee(assignee));
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
