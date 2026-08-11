package com.issuetracker.model;

import java.time.LocalDate;
import java.util.Objects;

public class Assignee
{
    private String assigneeId;
    private String assigneeName;
    private String assigneeEmail;
    private Unit workingUnit;
    private LocalDate employeeSince;
    private Integer numberOfIssuesActive;
    public String getAssigneeId()
    {
        return assigneeId;
    }
    public void setAssigneeId(String assigneeId)
    {
        this.assigneeId = assigneeId;
    }
    public String getAssigneeName()
    {
        return assigneeName;
    }
    public void setAssigneeName(String assigneeName)
    {
        this.assigneeName = assigneeName;
    }
    public String getAssigneeEmail()
    {
        return assigneeEmail;
    }
    public void setAssigneeEmail(String assigneeEmail)
    {
        this.assigneeEmail = assigneeEmail;
    }
    public Unit getWorkingUnit()
    {
        return workingUnit;
    }
    public void setWorkingUnit(Unit workingUnit)
    {
        this.workingUnit = workingUnit;
    }
    public LocalDate getEmployeeSince()
    {
        return employeeSince;
    }
    public void setEmployeeSince(LocalDate employeeSince)
    {
        this.employeeSince = employeeSince;
    }
    public Integer getNumberOfIssuesActive()
    {
        return numberOfIssuesActive;
    }
    public void setNumberOfIssuesActive(Integer numberOfIssuesActive)
    {
        this.numberOfIssuesActive = numberOfIssuesActive;
    }
    @Override
    public int hashCode()
    {
	return Objects.hash(assigneeEmail, assigneeId, assigneeName,
			    employeeSince, numberOfIssuesActive, workingUnit);
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
	Assignee other = (Assignee) obj;
	return Objects.equals(assigneeEmail, other.assigneeEmail)
	       && Objects.equals(assigneeId, other.assigneeId)
	       && Objects.equals(assigneeName, other.assigneeName)
	       && Objects.equals(employeeSince, other.employeeSince)
	       && Objects.equals(numberOfIssuesActive,
				 other.numberOfIssuesActive)
	       && workingUnit == other.workingUnit;
    }
    @Override
    public String toString()
    {
	return "Assignee [assigneeId=" + assigneeId + ", assigneeName="
	       + assigneeName + ", assigneeEmail=" + assigneeEmail
	       + ", workingUnit=" + workingUnit + ", employeeSince="
	       + employeeSince + ", numberOfIssuesActive="
	       + numberOfIssuesActive + "]";
    }
    public Assignee(String assigneeId, String assigneeName,
		    String assigneeEmail, Unit workingUnit,
		    LocalDate employeeSince, Integer numberOfIssuesActive)
    {
	super();
	this.assigneeId = assigneeId;
	this.assigneeName = assigneeName;
	this.assigneeEmail = assigneeEmail;
	this.workingUnit = workingUnit;
	this.employeeSince = employeeSince;
	this.numberOfIssuesActive = numberOfIssuesActive;
    }
    public Assignee()
    {
	super();
    }
    
    
}