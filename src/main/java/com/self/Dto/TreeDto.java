package com.self.Dto;

public class TreeDto {
	public String id;
	public String pId;
	public String name;
	public boolean open;
	public boolean checked;
	public String level;
	//目标id
	public String targetId;
	//移动类型
	public String moveType;
	
	public TreeDto() {
	}
	
	public TreeDto(String id, String pId, String name, boolean open,
			boolean checked) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.checked = checked;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
}
