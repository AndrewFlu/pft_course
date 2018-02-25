package ru.moneta.pft.mantis.Tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.mantis.model.Issue;
import ru.moneta.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects){
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(created.getSummary(), issue.getSummary());
    }

    @Test
    public void testIsIssueOpenStatus() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Project project = projects.iterator().next();
        Set<Issue> issues = app.soap().getIssues(project.getId());
        Issue issue = issues.iterator().next();
        boolean status = isIssueOpen(issue.getId());
        System.out.println("Id: " + issue.getId() +  " status: " + status);
    }

    @Test
    public void testClosedIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Project project = projects.iterator().next();
        Set<Issue> issues = app.soap().getIssues(project.getId());
        Issue issue = issues.iterator().next();

        skipIfNotFixed(issue.getId());

        Issue modifyIssue = app.soap().closed(issue);
        String status = app.soap().checkIssueStatus(modifyIssue.getId());
    }

    @Test
    public void testIssueStatus() throws RemoteException, ServiceException, MalformedURLException {
        String status = app.soap().checkIssueStatus(8);
        System.out.println(status);

    }


}
