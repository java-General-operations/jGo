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
package cloud.jgo.jjdom.css.concrete;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTML;

import cloud.jgo.£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.css.CSSSelection;
import cloud.jgo.jjdom.css.CSSSelectionFailedException;
import cloud.jgo.jjdom.css.CSSSelector;
import cloud.jgo.jjdom.css.ContextNotFoundException;
import cloud.jgo.jjdom.dom.HTMLElement;
import cloud.jgo.jjdom.dom.HTMLElements;
import cloud.jgo.jjdom.dom.HTMLManipulable;
import cloud.jgo.jjdom.dom.HTMLNode;
import cloud.jgo.jjdom.dom.HTMLRecursion;
/**
 * @author Martire91<br>
 * This class represents the default css selector
 */
public class CSSSimpleSelector implements CSSSelector{
	private static final long serialVersionUID = 1L;
	private HTMLNode rootContext = null ;
	private String[]subSelections = null ;
	// prima ultimare questo metodo
	@Override
	public CSSSelection select(String selection) {
		if (rootContext==null) {
			rootContext = JjDom.document ;
		}
		CSSSelection selection_ = new CSSSimpleSelection();
		if (selection.equals(GLOBAL_SELECTOR)) {
			HTMLElements allElements = HTMLRecursion.getAllElements(rootContext);
			((CSSSimpleSelection)selection_).selectedItems = allElements ;
		}
		else{
			this.subSelections = selection.split(" ");
			// questo controllo è provvisorio, quindi non perdere d'occhio
			if (this.subSelections.length>1||selection.contains("[")&&selection.endsWith("]")) {
				// ci sono spazi nella selezione
				recursiveSupport(rootContext,0,selection_);	
			}
			else{
				// non ci sono spazi nella selezione
				HTMLElements elements = new HTMLElements();
				if (selection.contains(",")) { // è semplicemente una selezione con virgole senza spazi e quindi gerarchicità, e senza parentesi quadre
					elements = multiSelection(selection);
				}
				else{
					// è senza virgole
					manageSelectionWithoutHierarchy(selection, elements);
				}
				((CSSSimpleSelection)selection_).selectedItems = elements ;
			}
		}
		return selection_ ;
	}
	
	
	
