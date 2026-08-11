package com.issuetracker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.issuetracker.dao.IssueDAO;
import com.issuetracker.dao.IssueDAOImpl;
import com.issuetracker.exception.IssueTrackerException;
import com.issuetracker.model.Assignee;
import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueReport;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.validator.Validator;

// Do Not Change Any Signature
public class IssueServiceImpl implements IssueService
{
    private AssigneeService assigneeService = new AssigneeServiceImpl();
    private IssueDAO issueDAO = new IssueDAOImpl();
    private Validator validator = new Validator();
    
    private static final Logger LOGGER = LogManager.getLogger(IssueServiceImpl.class);
    
    @Override
    public String reportAnIssue(Issue issue) throws IssueTrackerException
    {
	
	try {
	    
	    validator.validate(issue);
	    List<Assignee> assignees = assigneeService.fetchAssignee(issue.getIssueUnit());
	    if(!assignees.isEmpty()) {
		Assignee assignee = assignees.get(0);
		issue.setAssigneeEmail(assignee.getAssigneeEmail());
		assigneeService.updateActiveIssueCount(assignee.getAssigneeEmail(), 'I');
	    }
	    String issueId = issueDAO.reportAnIssue(issue);
	    if(issueId == null) {
		throw new IssueTrackerException("IssueService.DUPLICATE_ISSUE_ID");
	    }else {
		return issueId;
	    }
	    
	}catch(IssueTrackerException e) {
	    LOGGER.error(e.getMessage());
	    throw e;
	}
    }

    @Override
    public Boolean updateStatus(String issueId,
				IssueStatus status) throws IssueTrackerException
    {
	try {
	    Issue issue = issueDAO.getIssueById(issueId);
		if(issue == null) {
		    throw new IssueTrackerException("IssueService.ISSUE_NOT_FOUND");
		}
		
		if(issue.getStatus() == status) {
		    throw new IssueTrackerException("IssueService.NO_STATUS_CHANGE");
		}
		
		if(status == IssueStatus.RECALLED && issue.getStatus()!=IssueStatus.OPEN) {
		    throw new IssueTrackerException("IssueService.INCOMPATIBLE_STATUS");
		}
		issueDAO.updateStatus(issue, status);
		
		if(status!=IssueStatus.OPEN || status == IssueStatus.IN_PROGRESS) {
		    assigneeService.updateActiveIssueCount(issue.getAssigneeEmail(), 'D');
		}
		
		return true;
		
	}catch(IssueTrackerException e) {
	    LOGGER.error(e.getMessage());
	    throw e;
	}
    }

    @Override
    public List<IssueReport> showIssues(Map<Character, Object> filterCriteria) throws IssueTrackerException
    {
	try {
	    List<Issue> issueList = issueDAO.getIssueList();
	    
	    Character key = filterCriteria.keySet().iterator().next();
	    
	    Object value = filterCriteria.get(key);
	    
	    List<IssueReport> report;
	    
	    if(key == 'A') {
		report = issueList.stream().filter(issue->issue.getAssigneeEmail().equals(value)).map(issue-> new IssueReport(issue.getIssueId(),issue.getIssueDescription(),issue.getAssigneeEmail(),issue.getStatus())).collect(Collectors.toList());
	    }else {
		report = issueList.stream().filter(issue->issue.getStatus()==value).map(issue-> new IssueReport(issue.getIssueId(),issue.getIssueDescription(),issue.getAssigneeEmail(),issue.getStatus())).collect(Collectors.toList());
	    }
	    
	    if(report.isEmpty()) {
		throw new IssueTrackerException("IssueService.NO_ISSUES_FOUND");
	    }
	    
	    return report;
	}catch(IssueTrackerException e) {
	    LOGGER.error(e.getMessage());
	    throw e;
	}
    }

    @Override
    public List<Issue> deleteIssues() throws IssueTrackerException
    {
	try {
	    List<Issue> issueList = issueDAO.getIssueList();
	    
	    List<Issue> deletedIssues = issueList.stream().filter(issue->(issue.getStatus() == IssueStatus.RESOLVED || issue.getStatus() == IssueStatus.CLOSED) && issue.getUpdatedOn() != null && issue.getUpdatedOn().isBefore(LocalDate.now().minusDays(14))).collect(Collectors.toList());
	    
	    if(deletedIssues.isEmpty()) {
		throw new IssueTrackerException("IssueService.NO_ISSUES_DELETED");
	    }
	    
	    issueList.removeAll(deletedIssues);
	    
	    return deletedIssues;
	}catch(IssueTrackerException e) {
	    LOGGER.error(e.getMessage());
	    throw e;
	}
    }
}








