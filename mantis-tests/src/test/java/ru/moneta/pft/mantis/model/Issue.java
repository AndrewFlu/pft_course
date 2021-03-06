package ru.moneta.pft.mantis.model;

public class Issue {

    private int id;
    private String summary;
    private String description;
    private Project project;
    private Status status;

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }

    public Status getStatus() {
        return status;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    public Issue withStatus(Status status) {
        this.status = status;
        return this;
    }
}
