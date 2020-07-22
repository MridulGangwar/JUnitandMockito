package com.mgangwar.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import com.mgangwar.api.TodoService;

//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockImplTest {
	
	@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoService;
	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> argumentCaptor;
	
	@Test
	public void usingMockito_UsingBDD() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		//given
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		//when
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");

		//then
		assertThat(todos.size(), is(2));
	}
	
	@Test
	public void letsTestDeleteThen() {

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		then(todoService).should().deleteTodo("Learn to Dance");
		then(todoService).should(never()).deleteTodo("Learn Spring");
		then(todoService).should(times(1)).deleteTodo("Learn to Dance");
		
		//will fail, as we are calling only once
		//verify(todoService, atLeast(1)).deleteTodo("Learn to Dance");

	}
	
	@Test
	public void captureArgument() {

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
		verify(todoService).deleteTodo(argumentCaptor.capture());

		assertEquals("Learn to Dance", argumentCaptor.getValue());
	}

}
