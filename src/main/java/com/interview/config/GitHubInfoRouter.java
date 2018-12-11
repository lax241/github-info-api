package com.interview.config;

import com.interview.controller.GitHubInfoController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GitHubInfoRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GitHubInfoController gitHubInfoController) {
        return RouterFunctions
                .route(RequestPredicates.GET("/repositories/{owner}/{repositoryName}"), gitHubInfoController::getRepositoryInfo)
                .andRoute(RequestPredicates.GET("/repositories/calls"), gitHubInfoController::getAllSuccessfulCalls);
    }
}
