package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {

    private IssueRepository rep = new IssueRepository();

    @BeforeEach
    void setUp() {
        rep.addAll(List.of(
                new Issue(1, "Раз", "Author 1", "Assignee 1", true, null, 0),
                new Issue(2, "Два", "Author 2", "Assignee 2", false, null, 100),
                new Issue(3, "Три", "Author 3", "Assignee 3", true, null, 1000)
        ));
    }

    @Test
    void shouldFindAll() {
        LinkedList<Issue> expected = new LinkedList<>();
        expected.addAll(List.of(
                new Issue(1, "Раз", "Author 1", "Assignee 1", true, null, 0),
                new Issue(2, "Два", "Author 2", "Assignee 2", false, null, 100),
                new Issue(3, "Три", "Author 3", "Assignee 3", true, null, 1000)
        ));

        rep.findAll();
        assertArrayEquals(expected.toArray(), rep.findAll().toArray());
    }

    @Test
    void shouldFindById() {
        Issue expected = new Issue(2, "Два", "Author 2", "Assignee 2", false, null, 100);
        assertEquals(expected, rep.findById(2));
    }

    @Test
    void shouldRemoveById() {
        LinkedList<Issue> expected = new LinkedList<>();
        expected.addAll(List.of(
                new Issue(1, "Раз", "Author 1", "Assignee 1", true, null, 0),
                new Issue(3, "Три", "Author 3", "Assignee 3", true, null, 1000)
        ));

        rep.removeById(2);
        assertArrayEquals(expected.toArray(), rep.findAll().toArray());
    }
}