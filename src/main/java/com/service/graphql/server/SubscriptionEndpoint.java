package com.service.graphql.server;

import javax.websocket.server.ServerEndpoint;
import graphql.kickstart.servlet.GraphQLWebsocketServlet;
import com.service.graphql.server.GraphQLWSEndpointConfigurer;

import javax.websocket.Session;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;

import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@ServerEndpoint(value = "/subscriptions", configurator = GraphQLWSEndpointConfigurer.class)
public class SubscriptionEndpoint extends GraphQLWebsocketServlet {

  public SubscriptionEndpoint() {
    super(GraphQLConfigurationProvider.getInstance().getConfiguration());
  }
}
