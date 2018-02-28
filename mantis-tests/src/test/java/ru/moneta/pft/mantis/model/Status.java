package ru.moneta.pft.mantis.model;

public class Status {

    private int statusId;
    private String Status;

    public int getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return Status;
    }

    public Status withStatusId(int statusId) {
        this.statusId = statusId;
        return this;
    }

    public Status withStatus(String status) {
        Status = status;
        return this;
    }
}
