package com.service.graphql.server;

import javax.servlet.annotation.WebServlet;
import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.kickstart.servlet.GraphQLWebsocketServlet;

@WebServlet(urlPatterns = "/api/graphql")
public class GraphQLService extends GraphQLHttpServlet {

  @Override
  protected GraphQLConfiguration getConfiguration() {
    return GraphQLConfigurationProvider.getInstance().getConfiguration();
  }
}
