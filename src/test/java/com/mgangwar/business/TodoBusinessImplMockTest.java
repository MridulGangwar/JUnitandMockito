package com.mgangwar.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

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
	
	@Test
	public void usingMockito_UsingBDD() {
		TodoService todoService = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
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
	public void test() {
		List listMock = mock(List.class);
		when (listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2,listMock.size());
		assertEquals(3,listMock.size());
	}
	
	@Test
	public void test2() {
		List listMock = mock(List.class);
		when (listMock.get(anyInt())).thenReturn("Java is Awesome");
		assertEquals("Java is Awesome",listMock.get(0));
		//assertEquals(null,listMock.get(1));
		assertEquals("Java is Awesome",listMock.get(2));
	}
	
	@Test
	public void test2_usingBDD() {
		//Given
		List listMock = mock(List.class);
		given (listMock.get(anyInt())).willReturn("Java is Awesome");
		
		//When
		String firstElement = (String) listMock.get(0);
		
		//Then
		assertThat(firstElement,is("Java is Awesome"));

	}
	
	@Test(expected=RuntimeException.class)
	public void letsMockList_throwAnException() {
		List listMock = mock(List.class);
		when (listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		listMock.get(0);
	}
	
	@Test
	public void letsTestDeleteNow() {

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		verify(todoService).deleteTodo("Learn to Dance");
		verify(todoService, never()).deleteTodo("Learn Spring MVC");
		verify(todoService, times(1)).deleteTodo("Learn to Dance");
		verify(todoService, atLeastOnce()).deleteTodo("Learn to Dance");
		verify(todoService, atLeast(1)).deleteTodo("Learn to Dance");
		
		//will fail, as we are calling only once
		//verify(todoService, atLeast(1)).deleteTodo("Learn to Dance");

	}
	
	@Test
	public void letsTestDeleteThen() {

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		then(todoService).should().deleteTodo("Learn to Dance");
		then(todoService).should(never()).deleteTodo("Learn Spring");
		then(todoService).should(times(1)).deleteTodo("Learn to Dance");
		
		//will fail, as we are calling only once
		//verify(todoService, atLeast(1)).deleteTodo("Learn to Dance");

	}
	
	@Test
	public void captureArgument() {
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
				.forClass(String.class);

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
		verify(todoService).deleteTodo(argumentCaptor.capture());

		assertEquals("Learn to Dance", argumentCaptor.getValue());
	}
	
	@Test
	public void captureArgument_Multiple() {
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
				.forClass(String.class);

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn to row", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
		
		then(todoService).should(times(2)).deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getAllValues().size(),is(2));
		
	}
}
