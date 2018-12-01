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
import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Martire91<br>
 * La differenza tra la HTMLNodeList è :
 * di questa classe non si può creare una instanza.
 * Viene usata maggiormente nelle selezione jquery e css
 * ma anche nel dom in alcuni metodi , e può avere rispetto 
 * alla lista di nodi, una selezione e un criterio di selezione
 * quindi ebbene distinguerle
 *
 */
import java.util.Map;
import java.util.Map.Entry;

import cloud.jgo.£;
import cloud.jgo.io.File;
import cloud.jgo.jjdom.dom.HTMLRecursion;
import cloud.jgo.jjdom.dom.Manipulable;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLNodeList;
/**
 * 
 * @author Martire91<br>
 * @see Element
 * This class represents a list of html elements ({@link Element})
 */
public class Elements extends LinkedList<Element> implements Manipulable{
	
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < this.size(); i++) {
		buffer.append((i+1)+") "+get(i)+"\n");
		}
		return buffer.toString();
	}
	// questo è ricorsivo
	@Override
	public Element getElementById(String elementId) {
		Element found = null ;
		for (Element el:this) {
			el = HTMLRecursion.examinesForId(elementId,el);
			if (el!=null) {
				// sappiamo che è un id
				// per cui al primo elemento trovato, possiamo uscire
				found = el ;
				break ;
			}
		}
		return found ;
	}
	// questo no 
	public Element getElementById2(String elementId){
		Element found = null ;
		for (Element el:this) {
			if (el.isPresent("id")) {
				if (el.getAttribute("id").equals(elementId)) {
					found = el ;
					break ;
				}
			}
		}
		return found ;
	}
	
	// qui vediamo bene cosa fa questo metodo
	// seleziona elementi mediante tagName
	// su una lista di elementi :
	// quindi deve entrare in ogni nodo
	// della lista, e raccogliere tutti i nodi
	// di quel dato tag nella lista finale che poi verrà restituita
	// vediamo comunque di fare un esempio
	// ricorsivo
	@Override
	public Elements getElementsByTag(String tagName) {
		Elements found = new Elements();
		for (Element el:this) {
			Elements newList = HTMLRecursion.examinesForTag(tagName, el);
			if (newList!= null) {
				for (int i = 0; i < newList.size(); i++) {
					if (!found.contains(newList.get(i))) {
						found.add(newList.get(i));
					}
				}
			}
		}
		return found ;
	}
	// questo no
	public Elements getElementsByTag2(String tagName){
		Elements found = new Elements();
		for (Element el:this) {
			if (el.getNodeName().equals(tagName)) {
				found.add(el);
			}
		}
		return found ;
	}
	

	public Elements getDirectChildrenByTag(String tagName) {
		Elements found = new Elements();
		for (Element el:this) {
			// prendo i figli dell'elemento
			HTMLNodeList list = el.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				if (list.item(i)instanceof Element) {
					if (list.item(i).getNodeName().equals(tagName)) {
						found.add((Element) list.item(i));
					}
				}
			}
		}
		return found ;
	}
	
	// questi i primi fratelli che trova, esce dal ciclo
	@Override
	public Elements getAdiacentSiblingsByTag(String tagName) {
		boolean exit = false ;
		Elements found = new Elements();
		for (Element el:this) {
			Elements brothers = el.getAdiacentSiblingsByTag(tagName);
			// nel momento in cui la lista di elementi
			// ha un size maggiore di 0, si può uscire dal ciclo
			if (brothers.size()>0) {
				found = brothers ;
				break ;
			}
		}
		return found ;
	}

	// da testare particolarmente questo metodo
	
	@Override
	public Elements getGeneralSiblingsByTag(String tagName) {
		boolean exit = false ;
		Elements found = new Elements();
		for (Element el:this) {
			Elements brothers = el.getGeneralSiblingsByTag(tagName);
			// nel momento in cui la lista di elementi
			// ha un size maggiore di 0, si può uscire dal ciclo
			if (brothers.size()>0) {
				found = brothers ;
				break ;
			}
		}
		return found ;
	}


	// da sviluppare
	@Override
	public Elements getElementsByClassName(String className) {
		Elements found = new Elements();
		for (Element el:this) {
			Elements newList = HTMLRecursion.examinesForClass(className, el);
			if (newList!= null) {
				for (int i = 0; i < newList.size(); i++) {
					if (!found.contains(newList.get(i))) {
						found.add(newList.get(i));
					}
				}
			}
		}
		return found ;
	}


	@Override
	public Elements getElementsByAttribute(String attribute) {
		Elements found = new Elements();
		for (Element el:this) {
			if (el.hasAttributes()) {
				if (el.isPresent(attribute)) {
					if (!found.contains(el)) {
						found.add(el);
					}
				}
			}
		}
		return found ;
	}


	@Override
	public Elements getElementsByAttributeValue(String value) {
		Elements found = new Elements();
		for (Element el:this) {
			if (el.hasAttributes()) {
				// prendo tutti gli attributi 
				Iterator<Entry<String, String>>iterator = el.getAttributes().entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
							.next();
					String val = entry.getValue();
					if (value.equals(val)) {
						if (!found.contains(el)) {
							found.add(el);
							
							break ; // qui salto perchè, nel momento in cui trovo un attributo che ha questo valore, non mi interessa trovarne altri
						    // quindi prendiamo il primo attributo che ha il valore richiesto
						}
					}
				}
				
				
			}
		}
		return found ;
	}

	// questo metodo contiene ancora il codice vecchio commentato
	// magari in futuro ne vorrò prendere nota

	@Override
	public Elements getElementsByAttributeValue(String attr, String val) {
		Elements found = new Elements();
		for (Element el:this) {
			// qui stiamo recuperando la lista di elementi per tag su la lista attuale
//			Elements newList = HTMLRecursion.examinesForAttributeValue_(attr, val, el, "=");
//			if (newList!= null) {
//				for (int i = 0; i < newList.size(); i++) {
//					if (!found.contains(newList.get(i))) {
//						found.add(newList.get(i));
//					}
//				}
//			}
			if (el.hasAttributes()) {
				if (el.isPresent(attr)) {
					String value = el.getAttributeValue(attr);
					if (value.equals(val)) {
						if (!found.contains(el)) {
							found.add(el);
						}
					}
				}
			}
		}
		return found ;
	}


	@Override
	public Elements getElementsByDifferentAttributeValue(String attr, String val) {
		Elements found = new Elements();
		for (Element el:this) {
			if (el.hasAttributes()) {
				if (el.isPresent(attr)) {
					// ottengo il valore
					String value = el.getAttributeValue(attr);
					if (!value.equals(val)) {
						if (!found.contains(el)) {
							found.add(el);
						}
					}
				}
			}
		}
		return found ;
	}


	@Override
	public Elements getElementsThatStartWithAttributevalue(String attr, String val) {
		Elements found = new Elements();
		for (Element el:this) {
			if (el.hasAttributes()) {
				if (el.isPresent(attr)) {
					String value = el.getAttributeValue(attr);
					if (value.startsWith(val)) {
						if (!found.contains(el)) {
							found.add(el);
						}
					}
				}
			}
		}
		return found ;
	}


	@Override
	public Elements getElementsThatEndWithAttributeValue(String attr, String val) {
		Elements found = new Elements();
		for (Element el:this) {
			if (el.hasAttributes()) {
				if (el.isPresent(attr)) {
					String value = el.getAttributeValue(attr);
					if (value.endsWith(val)) {
						if (!found.contains(el)) {
							found.add(el);
						}
					}
				}
			}
		}
		return found ;
	}
	@Override
	public Elements getElementsThatContainTheAttributeValue(String attr, String val) {
		Elements found = new Elements();
		for (Element el:this) {
			if (el.hasAttributes()) {
				if (el.isPresent(attr)) {
					String value = el.getAttributeValue(attr);
					if (value.contains(val)) {
						if (!found.contains(el)) {
							found.add(el);
						}
					}
				}
			}
		}
		return found ;
	}	
}