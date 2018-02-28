package ru.moneta.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.moneta.pft.mantis.model.Issue;
import ru.moneta.pft.mantis.model.Project;
import ru.moneta.pft.mantis.model.Status;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    // fields
    private ApplicationManager app;

    // constructor
    public SoapHelper(ApplicationManager app){
        this.app = app;
    }

    // methods
    public Set<Project>getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mcp = getMantisConnect();
        ProjectData[] projects = mcp.mc_projects_get_user_accessible(app.getProperty("soap.adminlogin"), app.getProperty("soap.adminpassword"));
        return Arrays.asList(projects).stream().map((p) -> (new Project().withId(p.getId().intValue()).withName(p.getName()))).collect(Collectors.toSet());
    }

    public Set<Issue> getIssues(int projectId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData[] issuesInProject = mc.mc_project_get_issues(app.getProperty("soap.adminlogin"),
                app.getProperty("soap.adminpassword"), BigInteger.valueOf(projectId), null, null);
        return Arrays.asList(issuesInProject).stream()
                .map((i) -> (new Issue().withId(i.getId().intValue())).withSummary(i.getSummary())
                .withDescription(i.getDescription())).collect(Collectors.toSet());
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("soap.url")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = new IssueData();
        String[] categories = mc.mc_project_get_categories(app.getProperty("soap.adminlogin"), app.getProperty("soap.adminpassword"), BigInteger.valueOf(issue.getProject().getId()));
        issueData.setCategory(categories[0]);
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        BigInteger issueId = mc.mc_issue_add(app.getProperty("soap.adminlogin"), app.getProperty("soap.adminpassword"), issueData);
        IssueData createdIssueData = mc.mc_issue_get(app.getProperty("soap.adminlogin"), app.getProperty("soap.adminpassword"), issueId);
        return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getId().intValue())
                                          .withName(createdIssueData.getProject().getName()));
    }

    public String checkIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get(app.getProperty("soap.adminlogin"), app.getProperty("soap.adminpassword"), BigInteger.valueOf(issueId));
        ObjectRef status = issueData.getStatus();
        System.out.println("Issue has status " + status.getName() + " and id = " + status.getId());
        return status.getName();
    }


    public Issue closed(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        // берем нужный баг репорт
        IssueData modIssue = mc.mc_issue_get(app.getProperty("soap.adminlogin"), app.getProperty("soap.adminpassword"), BigInteger.valueOf(issue.getId()));
        // меняем ему статус и ID
        modIssue.setStatus(new ObjectRef(BigInteger.valueOf(Long.parseLong(app.getProperty("soap.closedStatusCode"))), "closed"));
        modIssue.setId(BigInteger.valueOf(issue.getId()));
        // обновляем баг репорт новыми данными
        mc.mc_issue_update(app.getProperty("soap.adminlogin"), app.getProperty("soap.adminpassword"), modIssue.getId(), modIssue);
        IssueData modifyIssue = mc.mc_issue_get(app.getProperty("soap.adminlogin"), app.getProperty("soap.adminpassword"), modIssue.getId());
        ObjectRef status = modifyIssue.getStatus();
        return new Issue().withId(modifyIssue.getId().intValue()).withSummary(modifyIssue.getSummary()).withDescription(modifyIssue.getDescription())
                .withProject(new Project().withId(modifyIssue.getProject().getId().intValue()).withName(modifyIssue.getProject().getName()))
                .withStatus(new Status().withStatusId(modifyIssue.getStatus().getId().intValue()).withStatus(status.getName())) ;
    }
}
