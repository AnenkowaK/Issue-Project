package ru.netology.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Issue {
    private int id;
    private String name;
    private String author;
    private String assignee;
    private boolean open;
    private final long created;
    private final Set<Label> labels = new HashSet<>();

    public Issue(int id, String name, String author, String assignee, boolean open, Set<Label> labels, long created) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.assignee = assignee;
        this.open = open;
        // this.labels = new HashSet<>();
        if (labels != null) { this.labels.addAll(labels); }
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public long getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && open == issue.open && created == issue.created && Objects.equals(name, issue.name) && Objects.equals(author, issue.author) && Objects.equals(assignee, issue.assignee) && Objects.equals(labels, issue.labels);
    }
}
