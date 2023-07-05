package com.example.issuetracker.model;
import jakarta.persistence.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Entity




@Table(name="issues")
public class Issue {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(name="issue_name")
    private String issueName;
    @Column(name="description")
    private String description;

    public Issue(){}
    public Issue(String issueName,String description){
        this.issueName=issueName;
        this.description=description;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setStatus(String started) {
    }
}
