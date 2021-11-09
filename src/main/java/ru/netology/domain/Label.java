package ru.netology.domain;

import java.util.Objects;

public class Label {
    public final String type;
    public final String value;
    public Label(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
