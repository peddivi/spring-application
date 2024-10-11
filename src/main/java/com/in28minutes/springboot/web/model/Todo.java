package com.in28minutes.springboot.web.model;

import java.util.Date;

import javax.validation.constraints.Size;

public class Todo {
    private int id;
    private String user;
    
    @Size(min=10, message="Enter at least 10 Characters...")
    private String desc;

    private Date targetDate;
    private boolean isDone;

    public Todo() {
    		super();
    }
    
    public Todo(int id, String user, String desc, Date targetDate,
            boolean isDone) {
        super();
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    /**
     * Gets the id value.
     * 
     * @return The id value as an integer.
     */
    public int getId() {
        return id;
    }

    /**
    * Sets the ID of the object.
    * 
    * @param id The integer value to set as the ID
    */
    public void setId(int id) {
        this.id = id;
    }

    /**
    * Gets the user associated with this object.
    * 
    * @return The user as a String value
    */
    public String getUser() {
        return user;
    }

    /**
    * Sets the user for this object.
    * 
    * @param user The username to be set
    */
    public void setUser(String user) {
        this.user = user;
    }

    /**
    * Retrieves the description of the object.
    * 
    * @return The description of the object as a String.
    */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description for this object.
     * 
     * @param desc The new description to be set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
    * Gets the target date.
    * 
    * @return The target date.
    */
    public Date getTargetDate() {
        return targetDate;
    }

    /**
    * Sets the target date for this object.
    * 
    * @param targetDate The new target date to be set
    */
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * Checks if the task or operation is completed.
     * 
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
    * Sets the done status of the object.
    * 
    * @param isDone The boolean value indicating whether the object is done or not
    */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
    * Calculates the hash code for this object.
    * 
    * This method overrides the default hashCode() method to provide a custom
    * implementation based on the object's id field.
    *
    * @return An integer hash code value for this object.
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * Checks if this Todo object is equal to another object.
     * 
     * This method overrides the default equals method to provide a custom equality
     * check for Todo objects. Two Todo objects are considered equal if they have the
     * same id.
     * 
     * @param obj The object to compare this Todo against
     * @return true if the given object represents a Todo equivalent to this Todo,
     *         false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Todo other = (Todo) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of the Todo object.
     * 
     * @return A formatted string containing the Todo object's fields: id, user, desc, targetDate, and isDone.
     */
    @Override
    public String toString() {
        return String.format(
                "Todo [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]", id,
                user, desc, targetDate, isDone);
    }

}