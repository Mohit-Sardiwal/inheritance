package com.example.inheritance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.config.RoutingFunction;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import com.example.inheritance.entity.GrandChild;
import com.example.inheritance.entity.Parent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class InheritanceApplication<T> {

	private static RoutingFunction functionRouter;

	public InheritanceApplication(RoutingFunction functionRouter) {
		this.functionRouter = functionRouter;
	}

	public static void main(String[] args) {
		SpringApplication.run(InheritanceApplication.class, args);
		InheritanceApplication inheritanceApplication = new InheritanceApplication(functionRouter);
		inheritanceApplication.testMethod();

	}

	public  void testMethod(){
		List<Parent> parentList = new ArrayList<>();

		String jsonString = "{\"id\":\"4a001\",\"name\":\"John\",\"childId\":\"12345\",\"value\":\"first\"," +
			"\"properties\":{\"hobbies\":[{\"hobbyId\":\"1\",\"hobbyName\":\"coding\"}]}}";

		Object jsonObject = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonObject = objectMapper.readValue(jsonString, GrandChild.class);
		} catch (JsonProcessingException e) {
		}
		Parent parent = (Parent)jsonObject;
		parentList.add(parent);

		log.debug("PARENT :{}",parent);
		Message<T> object = (Message<T>) MessageBuilder.withPayload(parentList).build();

		Message<T> functionMessage = MessageBuilder
			.withPayload(object.getPayload())
			.setHeader("spring.cloud.function.definition", "getFamilyData")
			.setHeader(MessageHeaders.CONTENT_TYPE, "text/plain")
			//.setHeader(MessageHeaders.CONTENT_TYPE, "application/json")
			.copyHeadersIfAbsent(object.getHeaders())
			.build();

		Object payload = functionRouter.apply(functionMessage);
		log.debug("Result is :{}",payload);

	}
}
