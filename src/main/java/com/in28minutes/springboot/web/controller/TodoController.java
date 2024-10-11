package com.in28minutes.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService service;

	/**
	* Initializes the WebDataBinder with a custom date format editor.
	* This method registers a custom editor for Date class to handle date inputs
	* in the format "dd/MM/yyyy".
	* 
	* @param binder The WebDataBinder to be initialized
	*/	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	/**
	* Displays the list of todos for the logged-in user.
	* 
	* This method handles GET requests to the "/list-todos" endpoint. It retrieves
	* the logged-in user's name, fetches their todos from the service, adds the
	* todos to the model, and returns the view name for displaying the list.
	*
	* @param model The ModelMap object used to pass attributes to the view
	* @return The name of the view to render, which is "list-todos"
	*/
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}

	/**
	* Retrieves the username of the currently logged-in user.
	* 
	* This method extracts the username from the security context. If the principal
	* is an instance of UserDetails, it returns the username. Otherwise, it returns
	* the string representation of the principal.
	*
	* @param model The ModelMap object, which is not used in this method but may be
	*              required by the calling context
	* @return The username of the logged-in user as a String
	*/
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

	/**
	* Shows the page for adding a new todo item.
	* 
	* @param model The ModelMap object to add attributes to the model
	* @return The name of the view to be rendered
	*/
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model),
				"Default Desc", new Date(), false));
		return "todo";
	}

	/**
	* Deletes a todo item with the specified ID and redirects to the todo list.
	* 
	* @param id The ID of the todo item to be deleted
	* @return A string indicating the redirect URL to the list of todos
	* @throws RuntimeException If the ID is 1, indicating something went wrong
	*/
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {

		if(id==1)
			throw new RuntimeException("Something went wrong");
		
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}

	/**
	* Displays the update page for a specific Todo item
	* 
	* @param id The unique identifier of the Todo item to be updated
	* @param model The ModelMap object to add attributes to the model
	* @return The name of the view to be rendered ("todo")
	*/
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}

	/**
	* Updates a Todo item in the system.
	* 
	* This method handles the POST request for updating an existing Todo item.
	* It validates the input, updates the Todo with the current user, and saves it.
	* 
	* @param model The ModelMap object to add attributes to the model
	* @param todo The Todo object containing the updated information
	* @param result The BindingResult object for validation errors
	* @return A string indicating the view to redirect to after processing
	*/	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo,
			BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		todo.setUser(getLoggedInUserName(model));

		service.updateTodo(todo);

		return "redirect:/list-todos";
	}

	/**
	 * Adds a new todo item to the list.
	 * 
	 * This method handles the POST request for adding a new todo item. It validates the input,
	 * adds the todo if valid, and redirects to the list of todos.
	 * 
	 * @param model The ModelMap object used to pass attributes to the view
	 * @param todo The Todo object containing the details of the new todo item
	 * @param result The BindingResult object for validation errors
	 * @return A string indicating the view to redirect to after processing
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),
				false);
		return "redirect:/list-todos";
	}
}
