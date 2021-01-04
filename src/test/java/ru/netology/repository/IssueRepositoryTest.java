package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();
    Issue issue1 = new Issue(1, "author1", List.of("Groovy"), "project1", "miles1", "assigne1", true, 50, 4, 6);
    Issue issue2 = new Issue(2, "author2", List.of("Platform"), "project3", "miles2", "assigne3", true, 56, 7, 8);
    Issue issue3 = new Issue(3, "author4", List.of("Jupiter"), "project4", "miles3", "assigne4", false, 1, 5, 5);
    Issue issue4 = new Issue(4, "author3", List.of("Kotlin"), "project1", "miles4", "assigne3", true, 0, 66, 1);
    Issue issue5 = new Issue(5, "author2", List.of("Groovy"), "project5", "miles1", "assigne1", true, 5, 7, 6);
    Issue issue6 = new Issue(6, "author1", List.of("Kotlin"), "project5", "miles3", "assigne2", false, 9, 4, 7);
    Issue issue7 = new Issue(7, "author1", List.of("Jupiter"), "project1", "miles2", "assigne4", true, 5, 3, 8);
    Issue issue8 = new Issue(8, "author2", List.of("Kotlin"), "project3", "miles1", "assigne1", true, 8, 1, 7);
    Issue issue9 = new Issue(9, "author1", List.of("Platform"), "project5", "miles4", "assigne2", false, 8, 2, 9);

    List<Issue> save = List.of(issue1, issue2, issue3, issue4, issue5, issue6, issue7, issue8);

    @BeforeEach
    public void setUP() {
        repository.saveAll(save);
        repository.save(issue9);
    }

    @Test
    public void removeById() {
        repository.removeById(5);
        List<Issue> actual = repository.findAll();
        List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue6, issue7, issue8, issue9);
        assertEquals(expected, actual);
    }

    @Test
    public void removeByIdNotFound() {
        repository.removeById(11);
        List<Issue> actual = repository.findAll();
        List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5, issue6, issue7, issue8, issue9);
        assertEquals(expected, actual);
    }

    @Test
    public void removeAll() {
        repository.removeAll(repository.findAll());
        List<Issue> actual = repository.findAll();
        List<Issue> expected = List.of();
        assertEquals(expected, actual);
    }
}