	public void manageSelectionWithoutHierarchy(String selection,HTMLElements elements){
		if (selection.contains("#")&&selection.contains(".")) {
			/** 
			 * 	ID AND CLASS
			 */
			if (selection.startsWith(".")) {
				// 4 passo : ottengo classe e id
				int index = selection.indexOf("#");
				String className,id = null ;
				className = selection.substring(0,index).replace(".","").trim();
				id = selection.substring(index).replace("#","").trim();
				HTMLElements classElements = ((HTMLManipulable)rootContext).getElementsByClassName(className);
				HTMLElement element = classElements.getElementById2(id);
				elements.add(element);
			}
			else{
				if (selection.startsWith("#")) {
					int index = selection.indexOf(".");
					String id,className = null ;
					id = selection.substring(0,index).trim().replace("#","").trim();
					className = selection.substring(index).replace(".","").trim();
					HTMLElements classElements = ((HTMLManipulable)rootContext).getElementsByClassName(className);
					HTMLElement element = classElements.getElementById2(id);
					elements.add(element);
				}
				else{
					try {
						throw new CSSSelectionFailedException(selection);
					} catch (CSSSelectionFailedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
		}
		else if(selection.contains("#")){
			/** 
			 * 	ID
			 */
			if (selection.startsWith("#")) {
				HTMLElement idElement = ((HTMLManipulable)rootContext).getElementById(selection.replace("#",""));
				if (idElement!=null) {
					elements.add(idElement);
				}
			}
			else{
				try {
					throw new CSSSelectionFailedException(selection);
				} catch (CSSSelectionFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(selection.contains(".")){
			/** 
			 * 	CLASS OR TagName and CLASS
			 *  qui diamo per scontato che se la selezione
			 *  inizia con una classe, deve trattarsi di una sola classe
			 *  dal momento che id non sono presenti, ne virgole e ne spazi
			 *  e quindi se non dovesse iniziare la selezione con il punto della
			 *  classe, diamo per scontato che inizia con un tagname, di fatto
			 *  questo tipo di selezione sarebbe plausibile
			 *  perciò diamo per scontato che lo è 
			 */
			if (selection.startsWith(".")) {
				/*
				 * Only Class
				 */
				HTMLElements classElements = ((HTMLManipulable)rootContext).getElementsByClassName(selection.replace(".",""));
				for (HTMLElement htmlElement : classElements) {
					elements.add(htmlElement);
				}
			}
			else{
					// qui ci deve essere un tagName come primo elemento
					// 1 passo : prendo il tagName e la classe
					int index = selection.indexOf(".");
					String tagName,className = null ;
					tagName = selection.substring(0,index).trim();
					className = selection.substring(index).replace(".","").trim();
					
					// ottengo gli elementi della classe
					HTMLElements classElements = ((HTMLManipulable)rootContext).getElementsByClassName(className);
					/** qui uso la seconda versione del metodo getElementsByTag2 */
					HTMLElements tagNameElements = classElements.getElementsByTag2(tagName);
					for (HTMLElement htmlElement : tagNameElements) {
						elements.add(htmlElement);
					}
			}
		}
		else{
			/** 
			 * 	TagName
			 */
			HTMLElements tagNameElements = ((HTMLManipulable)rootContext).getElementsByTag(selection);
			for (HTMLElement htmlElement : tagNameElements) {
				elements.add(htmlElement);
			}
		}
	}
	
	// per selezioni con virgole
	private HTMLElements multiSelection(int index,Object context){
		HTMLElements elements = new HTMLElements();
		// okok faccio lo split
		String[]selections = subSelections[index].split(",");
		
		for (int i = 0; i < selections.length; i++) {
			
			String currentSelection = selections[i];
	
			if (currentSelection.startsWith("#")) {
				HTMLElement el = ((HTMLManipulable)context).getElementById(currentSelection);
				if (el!=null) {
					elements.add(el);
				}
			}
			else if(currentSelection.startsWith(".")){
				HTMLElements els = ((HTMLManipulable)context).getElementsByClassName(currentSelection);
				for (HTMLElement htmlElement : els) {
					elements.add(htmlElement);
				}
			}
			else{
				HTMLElements els = ((HTMLManipulable)context).getElementsByTag(currentSelection);
				for (HTMLElement htmlElement : els) {
					elements.add(htmlElement);
				}
			}
		}
		// dopo il ciclo for segno il nuovo contesto 
		return elements ;
	}
	
	/**
	 * IN + QUESTO METODO SOSTITUISCE SIMBOLO ID E CLASS
	 * COSA CHE NON FA LA PRIMA VERSIONE DEL METODO	
	 * @param selection
	 * @return
	 */
	private HTMLElements multiSelection(String selection){
		HTMLElements elements = new HTMLElements();
		// okok faccio lo split
		String[]selections = selection.split(",");
		
		for (int i = 0; i < selections.length; i++) {
			
			String currentSelection = selections[i].trim();
	
			if (currentSelection.startsWith("#")) {
				HTMLElement el = ((HTMLManipulable)rootContext).getElementById(currentSelection.replace("#",""));
				if (el!=null) {
					elements.add(el);
				}
			}
			else if(currentSelection.startsWith(".")){
				HTMLElements els = ((HTMLManipulable)rootContext).getElementsByClassName(currentSelection.replace(".",""));
				for (HTMLElement htmlElement : els) {
					elements.add(htmlElement);
				}
			}
			else{
				HTMLElements els = ((HTMLManipulable)rootContext).getElementsByTag(currentSelection);
				for (HTMLElement htmlElement : els) {
					elements.add(htmlElement);
				}
			}
		}
		// dopo il ciclo for segno il nuovo contesto 
		return elements ;
	}
		
	private HTMLManipulable check(String element){
		HTMLManipulable manipulable = null ;
		if (element.startsWith("#")) {
			// id
			manipulable = ((HTMLManipulable)rootContext).getElementById(element.replace("#",""));
		}
		else if(element.startsWith(".")){
			// class
			manipulable = ((HTMLManipulable)rootContext).getElementsByClassName(element.replace(".",""));
		}
		else{
			// tagname
			manipulable = ((HTMLManipulable)rootContext).getElementsByTag(element);
		}
		return manipulable ;
	}
	
	@Override
	public CSSSelection select(String element, String combiner, String element2) {
		CSSSelection selection_ = new CSSSimpleSelection();
		HTMLManipulable manipulable = check(element);
		// qui controllo se la prima selezione è andata a buon fine 
		if (manipulable!=null) {
			// a questo punto controllo il tipo di combinatore 
			HTMLElements elements = null ;
			switch(combiner){
			case CHILDREN_COMBINER :
				elements = manipulable.getDirectChildrenByTag(element2);
				((CSSSimpleSelection)selection_).selectedItems = elements ;
				break ;
			case ADJACENT_BROTHERS_COMBINER :
				elements = manipulable.getAdiacentSiblingsByTag(element2);
				((CSSSimpleSelection)selection_).selectedItems = elements ;
			break ;
			case GENERAL_BROTHERS_COMBINER :
				elements = manipulable.getGeneralSiblingsByTag(element2);
				((CSSSimpleSelection)selection_).selectedItems = elements ;
				// continuare da qui ...
				break ;
			}
			return selection_ ;
		}
		else{
			return null ;
		}
	}
	private HTMLElements supportForAttributeSelection(int index,Object context,CSSSelection selection){
		HTMLElements elements = null ;
		String content = null ;
		String el = subSelections[index].substring(0,subSelections[index].indexOf("[")).trim();
		int index_ = subSelections[index].indexOf("[")+1;
		int lastIndex = subSelections[index].indexOf("]");
		String subString = subSelections[index].substring(index_,lastIndex).trim();
		String element,attr,val = null ;
		if (subString.contains("=")&&!subString.contains(DIFFERENT_OPERATOR)&&!subString.contains(STARTS_WITH_OPERATOR)&&!subString.contains(CONTAINS_OPERATOR)&&!subString.contains(ENDS_WITH_OPERATOR)) {
			// selezione [attr='value']
			attr = subString.split("=")[0].trim();
			val = subString.split("=")[1].replaceAll("'","").trim();
			elements = ((HTMLManipulable)context).getElementsByAttributeValue(attr, val);
			((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"[attr=val]|";
		}
		else if(subString.contains(DIFFERENT_OPERATOR)&&!subString.contains(STARTS_WITH_OPERATOR)&&!subString.contains(CONTAINS_OPERATOR)&&!subString.contains(ENDS_WITH_OPERATOR)){
			// selezione [attr!='value']
			attr = subString.split(DIFFERENT_OPERATOR)[0].trim();
			val = subString.split(DIFFERENT_OPERATOR)[1].replaceAll("'","").trim();
			elements = ((HTMLManipulable)context).getElementsByDifferentAttributeValue(attr, val);
			((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"[attr"+DIFFERENT_OPERATOR+"val]|";
		}
		else if(subString.contains(STARTS_WITH_OPERATOR)&&!subString.contains(DIFFERENT_OPERATOR)&&!subString.contains(CONTAINS_OPERATOR)&&!subString.contains(ENDS_WITH_OPERATOR)){
			// selezione [attr^='value']
			subString = subString.replace(STARTS_WITH_OPERATOR,"="); // piccola modifica
			attr = subString.split("=")[0].trim();
			val = subString.split("=")[1].replaceAll("'","").trim();
			elements = ((HTMLManipulable)context).getElementsThatStartWithAttributevalue(attr, val);
			((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"[attr"+STARTS_WITH_OPERATOR+"val]|";
		}
		else if(subString.contains(ENDS_WITH_OPERATOR)&&!subString.contains(DIFFERENT_OPERATOR)&&!subString.contains(CONTAINS_OPERATOR)&&!subString.contains(STARTS_WITH_OPERATOR)){
			// selezione [attr$='value']
			subString = subString.replace(ENDS_WITH_OPERATOR,"="); // piccola modifica
			attr = subString.split("=")[0].trim();
			val = subString.split("=")[1].replaceAll("'","").trim();
			elements = ((HTMLManipulable)context).getElementsThatEndWithAttributeValue(attr, val);
			((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"[attr"+ENDS_WITH_OPERATOR+"val]|";
		}
		else if(subString.contains(CONTAINS_OPERATOR)&&!subString.contains(DIFFERENT_OPERATOR)&&!subString.contains(STARTS_WITH_OPERATOR)&&!subString.contains(ENDS_WITH_OPERATOR)){
			// selezione [attr*='value']
			// provvisorio che è else
			subString = subString.replace(CONTAINS_OPERATOR,"="); // piccola modifica
			attr = subString.split("=")[0].trim();
			val = subString.split("=")[1].replaceAll("'","").trim();
			elements = ((HTMLManipulable)context).getElementsThatContainTheAttributeValue(attr, val);
			((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"[attr"+CONTAINS_OPERATOR+"val]|";
		}
		else{
			// quindi qui si prende l'attributo 
			String attr_ = subString ;
			elements = ((HTMLManipulable)context).getElementsByAttribute(attr_);
			((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"[attr]|";
		}
		return elements ;
	}
	
	
	
	// qui devo controllare che onon ci siano i simboli delle relazioni
	@Override
	public void recursiveSupport(Object context, int index, CSSSelection selection) {
		boolean selected = false ;
		if (index < subSelections.length) {
			Object selectedNewContext = null ;
				
				if (!subSelections[index].isEmpty()) {
					// prendo il valore corretto
					subSelections[index] = subSelections[index].trim();

					// 1 passo : comprendere ora il tipo di subSelection
					
					if (subSelections[index].startsWith("#")&&!subSelections[index].contains(" ")) {
						// ID
						// qui devo controllare se ci sono parentesi quadre nella selezione
						// questo è classico della selezione tramite paramentri
						
						if (!subSelections[index].contains("[")&&!subSelections[index].contains("]")) {
							/** N.B. casomai eliminare questo blocco */
							if (subSelections[index].contains(",")) {
								selectedNewContext = multiSelection(index, context); // se la vede questo metodo
								// qui poi devo verificare come gestire un eventuale valore null restituito
								((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"multiselection|";
							}
							else{
								selectedNewContext = ((HTMLManipulable)context).getElementById(subSelections[index].replace("#", ""));
								((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"id|";
							}
						}
						else{
							/** 1.0.2 version
							 * Qui poi devo fare il controllo se si tratta di un solo controllo per attributo
							 * oppure di un controllo multiplo, quindi devo lavorare sulle parentesi quadre
							 * che si trovano all'interno della stringa
							 */
							String element = subSelections[index].substring(0,subSelections[index].indexOf("[")).trim();
							selectedNewContext = ((HTMLManipulable)context).getElementById(element.replace("#", ""));
							HTMLElements elements = supportForAttributeSelection(index, selectedNewContext, selection);
							if (elements!=null) {
								for (int i = 0; i < elements.size(); i++) {
									selection.getSelectedItems().add(elements.get(i));
								}
							}
							// for moment
							return ;
						}
					}
					else if(subSelections[index].startsWith(".")&&!subSelections[index].contains(" ")){
						// CLASS
						if (!subSelections[index].contains("[")&&!subSelections[index].contains("]")) {
							/** N.B. casomai eliminare questo blocco */
							if (subSelections[index].contains(",")) {
								selectedNewContext = multiSelection(index, context);
								((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"multiselection|";
							}
							else{
								selectedNewContext = ((HTMLManipulable)context).getElementsByClassName(subSelections[index].replace(".",""));
								((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"class|";
							}
						}
						else{
							/** 1.0.2 version
							 * Qui poi devo fare il controllo se si tratta di un solo controllo per attributo
							 * oppure di un controllo multiplo, quindi devo lavorare sulle parentesi quadre
							 * che si trovano all'interno della stringa
							 */
							// è una ricerca con attributo
							String element = subSelections[index].substring(0,subSelections[index].indexOf("[")).trim();
							selectedNewContext = ((HTMLManipulable)context).getElementsByClassName(element.replace(".",""));
							HTMLElements elements = supportForAttributeSelection(index,selectedNewContext,selection);
							if (elements!=null) {
								for (int i = 0; i < elements.size(); i++) {
									selection.getSelectedItems().add(elements.get(i));
								}
							}
							// for moment
							return ;
						}	
					}
					else if(!subSelections[index].contains(" ")){
						// TAGNAME
						if (!subSelections[index].contains("[")&&!subSelections[index].contains("]")) {
							/** N.B. casomai eliminare questo blocco */
							if (subSelections[index].contains(",")) {
								selectedNewContext = multiSelection(index, context); // se la vede questo metodo
								// qui poi devo verificare come gestire un eventuale valore null restituito
								((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"multiselection|";
							}
							else{
								selectedNewContext = ((HTMLManipulable)context).getElementsByTag(subSelections[index]);
								((CSSSimpleSelection)selection).selectionCriterion = ((CSSSimpleSelection)selection).selectionCriterion+"tagname|";
							}
						}
						else{
							/** 1.0.2 version
							 * Qui poi devo fare il controllo se si tratta di un solo controllo per attributo
							 * oppure di un controllo multiplo, quindi devo lavorare sulle parentesi quadre
							 * che si trovano all'interno della stringa
							 */
							String element = subSelections[index].substring(0,subSelections[index].indexOf("[")).trim();
							selectedNewContext = ((HTMLManipulable)context).getElementsByTag(element);
							HTMLElements elements = supportForAttributeSelection(index,selectedNewContext,selection);
							if (elements!=null) {
								for (int i = 0; i < elements.size(); i++) {
									selection.getSelectedItems().add(elements.get(i));
								}
							}
							// for moment
							return ;
						}
					}	
				}
				// okok qui mi controllo se 
				// abbiamo effettivamente un nuovo elemento sul quale selezionare
				if (selectedNewContext!=null) {
					// si continua con la ricorsività
					recursiveSupport(selectedNewContext,index+1,selection); // ci passo l'incremento della variabile
				}
				else{
					try {
						throw new ContextNotFoundException("("+subSelections[index]+")");
					} catch (ContextNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		else{
			// siamo giunti a fine array 
			if (context instanceof HTMLElement) {
				// se è un elemento, aggiungi l'elemento alla lista di elementi selezionati
				selection.getSelectedItems().add((HTMLElement) context);
				selected = true ;
				
			}
			else if(context instanceof HTMLElements){
				// se è una lista, aggiungi la lista alla lista di elementi selezionati
				HTMLElements selectedElements = (HTMLElements) context ;
				for (int i = 0; i <selectedElements.size(); i++) {
					selection.getSelectedItems().add(selectedElements.get(i));
				}
				selected = true ;
			}
		}
	}
	/**
	 * 
	 * @author Martire91<br>
	 *	This method represents the default css selection
	 */
	public class CSSSimpleSelection implements CSSSelection{
		private static final long serialVersionUID = 1L;
		private HTMLElements selectedItems = null ;
		private String selectionString = null ;
		private String selectionCriterion = "" ;
		private CSSSimpleSelection() {this.selectedItems = new HTMLElements();}
		@Override
		public int getCountSelectedItems() {
			// TODO Auto-generated method stub
			return selectedItems.size();
		}
		@Override
		public String getSelectionCriterion() {
			// TODO Auto-generated method stub
			return this.selectionCriterion ;
		}
		@Override
		public HTMLElements getSelectedItems() {
			// TODO Auto-generated method stub
			return this.selectedItems ;
		}
		public String subSelection(int index) {
			// TODO Auto-generated method stub
			return subSelections[index];
		}
	}
	@Override
	public CSSSelection[]select(String... selections) {
		CSSSelection[]selections_ = new CSSSimpleSelection[selections.length];
		for (int i = 0; i < selections.length; i++) {
			selections_[i] = select(selections[i]);
		}
		return selections_ ;
	}



	@Override
	public HTMLNode getRootContext() {
		// TODO Auto-generated method stub
		return this.rootContext ;
	}
	
	@Override
	public void setRootContext(HTMLNode rootContext) {
		this.rootContext = rootContext;
	}
	
	

}
