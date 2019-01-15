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
package cloud.jgo.utils.command.color;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.fusesource.jansi.Ansi.Color;
import cloud.jgo.j£;
import cloud.jgo.£;
import cloud.jgo.utils.ColorString;
import cloud.jgo.utils.command.DefaultParameter;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.annotations.InvalidClassException;
import cloud.jgo.utils.command.annotations.CommandClass;
import cloud.jgo.utils.command.annotations.Configurable;
import cloud.jgo.utils.command.annotations.ParameterField;
import cloud.jgo.utils.command.annotations.ParameterMethod;
import cloud.jgo.utils.command.LocalCommand.HelpCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.TerminalColors;
import cloud.jgo.utils.command.terminal.phase.ColorLocalPhaseTerminal;

public class ColorLocalCommand extends LocalCommand {
	// version 1.0.9 :
	// variabile usata internamente
		private static ColorLocalCommand objCommand = null ;
	/**
	 * This method is very useful.
	 * Create a command to create a given type of object, 
	 * so the command is the name of the class, appropriately annotated with
	 * {@link CommandClass}, moreover, the configurable interface can also be implemented,
	 * which indicates when the configuration of a certain type of object is completed.
	 * The parameters of the command, will be the fields and methods of the class,
	 * obviously annotating everything with {@link ParameterField} and {@link ParameterMethod}.
	 * The symbol "£" acts as a space for the arguments (String) of a method, so to give as an
	 * argument for example "Hello World", we will do: -methodName hello £ world £ !!
	 * The final string will be: hello world !!
	 * @see Configurable
	 * @see CommandClass
	 * @see ParameterField
	 * @see ParameterMethod
	 * @param a The class you want to convert into command
	 * @return the command
	 */
	public static <A> ColorLocalCommand getCommandByType(Class<?> a) {
		// 1 cosa controllo che sia una classe annotata
		CommandClass commandAnnotation = null;
		if (a.isAnnotationPresent(CommandClass.class)) {
			commandAnnotation = a.getDeclaredAnnotation(CommandClass.class);
			if (commandAnnotation.command().equals("default")) {
				objCommand = new ColorLocalCommand(a.getSimpleName().toLowerCase(), commandAnnotation.help());
			} else {
				objCommand = new ColorLocalCommand(commandAnnotation.command(), commandAnnotation.help());
			}
			// parametro new : condivide l'oggetto
			Parameter parameter = objCommand.addParam("new", "This parameter instantiates the object");
			Parameter cancelParameter = objCommand.addParam("cancel","This parameter cancels the object currently being processed, thus making it \"null\"");
			parameter.setExecution(new Execution() {
				@Override
				public Object exec() {
					Object obj = null;
					try {
						obj = a.newInstance();
						objCommand.shareObject(obj);
						return ColorLocalPhaseTerminal.positiveMsg("Instantiated object");
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						return e.getMessage();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						return e.getMessage();
					}
				}
			});
			cancelParameter.setExecution(new Execution() {
				
				@Override
				public Object exec() {
					if (objCommand.getSharedObject()!=null) {
						objCommand.shareObject(null);
						return ColorLocalPhaseTerminal.positiveMsg("Cancellation of the object");
					}
					else {
						return ColorLocalPhaseTerminal.error("No objects have been processed");
					}
				}
			});
			// qui proseguo con i campi
			Field[] fields = a.getDeclaredFields();
			if (commandAnnotation.involveAllFields()) {
				// qui trasformiamo tutti i fields in parametri
				// tranne le costanti ovviamente
				for (Field field : fields) {
					field.setAccessible(true);
					// verifico che il campo non sia una costante
					if (!Modifier.isFinal(field.getModifiers())) {
						Parameter param = objCommand.addParam(field.getName(), "set " + £.escp(field.getName()) + "");
						param.setInputValueExploitable(true);
						param.setExecution(new Execution() {
							@Override
							public Object exec() {
								if (param.getInputValue() != null) {

									if (objCommand.getSharedObject() != null) {
										boolean setOk = false;
										String fieldValue = param.getInputValue();
										// controllo il tipo del campo

										if (field.getType().isPrimitive()) {
											// is a primitive
											if (field.getType().getSimpleName().equals("int")) {
												int value = Integer.parseInt(fieldValue);
												try {
													field.setInt(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("double")) {
												double value = Double.parseDouble(fieldValue);
												try {
													field.setDouble(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("float")) {
												float value = Float.parseFloat(fieldValue);
												try {
													field.setFloat(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("long")) {
												long value = Long.parseLong(fieldValue);
												try {
													field.setLong(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("short")) {
												short value = Short.parseShort(fieldValue);
												try {
													field.setShort(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("char")) {
												if (fieldValue.length() == 1) {
													try {
														field.setChar(objCommand.getSharedObject(),
																fieldValue.charAt(0));
														setOk = true;
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												} else {
													return "The \"" + field.getName()
															+ "\" field requires a single character #";
												}
											} else if (field.getType().getSimpleName().equals("boolean")) {
												short value = Short.parseShort(fieldValue);
												try {
													field.setShort(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										} else {
											// is an object
											if (!field.getType().isArray()) {

												if (field.getType().getSimpleName().equals("String")
														|| field.getType().getSimpleName().equals("StringBuffer")) {
													try {
														field.set(objCommand.getSharedObject(), fieldValue);
														setOk = true;
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												} else {
													// qui faccio il controllo del tipo di oggetto
													if (field.getType().getSimpleName().equals("Integer")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Integer.parseInt(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Double")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Double.parseDouble(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Float")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Float.parseFloat(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Long")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Long.parseLong(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Short")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Short.parseShort(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Character")) {
														if (fieldValue.length() == 1) {
															try {
																field.set(objCommand.getSharedObject(), fieldValue);
																setOk = true;
															} catch (NumberFormatException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															} catch (IllegalArgumentException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															} catch (IllegalAccessException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
														} else {
															return "The \"" + field.getName()
																	+ "\" field requires a single character #";
														}
													} else if (field.getType().getSimpleName().equals("Boolean")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Boolean.parseBoolean(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else {
														// qui si tratta di un altro tipo di oggetto
														// quindi gestire ....
													}
												}
											}
										}
										if (setOk) {
											// qui sappiamo che il settaggio è avvenuto, per cui posso controllare
											boolean completed = false;
											if (Configurable.class.isAssignableFrom(a)) {
												try {
													Method method = a.getDeclaredMethod("isCompleted", null);
													try {
														completed = (boolean) method.invoke(objCommand.getSharedObject(),
																new Object[] {});
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (InvocationTargetException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												} catch (NoSuchMethodException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (SecurityException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											if (completed)
												return ColorLocalPhaseTerminal.setOk(field.getName())+"\n"
														+ "\t\t***************** Object config:"+j£.colors("completed",Color.GREEN)+" *****************";
											else
												return ColorLocalPhaseTerminal.setOk(field.getName());
										} else {
											// da verificare ...
											return null;
										}
									} else {
										// non esiste un oggetto condiviso
										return ColorLocalPhaseTerminal.error("No instanced objects - use \"new\" param");
									}
								}
								return null;
							}
						});
					}
				}
				// qui ... :
				// prendo i metodi della classe : i metodi li filtro con l'annotazione
				Method[] methods = a.getDeclaredMethods();
				for (Method method : methods) {
					method.setAccessible(true);
					// controllo se è annotato questo metodo
					if (method.isAnnotationPresent(ParameterMethod.class)) {
						String help = method.getDeclaredAnnotation(ParameterMethod.class).help();
						// creo il parametro
						Parameter paraMethod = objCommand.addParam(method.getName(), help);
						int paramsCount = method.getParameterCount();
						// a questo punto devo verificare se il metodo ha parametri
						if (paramsCount>0) {
							// bene setto l'input sfruttabile
							paraMethod.setInputValueExploitable(true);
							// mi creo l'esecuzione
							paraMethod.setExecution(new Execution() {
								@Override
								public Object exec() {
									Object result = null ;
										if (objCommand.getSharedObject()!=null) {
											if (paraMethod.getInputValue()!=null) {
												// quindi nell'input value ci devono essere
												// paramsCount elementi
												String[]split = paraMethod.getInputValue().split(" ");
												if (split.length==paramsCount) {
													// bene i parametri sono stati forniti correttamente
													// adesso devo capire i tipi dei parametri
													Class<?>[]paramTypes=method.getParameterTypes();
													Object[]values = new Object[paramTypes.length];
													for (int i = 0; i < paramTypes.length; i++) {
														Class<?>type = paramTypes[i];
														Object currentValue = null ;
														if(type.isPrimitive()) {
															if(type.getSimpleName().equals("int"))currentValue = Integer.parseInt(split[i]);
															else if(type.getSimpleName().equals("double"))currentValue = Double.parseDouble(split[i]);
															else if(type.getSimpleName().equals("float"))currentValue = Float.parseFloat(split[i]);
															else if(type.getSimpleName().equals("short"))currentValue = Short.parseShort(split[i]);
															else if(type.getSimpleName().equals("long"))currentValue = Long.parseLong(split[i]);
															else if(type.getSimpleName().equals("boolean"))currentValue = Boolean.parseBoolean(split[i]);
															else if(type.getSimpleName().equals("char"))currentValue = split[i].charAt(0);// provvisorio ...
														}
														else if(type.isArray()) {
															// da definire ...
														}
														else {
															// is a object
															if (type.getSimpleName().equals("String"))currentValue = split[i].replaceAll("£"," ");
															else if(type.getSimpleName().equals("StringBuffer"))currentValue = split[i].replaceAll("£"," ");
															else if(type.getSimpleName().equals("Integer"))currentValue = Integer.parseInt(split[i]);
															else if(type.getSimpleName().equals("Double"))currentValue = Double.parseDouble(split[i]);
															else if(type.getSimpleName().equals("Float"))currentValue = Float.parseFloat(split[i]);
															else if(type.getSimpleName().equals("Short"))currentValue = Short.parseShort(split[i]);
															else if(type.getSimpleName().equals("Long"))currentValue = Long.parseLong(split[i]);
															else if(type.getSimpleName().equals("Boolean"))currentValue = Boolean.parseBoolean(split[i]);
															else if(type.getSimpleName().equals("Character"))currentValue = split[i].charAt(0);// provvisorio ...
														}
														if (currentValue!=null) {
															values[i] = currentValue;
														}
													}
													// okok qui abbiamo finito l'elaborazione, adesso possiamo eseguire il metodo
													try {
														result =  method.invoke(objCommand.getSharedObject(),values);
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (InvocationTargetException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
												else {
													// qui invece i parametri non si trovano
													// quindi o sn di più o di meno
													result = ColorLocalPhaseTerminal.error("Wrong number of parameters");
												}
											}
										}
										else {
											result = ColorLocalPhaseTerminal.error("non-existent object");
										}
										return result ;
								}
							});
						}
						else {
							// non c'è valore da input
							// qui facciamo una esecuzione semplice
							paraMethod.setExecution(new Execution() {
								
								@Override
								public Object exec() {
									// TODO Auto-generated method stub
									Object return_ = null ;
									if (objCommand.getSharedObject()!=null) {
										try {
											return_ = method.invoke(objCommand.getSharedObject(),new Object[] {});
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IllegalArgumentException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									else {
										return_ = ColorLocalPhaseTerminal.error("non-existent object");
									}
									return return_ ;
								}
							});
						}
					}
				}
			} else {
				// quii invece filtriamo quali field devono essere parametri
				for (Field field : fields) {
					field.setAccessible(true);
					// verifico se il campo è annotato
					if (field.isAnnotationPresent(ParameterField.class)) {
						Parameter param = objCommand.addParam(field.getName(),
								field.getAnnotation(ParameterField.class).help());
						param.setInputValueExploitable(true);
						param.setExecution(new Execution() {
							@Override
							public Object exec() {
								if (param.getInputValue() != null) {

									if (objCommand.getSharedObject() != null) {
										boolean setOk = false;
										String fieldValue = param.getInputValue();
										// controllo il tipo del campo

										if (field.getType().isPrimitive()) {
											// is a primitive
											if (field.getType().getSimpleName().equals("int")) {
												int value = Integer.parseInt(fieldValue);
												try {
													field.setInt(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("double")) {
												double value = Double.parseDouble(fieldValue);
												try {
													field.setDouble(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("float")) {
												float value = Float.parseFloat(fieldValue);
												try {
													field.setFloat(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("long")) {
												long value = Long.parseLong(fieldValue);
												try {
													field.setLong(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("short")) {
												short value = Short.parseShort(fieldValue);
												try {
													field.setShort(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											} else if (field.getType().getSimpleName().equals("char")) {
												if (fieldValue.length() == 1) {
													try {
														field.setChar(objCommand.getSharedObject(),
																fieldValue.charAt(0));
														setOk = true;
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												} else {
													return ColorLocalPhaseTerminal.error("The \"" + field.getName()
															+ "\" field requires a single character");
												}
											} else if (field.getType().getSimpleName().equals("boolean")) {
												short value = Short.parseShort(fieldValue);
												try {
													field.setShort(objCommand.getSharedObject(), value);
													setOk = true;
												} catch (IllegalArgumentException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (IllegalAccessException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										} else {
											// is an object
											if (!field.getType().isArray()) {

												if (field.getType().getSimpleName().equals("String")
														|| field.getType().getSimpleName().equals("StringBuffer")) {
													try {
														field.set(objCommand.getSharedObject(), fieldValue);
														setOk = true;
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												} else {
													// qui faccio il controllo del tipo di oggetto
													if (field.getType().getSimpleName().equals("Integer")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Integer.parseInt(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Double")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Double.parseDouble(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Float")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Float.parseFloat(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Long")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Long.parseLong(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Short")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Short.parseShort(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else if (field.getType().getSimpleName().equals("Character")) {
														if (fieldValue.length() == 1) {
															try {
																field.set(objCommand.getSharedObject(), fieldValue);
																setOk = true;
															} catch (NumberFormatException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															} catch (IllegalArgumentException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															} catch (IllegalAccessException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
														} else {
															return ColorLocalPhaseTerminal.error("The \"" + field.getName()
																	+ "\" field requires a single character");
														}
													} else if (field.getType().getSimpleName().equals("Boolean")) {
														try {
															field.set(objCommand.getSharedObject(),
																	Boolean.parseBoolean(fieldValue));
															setOk = true;
														} catch (NumberFormatException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													} else {
														// qui si tratta di un altro tipo di oggetto
														// quindi gestire ....
													}
												}
											}
										}
										if (setOk) {
											// qui sappiamo che il settaggio è avvenuto, per cui posso controllare
											boolean completed = false;
											if (Configurable.class.isAssignableFrom(a)) {
												try {
													Method method = a.getDeclaredMethod("isCompleted", null);
													try {
														completed = (boolean) method.invoke(objCommand.getSharedObject(),
																new Object[] {});
													} catch (IllegalAccessException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IllegalArgumentException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (InvocationTargetException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												} catch (NoSuchMethodException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (SecurityException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											if (completed)
												return ColorLocalPhaseTerminal.setOk(field.getName())+"\n"
														+ "\t\t***************** Object config:"+j£.colors("completed",Color.GREEN)+" *****************";
											else
												return "The \"" +ColorLocalPhaseTerminal.setOk(field.getName()) + "\" variable is set ( OK )";
										} else {
											// da verificare ...
											return null;
										}
									} else {
										// non esiste un oggetto condiviso
										return ColorLocalPhaseTerminal.error("No instanced objects - use \"new\" param");
									}
								}
								return null;
							}
						});
					}
				}
				// qui ... :
				// prendo i metodi della classe : i metodi li filtro con l'annotazione
				Method[] methods = a.getDeclaredMethods();
				for (Method method : methods) {
					method.setAccessible(true);
					// controllo se è annotato questo metodo
					if (method.isAnnotationPresent(ParameterMethod.class)) {
						String help = method.getDeclaredAnnotation(ParameterMethod.class).help();
						int paramsCount = method.getParameterCount();
						// creo il parametro
						Parameter paraMethod = objCommand.addParam(method.getName(), help);
						if (paramsCount>0) {
							// il metodo ha dei parametri, quindi setto l'input value sfruttabile
							paraMethod.setInputValueExploitable(true);
							paraMethod.setExecution(new Execution() {
								@Override
								public Object exec() {
									if (paraMethod.getInputValue()!=null) {
										// quindi nell'input value ci devono essere
										// paramsCount elementi
										String[]split = paraMethod.getInputValue().split(" ");
										if (split.length==paramsCount) {
											// bene i parametri sono stati forniti correttamente
											// adesso devo capire i tipi dei parametri
											Class<?>[]paramTypes=method.getParameterTypes();
											Object[]values = new Object[paramTypes.length];
											for (int i = 0; i < paramTypes.length; i++) {
												Class<?>type = paramTypes[i];
												Object currentValue = null ;
												if(type.isPrimitive()) {
													if(type.getSimpleName().equals("int"))currentValue = Integer.parseInt(split[i]);
													else if(type.getSimpleName().equals("double"))currentValue = Double.parseDouble(split[i]);
													else if(type.getSimpleName().equals("float"))currentValue = Float.parseFloat(split[i]);
													else if(type.getSimpleName().equals("short"))currentValue = Short.parseShort(split[i]);
													else if(type.getSimpleName().equals("long"))currentValue = Long.parseLong(split[i]);
													else if(type.getSimpleName().equals("boolean"))currentValue = Boolean.parseBoolean(split[i]);
													else if(type.getSimpleName().equals("char"))currentValue = split[i].charAt(0);// provvisorio ...
												}
												else if(type.isArray()) {
													// da definire ...
												}
												else {
													// is a object
													if (type.getSimpleName().equals("String"))currentValue = split[i];
													else if(type.getSimpleName().equals("StringBuffer"))currentValue = split[i];
													else if(type.getSimpleName().equals("Integer"))currentValue = Integer.parseInt(split[i]);
													else if(type.getSimpleName().equals("Double"))currentValue = Double.parseDouble(split[i]);
													else if(type.getSimpleName().equals("Float"))currentValue = Float.parseFloat(split[i]);
													else if(type.getSimpleName().equals("Short"))currentValue = Short.parseShort(split[i]);
													else if(type.getSimpleName().equals("Long"))currentValue = Long.parseLong(split[i]);
													else if(type.getSimpleName().equals("Boolean"))currentValue = Boolean.parseBoolean(split[i]);
													else if(type.getSimpleName().equals("Character"))currentValue = split[i].charAt(0);// provvisorio ...
												}
												if (currentValue!=null) {
													values[i] = currentValue;
												}
											}
											// okok qui abbiamo finito l'elaborazione, adesso possiamo eseguire il metodo
											try {
												return method.invoke(objCommand.getSharedObject(),values);
											} catch (IllegalAccessException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (IllegalArgumentException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (InvocationTargetException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										else {
											// qui invece i parametri non si trovano
											// quindi o sn di più o di meno
											return ColorLocalPhaseTerminal.error("Wrong number of parameters");
										}
									}
									return null ;
								}
							});
						}
						else {
						paraMethod.setExecution(new Execution() {
							@Override
							public Object exec() {
								if (objCommand.getSharedObject() != null) {
									Object return_ = null;
									try {
										return_ = method.invoke(objCommand.getSharedObject(), new Object[] {});
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									return return_;
								} else {
									return ColorLocalPhaseTerminal.error("non-existent object");
								}
							}
						});
						}
					}
				}
			}
		} else {
			// dare una eccezzione
			try {
				throw new InvalidClassException(a);
			} catch (InvalidClassException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return objCommand;
	}
	/**
	 * This method prints a report of all the fields of the shared object, then
	 * tells us which variables have been set and which are not, all this happens
	 * through reflection.
	 * 
	 * @param sharedObject
	 *            the shared object
	 * @param fieldNameColor
	 *            field name color
	 * @param fieldValueColor
	 *            field value color
	 * @return the shared object configuration
	 * @throws IllegalArgumentException
	 *             1 exception
	 * @throws IllegalAccessException
	 *             2 exception
	 */
	public static String toString(Object sharedObject, Color fieldNameColor, Color fieldValueColor)
			throws IllegalArgumentException, IllegalAccessException {
		ColorString string = new ColorString();
		if (sharedObject.getClass().isAnnotationPresent(CommandClass.class)) {
			string.append("-----------------------------------------------------------------------------------\n");
			if (((CommandClass)sharedObject.getClass().getDeclaredAnnotation(CommandClass.class)).command().equals("default")) {
				string.append(" " + sharedObject.getClass().getSimpleName()).append(" ~ Configuration\n", Color.BLUE);	
			}
			else {
				//sharedObject.getClass().getSimpleName()
				string.append(" " + ((CommandClass)sharedObject.getClass().getDeclaredAnnotation(CommandClass.class)).command()).append(" ~ Configuration\n", Color.BLUE);
			}
			string.append("-----------------------------------------------------------------------------------\n");
			Class<?> clazz = sharedObject.getClass();
			Field[] fields = clazz.getDeclaredFields();
			int count = 0;
			// here
			if (((CommandClass) clazz
					.getAnnotation(CommandClass.class)).involveAllFields()) {
				// qui vengono coinvolti tutti i parametri
				for (Field field : fields) {
					field.setAccessible(true);
//					if (!Modifier.isFinal(field.getModifiers())) {
						String fieldName = field.getName();
						Object fieldValue = field.get(sharedObject);
						if (count == 3) {
							// si va a capo
							count = 0;
							string.append("\n\n");
						}
						string.append("* " + fieldName, fieldNameColor).append("=", Color.WHITE)
						.append(fieldValue + "", fieldValueColor).append("  ");
//					}
					count++;
				}
			} else {
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(ParameterField.class)) {
						String fieldName = field.getName();
						Object fieldValue = field.get(sharedObject);
						if (count == 3) {
							// si va a capo
							count = 0;
							string.append("\n\n");
						}
						string.append("* " + fieldName, fieldNameColor).append("=", Color.WHITE)
						.append(fieldValue + "", fieldValueColor).append("  ");
					}
					count++;
				}
			}
			// okok qui controllo se l'oggetto è una instanza di configurable
						if (Configurable.class.isInstance(sharedObject)) {
							// qui devo individuare i 3 metodi
							Method[]methods = sharedObject.getClass().getDeclaredMethods();
							for (Method method : methods) {
								method.setAccessible(true);
								if (method.getName().equals("isCompleted")) {
									try {
										boolean result = (boolean) method.invoke(sharedObject,new Object[] {});
										if (!string.toString().endsWith("\n\n")) {
											string.append("\n\n").append("* " + method.getName(), fieldNameColor).append("=", Color.WHITE)
											.append(result + "", fieldValueColor).append("  ");
										}
										else {
											string.append("* " + method.getName(), fieldNameColor).append("=", Color.WHITE)
											.append(result + "", fieldValueColor).append("  ");
										}
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								// continuare da qui con gli altri due metodi di configurable ...
							}
						}
			return string.toString() + "\n";
		} else {
			Class<?extends Object>superClass = sharedObject.getClass().getSuperclass();
			if (superClass!=null) {
				if (superClass.isAnnotationPresent(CommandClass.class)) {
					string.append("-----------------------------------------------------------------------------------\n");
					if (((CommandClass)superClass.getDeclaredAnnotation(CommandClass.class)).command().equals("default")) {
						string.append(" " + superClass.getSimpleName()).append(" ~ Configuration\n", Color.BLUE);	
					}
					else {
						//sharedObject.getClass().getSimpleName()
						string.append(" " + ((CommandClass)superClass.getDeclaredAnnotation(CommandClass.class)).command()).append(" ~ Configuration\n", Color.BLUE);
					}
					string.append("-----------------------------------------------------------------------------------\n");
					Class<?> clazz = superClass;
					Field[] fields = clazz.getDeclaredFields();
					int count = 0;
					// here
					if (((CommandClass) superClass
							.getAnnotation(CommandClass.class)).involveAllFields()) {
						// qui vengono coinvolti tutti i parametri
						for (Field field : fields) {
							field.setAccessible(true);
//							if (!Modifier.isFinal(field.getModifiers())) {
								String fieldName = field.getName();
								Object fieldValue = field.get(sharedObject);
								if (count == 3) {
									// si va a capo
									count = 0;
									string.append("\n\n");
								}
								string.append("* " + fieldName, fieldNameColor).append("=", Color.WHITE)
								.append(fieldValue + "", fieldValueColor).append("  ");
//							}
							count++;
						}
					} else {
						for (Field field : fields) {
							field.setAccessible(true);
							if (field.isAnnotationPresent(ParameterField.class)) {
								String fieldName = field.getName();
								Object fieldValue = field.get(sharedObject);
								if (count == 3) {
									// si va a capo
									count = 0;
									string.append("\n\n");
								}
								string.append("* " + fieldName, fieldNameColor).append("=", Color.WHITE)
								.append(fieldValue + "", fieldValueColor).append("  ");
							}
							count++;
						}
					}
				}
				else {
					string = null ;
					try {
						throw new InvalidClassException(sharedObject.getClass());
					} catch (InvalidClassException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				string = null ;
				try {
					throw new InvalidClassException(sharedObject.getClass());
				} catch (InvalidClassException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (string!=null) return string.toString() + "\n";
		else return null ;
	}
	private ColorHelpCommand helpCommand = new ColorHelpCommand();

	public ColorLocalCommand(String command, String help) {
		super(command, help);
		// ricarico l'help colorato
		this.helpCommand.reload(this);
	}

	public ColorLocalCommand(String command, String help, Execution execution) {
		// TODO Auto-generated constructor stub
		super(command, help, execution);
		// ricarico l'help colorato
		this.helpCommand.reload(this);
	}

	@Override
	public ColorHelpCommand getHelpCommand() {
		// TODO Auto-generated method stub
		return this.helpCommand;
	}

	public static class ColorHelpCommand extends HelpCommand {
		public void reload(LocalCommand command) {
			this.command = command;
			this.buffer = new StringBuffer();
			buffer.append("===================================================================================\n");
			if (this.command.getBelongsTo() != null) {
				buffer.append("HELP Of " + "\"" + j£.colors(this.command.getCommand(), TerminalColors.COMMAND_COLOR)
						+ "\" - Phase :"
						+ j£.colors(this.command.getBelongsTo().phaseName(), TerminalColors.PHASE_COLOR) + "\n");
			} else {
				buffer.append("HELP Of " + "\"" + j£.colors(this.command.getCommand(), TerminalColors.COMMAND_COLOR)
						+ "\" - Phase :" + j£.colors("absent", Color.DEFAULT) + "\n");
			}
			buffer.append("===================================================================================\n");
			// qui devo prendere tutti i parameters
			Collection<Parameter> collection = command.getStructure().values();
			List<Parameter> orderParameters = command.sortParameters();
			// qui ci sarà la descrizione del comando root
			buffer.append(this.command.getHelp().toUpperCase() + "   / has input value ="
					+ j£.colors(this.command.hasInputValueExploitable() + "", Color.GREEN) + "\n\n");
			if (orderParameters != null) {
				buffer.append("* Parameters :" + orderParameters + " :\n\n");
				if (this.command.hasParameters()) {
					// ci sono parametri
					// quindi qui devo prendere i params
					Iterator<Parameter> iterator = orderParameters.iterator();
					while (iterator.hasNext()) {
						Parameter param = iterator.next();
						buffer.append(j£.colors(param.getParam(), TerminalColors.PARAMETER_COLOR) + "="
								+ param.getParameterHelp() + "  / has input value ="
								+ j£.colors(param.hasInputValueExploitable() + "", Color.GREEN) + "\n");
					}
				}
			} else {
				buffer.append("* Parameters :" + collection + " :\n\n");
				if (this.command.hasParameters()) {
					// ci sono parametri
					// quindi qui devo prendere i params
					Iterator<Entry<String, Parameter>> iterator = command.iterator();
					while (iterator.hasNext()) {
						Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter> entry = (Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter>) iterator
								.next();
						Parameter param = entry.getValue();
						buffer.append(j£.colors(param.getParam(), TerminalColors.PARAMETER_COLOR) + "="
								+ param.getParameterHelp() + "  / has input value ="
								+ j£.colors(param.hasInputValueExploitable() + "", Color.GREEN) + "\n");
					}
				}
			}
		}
	}

}
