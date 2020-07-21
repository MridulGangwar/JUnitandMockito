package com.mgangwar.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.mgangwar.api.TodoService;


public class TodoBusinessImplMockTest {

	@Test
	public void usingMockito() {
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		
		when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	
	}
}
