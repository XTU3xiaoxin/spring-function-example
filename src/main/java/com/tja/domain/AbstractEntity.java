package com.tja.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

	private Long id;

	
	@Override
	public boolean equals(Object obj) {
		if(null == obj) {
			return false;
		}
		AbstractEntity target = (AbstractEntity)obj;
		if(null == target.id) {
			return false;
		}
		return ((AbstractEntity)obj).id.equals(this.id);
	}

	@Override
	public int hashCode() {
		if(null != id) {
			return this.id.hashCode();
		}else {
			final int prime = 31;
		    int result = 1;
		    result = prime * result + 7;
		    return result;
		}
	}

	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
