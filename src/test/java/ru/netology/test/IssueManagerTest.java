package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.manager.IssueManager;
import ru.netology.repository.IssueRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    IssueManager manager = new IssueManager(new IssueRepository());
    Label label1 = new Label("type1", "value1");
    Label label2 = new Label("type1", "value2");
    Label label3 = new Label("type2", "value1");
    Set<Label> labelSet = new HashSet<>();

    Issue issue1 = new Issue(1, "Раз", "Author 1", "Assignee", true, null, 1000);
    Issue issue2 = new Issue(2, "Два", "Author 21", "Assignee 12", false, null, 100);
    Issue issue3 = new Issue(3, "Три", "Author 22", "Assignee 11", true, null, 10);


    @BeforeEach
    void setUp() {
        issue1.getLabels().add(label1);
        labelSet.addAll(List.of(label2, label3));
        issue2.getLabels().addAll(labelSet);
        manager.add(issue1);
        manager.addAll(List.of(issue2, issue3));
    }

    @Test
    void shouldFilterByAuthorDefault() {
        Issue[] expected = {issue3, issue2, issue1};
        assertArrayEquals(expected, manager.filterByAuthor("author", null));
    }

    @Test
    void shouldFilterByAuthorOrderByAuthor() {
        Issue[] expected = {issue2, issue3};
        assertArrayEquals(expected, manager.filterByAuthor("author 2", IssueManager.SortOrder.byAuthor));
    }

    @Test
    void shouldFilterByAssigneeOrderByAssignee() {
        Issue[] expected = {issue3, issue2};
        assertArrayEquals(expected, manager.filterByAsignee("ASSIGNEE 1", IssueManager.SortOrder.byAssignee));
    }

    @Test
    void shouldFilterByNameOrderByName() {
        Issue[] expected = {issue2, issue1};
        assertArrayEquals(expected, manager.filterByName("а", IssueManager.SortOrder.byName));
    }
    @Test
    void shouldGetAllOrderByCreated() {
        Issue[] expected = {issue3, issue2, issue1};
        assertArrayEquals(expected, manager.getAll(true, true, IssueManager.SortOrder.byCreated));
    }

    @Test
    void shouldGetAllOpenOrderByAuthor() {
        Issue[] expected = {issue1, issue3};
        assertArrayEquals(expected, manager.getAll(true, false, IssueManager.SortOrder.byAuthor));
    }


    @Test
    void shouldGetAllClosed() {
        Issue[] expected = {issue2};
        assertArrayEquals(expected, manager.getAll(false, true, IssueManager.SortOrder.byCreated));
    }

    @Test
    void shouldFilterByLabels() {
        Issue[] expected = {issue2};
        assertArrayEquals(expected, manager.filterByLabels(labelSet, null));
    }
}