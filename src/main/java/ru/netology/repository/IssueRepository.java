package ru.netology.repository;
import ru.netology.domain.Issue;
import java.util.Collection;
import java.util.LinkedList;

public class IssueRepository {
    private final Collection<Issue> repository = new LinkedList<>();

    //Сохранить issues в список
    public void addAll(Collection<? extends Issue> issues) {
        repository.addAll(issues);
    }

    //Сохранить issue в список
    public void add(Issue issue) {
        repository.add(issue);
    }

    //Найти все issues
    public Collection<Issue> findAll() {
        return new LinkedList<Issue>(repository);
    }

    //Найти issue по id
    public Issue findById(int id) {
        return repository.stream()
                .filter(issue -> issue.getId() == id)
                .findFirst()
                .get();
    }

    //Удалить issue по id
    public void removeById(int id) {
        repository.remove(findById(id));
    }
}




