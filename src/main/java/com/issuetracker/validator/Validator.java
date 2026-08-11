package com.issuetracker.validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueStatus;

// Do Not Change Any Signature
public class Validator
{
    
    private static final Logger LOGGER = LogManager.getLogger(Validator.class);
    
    public void validate(Issue issue) throws IssueTrackerException
    {
	if(!isValidIssueId(issue.getIssueId())) {
	    LOGGER.error("Validator.INVALID_ISSUE_ID");
	    throw new IssueTrackerException("Validator.INVALID_ISSUE_ID");
	}
	
	if(!isValidIssueDescription(issue.getIssueDescription())) {
	    LOGGER.error("Validator.INVALID_ISSUE_DESCRIPTION");
	    throw new IssueTrackerException("Validator.INVALID_ISSUE_DESCRIPTION");
	}
	
	if(!isValidReportedOn(issue.getReportedOn())) {
	    LOGGER.error("Validator.INVALID_REPORTED_DATE");
	    throw new IssueTrackerException("Validator.INVALID_REPORTED_DATE");
	}
	
	if(!isValidStatus(issue.getStatus())) {
	    LOGGER.error("Validator.INVALID_STATUS");
	    throw new IssueTrackerException("Validator.INVALID_STATUS");
	}
    }

    public Boolean isValidIssueId(String issueId)
    {
	if(issueId==null || issueId.isEmpty()) {
	    return false;
	}
	return Pattern.matches("^MTI-I-(?!000)\\d{3}-(LS|MS|HS)$",issueId);

    }

    public Boolean isValidIssueDescription(String issueDescription)
    {
	if(issueDescription == null || issueDescription.isBlank() || issueDescription.isEmpty()) {
	    return false;
	}
	

	return Pattern.matches("^[A-Za-z]+( [A-Za-z]+)*$", issueDescription) && issueDescription.length()>=1 && issueDescription.length()<=50;
    }

    public Boolean isValidReportedOn(LocalDate reportedOn)
    {
	return reportedOn!=null && !reportedOn.isAfter(LocalDate.now());
    }

    public Boolean isValidStatus(IssueStatus status)
    {
	return status == IssueStatus.OPEN || status == IssueStatus.IN_PROGRESS;
    }
}