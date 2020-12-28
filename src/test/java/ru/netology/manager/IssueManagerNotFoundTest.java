package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.comparator.*;
import ru.netology.domain.Issue;
import ru.netology.predicates.IssuePredicates;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class IssueManagerNotFoundTest {
    @Mock
    private IssueRepository repository;
    @InjectMocks
    private IssueManager manager;

    @BeforeEach
    public void setUp() {
        List<Issue> returned = List.of();
        doReturn(returned).when(repository).findAll();
    }

    @Test
    public void findAllOpenDefault() {
        List<Issue> actual = manager.findAllOpen();
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortNewIssue() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortOldIssue() {
        OldIssueComparator comparator = new OldIssueComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortMostCommented() {
        MostCommentedComparator comparator = new MostCommentedComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortLeastCommented() {
        LeastCommentedComparator comparator = new LeastCommentedComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortRecentlyUpdated() {
        RecentlyUpdatedComparator comparator = new RecentlyUpdatedComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortLeastRecentlyUpdated() {
        LeastRecentlyUpdatedComparator comparator = new LeastRecentlyUpdatedComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllClose() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.findAllClose(comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void filterByOpenAuthorAndComparator() {
        NewIssueComparator comparator = new NewIssueComparator();
        IssuePredicates predicates = new IssuePredicates();
        List<Issue> actual = manager.filterByOpen(predicates.filterAuthor("author2"), comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void filterByOpenLabel() {
        IssuePredicates predicates = new IssuePredicates();
        List<Issue> actual = manager.filterByOpen(predicates.filterLabel("Kotlin"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void filterByCloseAssigneeAndComparator() {
        NewIssueComparator comparator = new NewIssueComparator();
        IssuePredicates predicates = new IssuePredicates();
        List<Issue> actual = manager.filterByClose(predicates.filterAssignee("assigne4"), comparator);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void filterByCloseAssignee() {
        IssuePredicates predicates = new IssuePredicates();
        List<Issue> actual = manager.filterByClose(predicates.filterAssignee("assigne4"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}
