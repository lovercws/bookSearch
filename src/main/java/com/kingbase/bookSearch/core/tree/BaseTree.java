package com.kingbase.bookSearch.core.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

public class BaseTree {

	/**
	 * 生成基本树
	 * @param nodes 节点集合
	 * @return
	 */
	public List<Node> parser(List<Node> nodes){
		if(nodes==null||nodes.size()==0){
			return null;
		}
		
		//获取根节点
		List<Node> topNodes=getTopNodes(nodes);
		
		//迭代根节点
		Iterator<Node> iterator = topNodes.iterator();
		while(iterator.hasNext()){
			Node topNode = iterator.next();
			iteratorTopNode(topNode,nodes);
		}
		
		return topNodes;
	}

	/**
	 * 获取根节点集合
	 * @param nodes 节点集合
	 * @return
	 */
	private List<Node> getTopNodes(List<Node> nodes) {
		List<Node> topNodes=new ArrayList<Node>();
		//迭代所有的节点
		Iterator<Node> iterator = nodes.iterator();
		while(iterator.hasNext()){
			Node node = iterator.next();
			//获取父节点
			String parentId = node.get_parentId();
			if(parentId==null||"0".equals(parentId)){
				topNodes.add(node);
				
				iterator.remove();
			}
		}
		
		return topNodes;
	}
	
	/**
	 * 迭代根节点
	 * @param topNode 顶节点
	 * @param nodes 所有的节点
	 */
	private void iteratorTopNode(Node topNode, List<Node> nodes) {
		List<Node> childrenNodes=getDirectChildrenNode(topNode.getId(),nodes);
		for (Node childrenNode : childrenNodes) {
			iteratorTopNode(childrenNode, nodes);
		}
		topNode.getChildren().addAll(childrenNodes);       
	}

	/**
	 * 获取直接子节点
	 * @param id 节点id
	 * @param nodes 所有的节点
	 * @return
	 */
	private List<Node> getDirectChildrenNode(String id, List<Node> nodes) {
		List<Node> directChildrenNodes=new ArrayList<Node>();
		//迭代所有的节点
		Iterator<Node> iterator = nodes.iterator();
		while(iterator.hasNext()){
			Node node = iterator.next();
			if(id.equals(node.get_parentId())){
				directChildrenNodes.add(node);
				
				iterator.remove();
			}
		}		
		return directChildrenNodes;
	}
	
	public static void main(String[] args) {
		Node systemManagerNode=new Node("1", null,"系统管理管理", "open",null);
		
		Node userManagerNode=new Node("11","1","用户管理", "open",null);
		Node user1=new Node("111","11","用户详情","open","system/userManagerAction_userDetailMessage.do");
		Node user2=new Node("112","11","用户列表","open","system/userManagerAction_userList.do");
		Node user3=new Node("113","11","密码修改","open","system/userManagerAction_changePassword.do");
		Node user4=new Node("114","11","用户统计","open","system/userManagerAction_userStatistics.do");
		Node user5=new Node("115","11","在线用户","open","system/userManagerAction_onlineUser.do");
		userManagerNode.getChildren().add(user1);
		userManagerNode.getChildren().add(user2);
		userManagerNode.getChildren().add(user3);
		userManagerNode.getChildren().add(user4);
		userManagerNode.getChildren().add(user5);
		
		Node authManagerNode=new Node("12","1","权限管理", "open",null);
		Node auth1=new Node("121","12","权限列表","open","system/menuManagerAction_menuTable.do");
		authManagerNode.getChildren().add(auth1);
		
		Node roleManagerNode=new Node("13","1","角色管理", "open",null);
		Node role1=new Node("131","13","角色列表","open","");
		roleManagerNode.getChildren().add(role1);
		
		Node menuManagerNode=new Node("14","1","按钮管理", "open",null);
		Node menu1=new Node("141","13","按钮列表","open","system/menuManagerAction_menuTable.do");
		Node menu2=new Node("142","13","按钮树","open","system/menuManagerAction_menuTree.do");
		menuManagerNode.getChildren().add(menu1);
		menuManagerNode.getChildren().add(menu2);
		
		systemManagerNode.getChildren().add(userManagerNode);
		systemManagerNode.getChildren().add(authManagerNode);
		systemManagerNode.getChildren().add(roleManagerNode);
		systemManagerNode.getChildren().add(menuManagerNode);
		
		
		Node bookManagerNode=new Node("2", null,"书籍管理管理", "open",null);

		Node uploadManagerNode=new Node("21","2","上传管理", "open",null);
		Node upload1=new Node("211","21","上传设置","open","book/uploadManagerAction_uploadSetting.do");
		Node upload2=new Node("212","21","书籍上传","open","book/uploadManagerAction_uploadBook.do");
		Node upload3=new Node("213","21","上传记录","open","book/uploadManagerAction_uploadHistory.do");
		Node upload4=new Node("214","21","上传统计","open","book/uploadManagerAction_uploadStatistics.do");
		uploadManagerNode.getChildren().add(upload1);
		uploadManagerNode.getChildren().add(upload2);
		uploadManagerNode.getChildren().add(upload3);
		uploadManagerNode.getChildren().add(upload4);
		
		Node bookNode=new Node("22","2","书籍管理", "open",null);
		Node book1=new Node("221","22","书籍列表","open","book/bookManagerAction_bookList.do");
		Node book2=new Node("222","22","书籍分类","open","book/bookManagerAction_bookClassify.do");
		Node book3=new Node("223","22","书籍分类统计","open","book/bookManagerAction_bookClassifyStatistics.do");
		bookNode.getChildren().add(book1);
		bookNode.getChildren().add(book2);
		bookNode.getChildren().add(book3);
		
		Node collectionManagerNode=new Node("23","2","收藏管理", "open",null);
		Node collection1=new Node("131","13","收藏列表","open","book/collectionManagerAction_collectionHistory.do");
		collectionManagerNode.getChildren().add(collection1);
		
		Node classifyManagerNode=new Node("24","2","书籍分类管理", "open",null);
		Node classify1=new Node("241","24","分类列表","open","book/categoryManagerAction_categoryTable.do");
		Node classify2=new Node("242","24","分类树","open","book/categoryManagerAction_categoryTree.do");
		classifyManagerNode.getChildren().add(classify1);
		classifyManagerNode.getChildren().add(classify2);
		
		Node downloadManagerNode=new Node("25","2","书籍分类管理", "open",null);
		Node download1=new Node("251","25","下载记录","open","book/downloadManagerAction_downloadHistory.do");
		Node download2=new Node("252","25","下载统计","open","book/downloadManagerAction_downloadStatistics.do");
		downloadManagerNode.getChildren().add(download1);
		downloadManagerNode.getChildren().add(download2);
		
		bookManagerNode.getChildren().add(uploadManagerNode);
		bookManagerNode.getChildren().add(bookNode);
		bookManagerNode.getChildren().add(collectionManagerNode);
		bookManagerNode.getChildren().add(classifyManagerNode);
		bookManagerNode.getChildren().add(downloadManagerNode);
		
		List<Node> menuData=new ArrayList<>();
		menuData.add(systemManagerNode);
		menuData.add(bookManagerNode);
		
		
		BaseTree tree=new BaseTree();
		List<Node> list = tree.parser(menuData);
		
		Gson gson=new Gson();
		System.out.println(gson.toJson(list));
		
	}
}
