package com.example.issuetracker.controller;

import com.example.issuetracker.model.Issues;
import com.example.issuetracker.repository.IssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/issues")
public class IssuesController {
    @Autowired
    private IssuesRepository issuesRepository;

    @GetMapping("/client/{clientId}")
    public List<Issues> getIssuesByClient(@PathVariable Long clientId) {
        // Retrieve and return issues by client ID
        return issuesRepository.findByClientId(clientId);
    }

    @PostMapping
    public Issues createIssue(@RequestBody Issues issue) {
        // Validate and save the issue
        return issuesRepository.save(issue);
    }
    @GetMapping("/new")
    public List<Issues> getNewIssues() {
        // Retrieve and return new issues
        return issuesRepository.findByStatus("New");
    }

    @PutMapping("/{issueId}/start")
    public void startHandlingIssue(@PathVariable Long issueId) {
        // Find and update the status of the issue to "Started"
        Optional<Issues> optionalIssue = issuesRepository.findById(issueId);
        optionalIssue.ifPresent(issue -> {
            issue.setStatus("Started");
            issuesRepository.save(issue);
        });
    }

    @PutMapping("/{issueId}/close")
    public void closeIssue(@PathVariable Long issueId) {
        // Find and update the status of the issue to "Closed"
        Optional<Issues> optionalIssue = issuesRepository.findById(issueId);
        optionalIssue.ifPresent(issue -> {
            issue.setStatus("Closed");
            issuesRepository.save(issue);
        });
    }

}
