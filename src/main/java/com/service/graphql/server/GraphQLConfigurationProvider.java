package com.service.graphql.server;

import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.SubscriptionExecutionStrategy;
import graphql.kickstart.execution.GraphQLQueryInvoker;
import graphql.kickstart.execution.config.DefaultExecutionStrategyProvider;
import graphql.kickstart.servlet.GraphQLConfiguration;

public class GraphQLConfigurationProvider {

  private static GraphQLConfigurationProvider instance;
  private final GraphQLConfiguration configuration;

  private GraphQLSchemaProvider graphQLSchemaProvider;

  public GraphQLConfigurationProvider() {
    graphQLSchemaProvider = new GraphQLSchemaProvider();
    configuration = GraphQLConfiguration
        .with(graphQLSchemaProvider.getSchema())
        .with(GraphQLQueryInvoker.newBuilder()
            .withExecutionStrategyProvider(new DefaultExecutionStrategyProvider(
                new AsyncExecutionStrategy(),
                null,
                new SubscriptionExecutionStrategy()))
            .build())
        .build();
  }

  static GraphQLConfigurationProvider getInstance() {
    if (instance == null) {
      instance = new GraphQLConfigurationProvider();
    }
    return instance;
  }

  GraphQLConfiguration getConfiguration() {
    return configuration;
  }
}
