/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo.jjdom.dom.nodes;
import java.io.Serializable;
import java.util.LinkedList;
/**
 * 
 * @author Martire91<br>
 * This class represents a list of html nodes ({@link Node})
 *
 */
public class NodeList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Node>nodeList = null ;
	
	public NodeList() {
		// TODO Auto-generated constructor stub
		this.nodeList = new LinkedList<>();
	}
	/**
	 * This method adds a node
	 * @param node the node
	 * @return true if the node is added
	 */
	public boolean addNode(Node node){
		return this.nodeList.add(node);
	}
	// return -1 come valore non valido 
	/**
	 * This method returns the node index.
	 * @param node the node
	 * @return the node index
	 */
	public int getIndexOf(Node node){
		int index = -1 ;
		for (int i = 0; i < this.nodeList.size(); i++) {
			if (this.nodeList.get(i).equals(node)) {
				index = i ;
				break ;
			}
		}
		return index ;
	}
	
	/**
	 * This method adds the last node
	 * @param node the node
	 */
	public void addLastNode(Node node){
		this.nodeList.addLast(node);
	}
	
	/**
	 * This method adds the first node
	 * @param node the node
	 */
	public void addFirstNode(Node node){
		this.nodeList.addFirst(node);
	}
	
	/**
	 * This method checks if the node passed as a parameter
	 * is contained in the list
	 * @param node the node
	 * @return true if is contained
	 */
	public boolean contains(Node node){
		return this.nodeList.contains(node);
	}
	
	/**
	 * This method removes a node from list
	 * @param node the node
	 * @return true if the node has been deleted
	 */
	public boolean remove(Node node){
		return this.nodeList.remove(node);
	}
	/**
	 * This method returns the item to the index position
	 * @param index the item index
	 * @return the item
	 */
	public Node item(int index){
			return this.nodeList.get(index);
	}
	/**
	 * This method returns the list length
	 * @return the list length
	 */
	public int getLength(){
		return this.nodeList.size();
	}
	
	/**
	 * This method returns the first item
	 * @return the first item
	 */
	public Node getFirstItem(){
		return this.nodeList.getFirst();
	}
	/**
	 * This method returns the last item
	 * @return the last item
	 */
	public Node getLastItem(){
		return this.nodeList.getLast();
	}
	/**
	 * This method sets a node
	 * @param index the list index
	 * @param node the node
	 * @return {@link LinkedList#set(int, Object)}
	 */
	public Node setNode(int index, Node node){
		return this.nodeList.set(index, node);
	}

}
