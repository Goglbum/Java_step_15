package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.comparator.*;
import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.IssueRepository;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class IssueManagerTest {
    @Mock
    private IssueRepository repository;
    @InjectMocks
    private IssueManager manager;
    Issue issue1 = new Issue(1,"author1", List.of("Groovy","Jupiter"), "project1", "miles1", "assigne4", true, 50, 4, 6);
    Issue issue2 = new Issue(2,"author2", List.of("Platform","Groovy","Jupiter"), "project3", "miles2", "assigne3", true, 56, 7, 8);
    Issue issue3 = new Issue(3,"author4", List.of("Jupiter","Groovy"), "project4", "miles3", "assigne4", false, 1, 5, 5);
    Issue issue4 = new Issue(4,"author3", List.of("Kotlin","Groovy","Jupiter"), "project1", "miles4", "assigne3", true, 0, 66, 1);
    Issue issue5 = new Issue(5,"author2", List.of("Groovy"), "project5", "miles1", "assigne1", true, 5, 7, 6);
    Issue issue6 = new Issue(6,"author1", List.of("Kotlin","Groovy","Platform"), "project5", "miles3", "assigne2", false, 9, 4, 7);
    Issue issue7 = new Issue(7,"author1", List.of("Jupiter"), "project1", "miles2", "assigne4", true, 5, 3, 8);
    Issue issue8 = new Issue(8,"author2", List.of("Kotlin"), "project3", "miles1", "assigne1", true, 8, 1, 7);
    Issue issue9 = new Issue(9,"author1", List.of("Platform"), "project5", "miles4", "assigne2", false, 8, 2, 9);

    @BeforeEach
    public void setUp() {
        List<Issue> returned = List.of(issue1, issue2, issue3, issue4, issue5, issue6, issue7, issue8, issue9);
        doReturn(returned).when(repository).findAll();
    }


    @Test
    public void findAllOpenDefault() {
        List<Issue> actual = manager.findAllOpen();
        List<Issue> expected = List.of(issue4, issue5, issue7, issue8, issue1, issue2);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortNewIssue() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = List.of(issue4, issue5, issue7, issue8, issue1, issue2);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortOldIssue() {
        OldIssueComparator comparator = new OldIssueComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = List.of(issue2, issue1, issue8, issue5, issue7, issue4);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortMostCommented() {
        MostCommentedComparator comparator = new MostCommentedComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = List.of(issue4, issue2, issue5, issue1, issue7, issue8);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortLeastCommented() {
        LeastCommentedComparator comparator = new LeastCommentedComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = List.of(issue8, issue7, issue1, issue2, issue5, issue4);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortRecentlyUpdated() {
        RecentlyUpdatedComparator comparator = new RecentlyUpdatedComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = List.of(issue4, issue1, issue5, issue8, issue2, issue7);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllOpenSortLeastRecentlyUpdated() {
        LeastRecentlyUpdatedComparator comparator = new LeastRecentlyUpdatedComparator();
        List<Issue> actual = manager.findAllOpen(comparator);
        List<Issue> expected = List.of(issue2, issue7, issue8, issue1, issue5, issue4);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllClose() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.findAllClose(comparator);
        List<Issue> expected = List.of(issue3, issue9, issue6);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByOpenAuthorAndComparator() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.filterAuthorByOpen("author2", comparator);
        List<Issue> expected = List.of(issue5, issue8, issue2);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByOpenAuthor() {
        List<Issue> actual = manager.filterAuthorByOpen("author2");
        List<Issue> expected = List.of(issue5, issue8, issue2);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByOpenLabelAndComparator() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.filterLabelByOpen("Kotlin", comparator);
        List<Issue> expected = List.of(issue4, issue8);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByOpenLabel() {
        List<Issue> actual = manager.filterLabelByOpen("Kotlin");
        List<Issue> expected = List.of(issue4, issue8);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByOpenAssigneeAndComparator() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.filterAssigneeByOpen("assigne4", comparator);
        List<Issue> expected = List.of(issue7, issue1);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByOpenAssignee() {
        List<Issue> actual = manager.filterAssigneeByOpen("assigne4");
        List<Issue> expected = List.of(issue7, issue1);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByCloseAuthorAndComparator() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.filterAuthorByClose("author1", comparator);
        List<Issue> expected = List.of(issue9, issue6);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByCloseAuthor() {
        List<Issue> actual = manager.filterAuthorByClose("author1");
        List<Issue> expected = List.of(issue9, issue6);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByCloseLabelAndComparator() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.filterLabelByClose("Kotlin", comparator);
        List<Issue> expected = List.of(issue6);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByCloseLabel() {
        List<Issue> actual = manager.filterLabelByClose("Kotlin");
        List<Issue> expected = List.of(issue6);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByCloseAssigneeAndComparator() {
        NewIssueComparator comparator = new NewIssueComparator();
        List<Issue> actual = manager.filterAssigneeByClose("assigne4", comparator);
        List<Issue> expected = List.of(issue3);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByCloseAssignee() {
        List<Issue> actual = manager.filterAssigneeByClose("assigne4");
        List<Issue> expected = List.of(issue3);
        assertEquals(expected, actual);
    }

    @Test
    public void closeByTag() {
        manager.closeByTag(7);
        List<Issue> actual = manager.findAllOpen();
//        System.out.println(actual);
        List<Issue> expected = List.of(issue4, issue5, issue8, issue1, issue2);
        assertEquals(expected, actual);
    }

    @Test
    public void closeByTagNotFound() {
        assertThrows(NotFoundException.class, () -> manager.closeByTag(3));
    }
}