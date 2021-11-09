package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public class IssueManager {
    public static class SortOrder {
        public final static Comparator<Issue> byCreated = Comparator.comparingLong(Issue::getCreated);

        public final static Comparator<Issue> byAuthor =
                (i1, i2) -> String.CASE_INSENSITIVE_ORDER.compare(i1.getAuthor(), i2.getAuthor());

        public final static Comparator<Issue> byAssignee =
                (i1, i2) -> String.CASE_INSENSITIVE_ORDER.compare(i1.getAssignee(), i2.getAssignee());

        public final static Comparator<Issue> byName =
                (i1, i2) -> String.CASE_INSENSITIVE_ORDER.compare(i1.getName(), i2.getName());
    }

    private final IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.add(issue);
    }

    public void addAll(Collection<? extends Issue> issues) {
        repository.addAll(issues);
    }

    public Issue[] getAll(boolean showOpen, boolean showClosed, Comparator<Issue> sortOrder) {
        sortOrder = (sortOrder == null) ? SortOrder.byCreated : sortOrder;
        return repository.findAll().stream()
                .filter(issue -> issue.isOpen() == showOpen || issue.isOpen() == !showClosed)
                .sorted(sortOrder)
                .toArray(Issue[]::new);
    }

    public Issue[] filterByAuthor(String author, Comparator<Issue> sortOrder) {
        sortOrder = (sortOrder == null) ? SortOrder.byCreated : sortOrder;
        return repository.findAll().stream()
                .filter(issue -> issue.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .sorted(sortOrder)
                .toArray(Issue[]::new);
    }

    public Issue[] filterByAsignee(String assignee, Comparator<Issue> sortOrder) {
        sortOrder = (sortOrder == null) ? SortOrder.byCreated : sortOrder;
        return repository.findAll().stream()
                .filter(issue -> issue.getAssignee().toLowerCase().contains(assignee.toLowerCase()))
                .sorted(sortOrder)
                .toArray(Issue[]::new);
    }

    public Issue[] filterByName(String name, Comparator<Issue> sortOrder) {
        sortOrder = (sortOrder == null) ? SortOrder.byCreated : sortOrder;
        return repository.findAll().stream()
                .filter(issue -> issue.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted(sortOrder)
                .toArray(Issue[]::new);
    }

    public Issue[] filterByLabels(Set<Label> labels, Comparator<Issue> sortOrder) {
        sortOrder = (sortOrder == null) ? SortOrder.byCreated : sortOrder;
        return repository.findAll().stream()
                .filter(issue -> issue.getLabels().containsAll(labels))
                .sorted(sortOrder)
                .toArray(Issue[]::new);
    }
}
