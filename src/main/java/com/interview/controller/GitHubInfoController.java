package com.interview.controller;

import com.interview.dto.RepositoryDTO;
import com.interview.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Controller
public class GitHubInfoController {

    private GitHubService gitHubService;

    @Autowired
    public GitHubInfoController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;

    }

    public Mono<ServerResponse> getRepositoryInfo(ServerRequest request) {
        String ip = request.remoteAddress().map(it -> it.getAddress().getHostAddress()).orElse("Unknown");
        try {
            RepositoryDTO repositoryDTO = gitHubService.getRepositoryInfo(request.pathVariable("owner"), request.pathVariable("repositoryName"));
            gitHubService.saveSuccessfulCall(repositoryDTO, ip);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(repositoryDTO));
        } catch (HttpClientErrorException e) {
            return ServerResponse.status(e.getStatusCode()).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(BodyInserters.fromObject(e.getResponseBodyAsString()));
        }
    }

    public Mono<ServerResponse> getAllSuccessfulCalls(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(gitHubService.getAllSuccessfulCalls()));
    }
}
