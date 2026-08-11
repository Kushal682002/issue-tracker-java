package com.issuetracker.service;

import java.util.List;
import java.util.stream.Collectors;

import com.issuetracker.dao.AssigneeDAO;
import com.issuetracker.dao.AssigneeDAOImpl;
import com.issuetracker.model.Assignee;
import com.issuetracker.model.Unit;

// Do Not Change Any Signature
public class AssigneeServiceImpl implements AssigneeService
{
    private AssigneeDAO assigneeDAO = new AssigneeDAOImpl();

    @Override
    public List<Assignee> fetchAssignee(Unit unit)
    {
	List<Assignee> list = assigneeDAO.fetchAssignees(unit).stream().filter(p->p.getNumberOfIssuesActive()<3).collect(Collectors.toList());

	return list;
    }

    @Override
    public void updateActiveIssueCount(String assigneeEmail,
				       Character operation)
    {
	Assignee assignee = assigneeDAO.getAssigneeByEmail(assigneeEmail);
	if(assignee != null) {
	    if(operation == 'I') {
		assignee.setNumberOfIssuesActive(assignee.getNumberOfIssuesActive()+1);
	    }else {
		assignee.setNumberOfIssuesActive(assignee.getNumberOfIssuesActive()-1);
	    }
	}
    }
}