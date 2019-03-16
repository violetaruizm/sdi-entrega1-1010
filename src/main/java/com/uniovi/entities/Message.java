package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @ManyToOne
    private Sale sale;

    private String body;

    private LocalDateTime date;

    private boolean valid;

    public Message() {

    }

    public long getId() {
	return id;
    }

    public User getSender() {
	return sender;
    }

    public void setSender(User sender) {
	this.sender = sender;
    }

    public User getReceiver() {
	return receiver;
    }

    public void setReceiver(User receiver) {
	this.receiver = receiver;
    }

    public Sale getSale() {
	return sale;
    }

    public void setSale(Sale sale) {
	this.sale = sale;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public LocalDateTime getDate() {
	return date;
    }

    public void setDate(LocalDateTime date) {
	this.date = date;
    }

    public boolean isValid() {
	return valid;
    }

    public void setValid(boolean valid) {
	this.valid = valid;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Message other = (Message) obj;
	if (id != other.id)
	    return false;
	return true;
    }

}
