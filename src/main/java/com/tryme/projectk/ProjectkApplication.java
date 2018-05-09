package com.tryme.projectk;

import com.tryme.projectk.repository.AccountRepository;
import com.tryme.projectk.repository.NoteRepository;
import com.tryme.projectk.resolver.Mutation;
import com.tryme.projectk.resolver.NoteResolver;
import com.tryme.projectk.resolver.Query;
import com.tryme.projectk.utils.GraphQLErrorAdapter;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ProjectkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectkApplication.class, args);
	}

	@Bean
	public NoteResolver authorResolver(AccountRepository authorRepository) {
		return new NoteResolver(authorRepository);
	}

	@Bean
	public Query query(AccountRepository accountRepository, NoteRepository noteRepository) {
		return new Query(accountRepository, noteRepository);
	}

	@Bean
	public Mutation mutation(AccountRepository accountRepository, NoteRepository noteRepository) {
		return new Mutation(accountRepository, noteRepository);
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}
}
