package com.example.issuetracker.model;
import jakarta.persistence.*;
import java.time.LocalDate;



    @Entity
    public class Issues {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String issueName;
        private String description;
        private String status;
        private LocalDate issueDate;
        private LocalDate startDate;
        private LocalDate closureDate;
        private boolean acknowledged;
        private String acknowledgedBy;
        private LocalDate acknowledgedDate;
        @ManyToOne
        private User client;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public LocalDate getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(LocalDate issueDate) {
            this.issueDate = issueDate;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getClosureDate() {
            return closureDate;
        }

        public void setClosureDate(LocalDate closureDate) {
            this.closureDate = closureDate;
        }

        public boolean isAcknowledged() {
            return acknowledged;
        }

        public void setAcknowledged(boolean acknowledged) {
            this.acknowledged = acknowledged;
        }

        public String getAcknowledgedBy() {
            return acknowledgedBy;
        }

        public void setAcknowledgedBy(String acknowledgedBy) {
            this.acknowledgedBy = acknowledgedBy;
        }

        public LocalDate getAcknowledgedDate() {
            return acknowledgedDate;
        }

        public void setAcknowledgedDate(LocalDate acknowledgedDate) {
            this.acknowledgedDate = acknowledgedDate;
        }

        public User getClient() {
            return client;
        }

        public void setClient(User client) {
            this.client = client;
        }
// getters and setters
    }


