package com.issuetracker.model;

import java.util.Objects;

public class IssueReport
{
    private String issueId;
    private String issueDescription;
    private String assigneeEmail;
    private IssueStatus status;
    public String getIssueId()
    {
        return issueId;
    }
    public void setIssueId(String issueId)
    {
        this.issueId = issueId;
    }
    public String getIssueDescription()
    {
        return issueDescription;
    }
    public void setIssueDescription(String issueDescription)
    {
        this.issueDescription = issueDescription;
    }
    public String getAssigneeEmail()
    {
        return assigneeEmail;
    }
    public void setAssigneeEmail(String assigneeEmail)
    {
        this.assigneeEmail = assigneeEmail;
    }
    public IssueStatus getStatus()
    {
        return status;
    }
    public void setStatus(IssueStatus status)
    {
        this.status = status;
    }
    @Override
    public int hashCode()
    {
	return Objects.hash(assigneeEmail, issueDescription, issueId, status);
    }
    @Override
    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	IssueReport other = (IssueReport) obj;
	return Objects.equals(assigneeEmail, other.assigneeEmail)
	       && Objects.equals(issueDescription, other.issueDescription)
	       && Objects.equals(issueId, other.issueId)
	       && status == other.status;
    }
    @Override
    public String toString()
    {
	return "IssueReport [issueId=" + issueId + ", issueDescription="
	       + issueDescription + ", assigneeEmail=" + assigneeEmail
	       + ", status=" + status + "]";
    }
    public IssueReport(String issueId, String issueDescription,
		       String assigneeEmail, IssueStatus status)
    {
	super();
	this.issueId = issueId;
	this.issueDescription = issueDescription;
	this.assigneeEmail = assigneeEmail;
	this.status = status;
    }
    public IssueReport()
    {
	super();
    }
    
    
}