package com.example.issuetracker.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.issuetracker.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.issuetracker.model.Issue;
import com.example.issuetracker.repository.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class IssueController {
    @Autowired
    private EmployeeRepository issueRepository;
    //get all issues
    @GetMapping("/issues")
    public List<Issue> getAllIssues(){
        return issueRepository.findAll();
    }
    //get an issue with a specific id
    @GetMapping("/issues/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(issue);
    }
    //create an issue
    @PostMapping("/issues/create")
    public Issue createIssue(@RequestBody Issue issue){
return issueRepository.save(issue);
    }
//delete an issue with a specific id
@DeleteMapping("/issues/{id}")
public ResponseEntity<Map<String, Boolean>> deleteIssue(@PathVariable Long id){
    Issue issue = issueRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

    issueRepository.delete(issue);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
}
//update an issue
@PutMapping("/issue/{id}")
public ResponseEntity<Issue> updateIssue(@PathVariable Long id, @RequestBody Issue issueDetails){
    Issue issue = issueRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

    issue.setIssueName(issueDetails.getIssueName());
//    issue.getDescription(issueDetails.setDescription());


    Issue updatedIssue = issueRepository.save(issue);
    return ResponseEntity.ok(updatedIssue);
}

}
