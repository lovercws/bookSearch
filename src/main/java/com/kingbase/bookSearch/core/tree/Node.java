package com.kingbase.bookSearch.core.tree;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * easyUI 树节点结构
 * 
 * @author ganliang
 */
public class Node {

	private String id;// 树节点id（唯一性）
	private String _parentId;// 父节点id
	private String text;// 节点名称
	private String state;// 节点状态 open close
	private boolean checked;// 节点是否被选中
	private String src;
	private Set<Node> children = new LinkedHashSet<Node>(); // 子节点(不重复)

	public Node() {
		super();
	}

	public Node(String id, String _parentId, String text, String state, String src) {
		super();
		this.id = id;
		this._parentId = _parentId;
		this.text = text;
		this.state = state;
		this.src = src;
	}

	public Node(String id, String _parentId, String text, String state,String src, boolean checked) {
		super();
		this.id = id;
		this._parentId = _parentId;
		this.text = text;
		this.state = state;
		this.src = src;
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Set<Node> getChildren() {
		return children;
	}

	public void setChildren(Set<Node> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
