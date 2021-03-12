package com.service.graphql.server;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

class GraphQLSchemaProvider {

      GraphQLDataFetchers graphQLDataFetchers = new GraphQLDataFetchers();

      private GraphQLSchema graphQLSchema;

      GraphQLSchemaProvider() {
        try {
          init();
        }
        catch(IOException e) {
          e.printStackTrace();
        }
      }

      // @PostConstruct
      public void init() throws IOException {
          URL url = Resources.getResource("schema.graphqls");
          String sdl = Resources.toString(url, Charsets.UTF_8);
          this.graphQLSchema = buildSchema(sdl);
      }

      private GraphQLSchema buildSchema(String sdl) {
          TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
          RuntimeWiring runtimeWiring = buildWiring();
          SchemaGenerator schemaGenerator = new SchemaGenerator();
          return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
      }

      private RuntimeWiring buildWiring() {
          return RuntimeWiring.newRuntimeWiring()
                  .type(newTypeWiring("Query")
                          .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
                  .type(newTypeWiring("Book")
                          .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                  .type(newTypeWiring("Subscription")
                          .dataFetcher("randomNumber", graphQLDataFetchers.getRandomNumberFetcher()))
                  .build();
      }

  public GraphQLSchema getSchema(){
      return graphQLSchema;
  }
}
