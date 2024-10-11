package com.in28minutes.springboot.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.model.Todo;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "in28Minutes", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "in28Minutes", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "in28Minutes", "Learn Hibernate", new Date(),
                false));
    }

    /**
     * Retrieves a list of Todo objects for a specific user.
     * 
     * @param user The username to filter the todos by (case-insensitive)
     * @return A list of Todo objects belonging to the specified user
     */
    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }
    
    /**
    * Retrieves a Todo object by its ID from a collection of todos.
    * 
    * @param id The unique identifier of the Todo to retrieve
    * @return The Todo object with the specified ID, or null if not found
    */
    public Todo retrieveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }

    /**
    * Updates a Todo item in the collection.
    * 
    * This method removes the existing Todo item from the collection
    * and adds the updated version, effectively updating the item.
    * 
    * @param todo The Todo object to be updated
    */
    public void updateTodo(Todo todo){
    		todos.remove(todo);
    		todos.add(todo);
    }

    /**
    * Adds a new Todo item to the list of todos
    * 
    * @param name The name or title of the todo item
    * @param desc The description of the todo item
    * @param targetDate The target date for completing the todo item
    * @param isDone The status of the todo item, true if completed, false otherwise
    */
    public void addTodo(String name, String desc, Date targetDate,
            boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    /**
     * Deletes a todo item from the collection based on its ID.
     * 
     * This method iterates through the collection of todos and removes the todo
     * item that matches the given ID. If no matching todo is found, the collection
     * remains unchanged.
     * 
     * @param id The unique identifier of the todo item to be deleted
     */
    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}