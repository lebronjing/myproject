package com.self.common.base.bean;

public class BaseEntity {
	private String id;
	
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
}
