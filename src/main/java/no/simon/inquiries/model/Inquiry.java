package no.simon.inquiries.model;

import java.time.Instant;

public class Inquiry {

    private Long id;
    private String name;
    private String message;
    private Status status;
    private Instant createdAt;

    public Inquiry(String name, String message){
        this.name = name;
        this.message = message;
        this.status = Status.NEW; // Default starting status
        this.createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    //Only the status and the id can be changed after creation
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
