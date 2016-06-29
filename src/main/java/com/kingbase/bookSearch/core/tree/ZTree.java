package com.kingbase.bookSearch.core.tree;

/**
 * ZTree
 * 
 * @author ganliang
 *
 */
public class ZTree {

	private int id;
	private int pId;
	private String name;
	private boolean open;//
	private boolean checked;//
	private String src;//

	public ZTree() {
		super();
	}

	public ZTree(int id, int pId, String name, String src, boolean open, boolean checked) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.src = src;
		this.open = open;
		this.checked = checked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
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

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
