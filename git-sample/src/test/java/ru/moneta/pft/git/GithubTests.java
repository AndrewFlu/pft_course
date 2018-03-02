package ru.moneta.pft.git;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub();
        RepoCommits commits = github.repos().get(new Coordinates.Simple("AndrewFlu", "pft_course")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(commit);
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
