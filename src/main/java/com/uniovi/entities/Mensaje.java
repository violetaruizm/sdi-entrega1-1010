package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mensaje {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	
	@ManyToOne
	private Conversacion conversacion;
	private String contenido;
	private Date fecha;
	private boolean leido;
	private String emisor;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conversacion == null) ? 0 : conversacion.hashCode());
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
		Mensaje other = (Mensaje) obj;
		if (conversacion == null) {
			if (other.conversacion != null)
				return false;
		} else if (!conversacion.equals(other.conversacion))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	public Conversacion getConversacion() {
		return conversacion;
	}
	public void setConversacion(Conversacion conversacion) {
		this.conversacion = conversacion;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isLeido() {
		return leido;
	}
	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	public String getEmisor() {
		return emisor;
	}
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public long getId() {
		return id;
	}
	

}
