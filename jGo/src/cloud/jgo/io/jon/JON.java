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
package cloud.jgo.io.jon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

import cloud.jgo.Home;
import cloud.jgo.£;
import cloud.jgo.io.File;

/**
 * 
 * @author Martire91<br>
 *         <strong>J</strong> - <em>Java</em><br>
 *         <strong>O</strong> - <em>Object</em><br>
 *         <strong>N</strong> - <em>Notation</em><br>
 *         This technology will be deepened in the next version.
 *
 */
public final class JON extends Home { // java general object notation
	public final static String OPEN_COMMENT = "£/";
	public final static String CLOSE_COMMENT = "/£";

	private static void comments(JONComment commentJon, BufferedWriter writer) {
		if (!commentJon.comment().contains("\n") && !commentJon.comment().contains("\r\n")) {
			try {
				writer.write(OPEN_COMMENT + " " + commentJon.comment() + " " + CLOSE_COMMENT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @author Martire91<br>
	 *         This class will be deepened in the next version.
	 * @param <T>
	 *            the file type
	 *
	 */
	public static class JONFile<T> implements Serializable {
		private static final long serialVersionUID = 1L;
		private cloud.jgo.io.File file = null;
		private Class<?> type = null;
		private String classPackage = null;
		private T unserialized = null;

		/**
		 * This method returns the serialized object
		 * 
		 * @return the serialized object
		 */
		public T getUnserialized() {
			return unserialized;
		}

		/**
		 * This method sets the serialized object
		 * 
		 * @param o
		 *            serialized object
		 */
		private void setUnserialized(T o) {
			this.unserialized = o;
		}

		/**
		 * This method returns the class package
		 * 
		 * @return the class package text
		 */
		public String getClassPackage() {
			return classPackage;
		}

		/**
		 * This method returns the file
		 * 
		 * @return the file
		 */
		public cloud.jgo.io.File getFile() {
			return file;
		}

		/**
		 * This method sets the type
		 * 
		 * @param type
		 *            the type
		 */
		private void setType(Class<?> type) {
			this.type = type;
			this.classPackage = this.type.getName();
		}

		/**
		 * This method returns the type
		 * 
		 * @return the type
		 */
		public Class<?> getType() {
			return this.type;
		}

		/**
		 * This method sets the file
		 * 
		 * @param file
		 *            the file
		 */
		private void setFile(cloud.jgo.io.File file) {
			this.file = file;
		}

		private JONFile() {
			// constructor void
		}

		// constructor private
		public JONFile(String jonFileName) {
			if (new File(jonFileName).exists()) {
				if (£.extractFormatFromFileName(jonFileName).equals("jon")) {
					// imposto il file prima cosa di tutta
					setFile(new File(jonFileName));
					try {
						this.unserialized = unmarsh(this.file);
						if (this.unserialized != null) {
							setType(this.unserialized.getClass());
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						throw new JONInvalidFileFormatException(£.extractFormatFromFileName(jonFileName));
					} catch (JONInvalidFileFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		/**
		 * This method returns file sintax
		 * 
		 * @return file sintax
		 */
		public String getSyntax() {
			String code = null;
			try {
				code = £.readFile(getFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return code;
		}

		/**
		 * This method returns the comprehensible sintax
		 * 
		 * @return the comprehensible sintax
		 */
		public String getComprehensibleSyntax() {
			String code = null;
			try {
				code = £.readFile(getFile());
				code = code.replaceAll("£_", "");
				if (code.contains(OPEN_COMMENT)) {
					code = code.replaceAll(OPEN_COMMENT, "");
				}
				if (code.contains(CLOSE_COMMENT)) {
					code = code.replaceAll(CLOSE_COMMENT, "");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return code;
		}

	}

	private JON() {

		/*
		 * private constructor
		 */
	}

	/**
	 * This method convert an object to JONFile
	 * 
	 * @param obj
	 *            the object
	 * @param fileName
	 *            the file name
	 * @return the JON file
	 * @throws FileNotFoundException
	 *             1 exception
	 * @throws IllegalArgumentException
	 *             2 exception
	 * @throws IllegalAccessException
	 *             3 exception
	 * @throws JONMarshallingException
	 *             4 exception :
	 * @throws JONNotSupportedTypeException
	 *             5 exception
	 */
	public static JONFile marsh(Object obj, String fileName) throws FileNotFoundException, IllegalArgumentException,
			IllegalAccessException, JONMarshallingException, JONNotSupportedTypeException {
		if (!fileName.contains(".")) {
			fileName = fileName + ".jon";
		}
		JONFile file_jgo = null;
		if (£.extractFormatFromFileName(fileName).equals("jon")) {
			file_jgo = new JONFile();
			cloud.jgo.io.File file = new cloud.jgo.io.File(fileName);
			Class<?> classObj = obj.getClass();
			// in tanto ottengo le classi delle annotazioni

			JONClass annotationClass = classObj.getAnnotation(JONClass.class);

			if (annotationClass != null) {
				JONComment commentJon = classObj.getAnnotation(JONComment.class);

				// okok qui preparo il file

				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				try {
					if (commentJon != null) {

						comments(commentJon, writer);
					}
					writer.write("£_" + classObj.getName());
					writer.newLine();
					writer.write("£_" + classObj.getSimpleName() + "{");
					writer.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// okok la classe è serializzabile

				// 1 passo ottengo tutti i campi prima cosa di tutte

				Field[] fields = classObj.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					// commento field fino a #
					if (field.isAnnotationPresent(JONComment.class)) {
						// okok il campo è annotato
						JONComment fieldComment = field.getAnnotation(JONComment.class);
						comments(fieldComment, writer);
					}
					// #
					if (field.isAnnotationPresent(JONField.class)) {
						// scrivo il field

						// qui scrivo il file
						Object object = field.get(obj);
						if (object != null) {
							if (field.getType().isArray()) {
								Class<?> typeComponentArray = field.getType().getComponentType();
								if (!typeComponentArray.isPrimitive()
										&& !typeComponentArray.getSimpleName().equals("String")
										&& !isWrapper(typeComponentArray)) {

									// okok è un tipo object custom

									if (typeComponentArray.isAnnotationPresent(JONClass.class)) {
										try {
											writer.append("£_" + field.getName());
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}

								} else {
									try {
										writer.append("£_" + field.getName());
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								if (typeComponentArray.isPrimitive()) {

									// okok devo sviluppare qui dentro, per questo si vede
									// male sul file l'array int perchè ancora non ho definito
									// qui dentro

									marshPrimitiveArrayChild(typeComponentArray, object, writer);
									try {
										writer.newLine();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {
									/*
									 * E' un array di oggetti
									 */
									if (object instanceof String[]) {
										// mi sostituisce le stringhe con le stringhe nelle stringhe
										String[] array = (String[]) object;
										for (int i = 0; i < array.length; i++) {
											if (array[i] == null) {
												array[i] = "null";
											}
											if (array[i].contains("\n")) {
												array[i] = array[i].replaceAll("\n", "@n");
											}
											array[i] = "\"" + array[i] + "\"";

										}
										try {
											writer.write(Arrays.toString((array)));
											writer.newLine();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {
										if (isWrapper(field.getType().getComponentType())) {
											marshWrapperArrayChild(object, writer, true);
										} else {
											if (typeComponentArray.isAnnotationPresent(JONClass.class)) {

												// si procede
												// array di oggetti custom

												throw new JONNotSupportedTypeException(field.getType());

											} else {
												throw new JONMarshallingException(typeComponentArray);
											}
										}
									}
								}
							} else {
								// qui c'è da controllare se un custom oggetto o uno predefinito
								// tra quelli memorizzabili
								if (field.getType().getSimpleName().equals("String") || isWrapper(field)
										|| field.getType().isPrimitive()) {

									marshSimpleObjectChild(object, field, writer, true);
								} else {
									// metodo ricorsivo
									try {
										marshObjectChild(object, field.getName(), writer);
									} catch (JONMarshallingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}
				}

				try {
					writer.write("}");
					writer.flush();
					// imposto i dati del file jon
					file_jgo.setType(classObj);
					file_jgo.setFile(file);
					// deserializzo il file
					Object des = null;
					try {
						des = unmarsh(file);
						file_jgo.setUnserialized(des);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					throw new JONMarshallingException(classObj);
				} catch (JONMarshallingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				throw new JONInvalidFileFormatException(£.extractFormatFromFileName(fileName));
			} catch (JONInvalidFileFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file_jgo;
	}

	private static void marshWrapperArrayChild(Object object, BufferedWriter writer, boolean flag) {
		try {
			writer.write(Arrays.toString(((Object[]) object)));
			if (flag) {
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// questo metodo scrive un array di tipi primitivi
	private static void marshPrimitiveArrayChild(Class<?> typeComponentArray, Object object, BufferedWriter writer) {
		if (typeComponentArray.getSimpleName().equals("int")) {

			try {
				writer.write(Arrays.toString((int[]) (object)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeComponentArray.getSimpleName().equals("double")) {
			try {
				writer.write(Arrays.toString((double[]) (object)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeComponentArray.getSimpleName().equals("float")) {
			try {
				writer.write(Arrays.toString((float[]) (object)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeComponentArray.getSimpleName().equals("byte")) {
			try {
				writer.write(Arrays.toString((byte[]) (object)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeComponentArray.getSimpleName().equals("long")) {
			try {
				writer.write(Arrays.toString((long[]) (object)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeComponentArray.getSimpleName().equals("char")) {
			try {
				writer.write(Arrays.toString((char[]) (object)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeComponentArray.getSimpleName().equals("boolean")) {
			try {
				writer.write(Arrays.toString((boolean[]) (object)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void marshPrimitiveType(Object fieldValueObj, Field field, BufferedWriter writer) {
		try {
			writer.write("£_" + field.getName() + " " + fieldValueObj);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void marshObjectChild(Object obj, String idField, BufferedWriter writer)
			throws FileNotFoundException, IllegalArgumentException, IllegalAccessException, JONMarshallingException {
		Class<?> classObj = obj.getClass();
		// in tanto ottengo le classi delle annotazioni
		JONClass annotationClass = classObj.getAnnotation(JONClass.class);
		if (annotationClass != null) {
			// okok qui preparo il file
			JONComment commentJon = classObj.getAnnotation(JONComment.class);
			try {
				if (commentJon != null) {
					comments(commentJon, writer);
				}
				writer.append("£_" + idField + "{");
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// okok la classe è serializzabile

			// 1 passo ottengo tutti i campi prima cosa di tutte
			Field[] fields = classObj.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				// commento field fino a #
				if (field.isAnnotationPresent(JONComment.class)) {
					// okok il campo è annotato
					JONComment fieldComment = field.getAnnotation(JONComment.class);
					comments(fieldComment, writer);
				}
				// #
				if (field.isAnnotationPresent(JONField.class)) {
					// scrivo il field
					// qui scrivo il file
					Object object = field.get(obj);
					if (object != null) {
						if (field.getType().isArray()) {
							// solo se l'array è diverso da null si scrive sul file
							Class<?> typeComponentArray = field.getType().getComponentType();

							// controllo se la classe dell'array è annotata

							/**
							 * N.B. - forse ca copiato in altri punti sto pezzo di codice
							 */
							// questo controllo è importante, da qui a #

							if (!typeComponentArray.isPrimitive()
									&& !typeComponentArray.getSimpleName().equals("String")
									&& !isWrapper(typeComponentArray)) {

								// okok è un tipo object custom

								if (typeComponentArray.isAnnotationPresent(JONClass.class)) {
									try {
										writer.append("£_" + field.getName());
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}

							} else {
								try {
									writer.append("£_" + field.getName());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

							// #

							if (typeComponentArray.isPrimitive()) {

								marshPrimitiveArrayChild(typeComponentArray, object, writer);

								try {
									writer.newLine();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								/*
								 * E' un array di oggetti
								 */
								if (object instanceof String[]) {
									// mi sostituisce le stringhe con le stringhe nelle stringhe
									String[] array = (String[]) object;
									for (int i = 0; i < array.length; i++) {
										if (array[i] == null) {
											array[i] = "null";
										}
										if (array[i].contains("\n")) {
											array[i] = array[i].replaceAll("\n", "@n");
										}
										array[i] = "\"" + array[i] + "\"";
									}
									try {
										writer.append(Arrays.toString((array)));
										writer.newLine();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (isWrapper(object.getClass().getComponentType())) {

									marshWrapperArrayChild(object, writer, true);
								} else {
									// array custom objects

									// qui controllo di nuovo che la classe sia annotata
									// in caso contrario lancio l'eccezzione

									if (typeComponentArray.isAnnotationPresent(JONClass.class)) {
										try {
											throw new JONNotSupportedTypeException(field.getType());
										} catch (JONNotSupportedTypeException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {
										throw new JONMarshallingException(typeComponentArray);
									}
								}
							}
						} else {
							// qui c'è da controllare se un custom oggetto o uno predefinito
							// tra quelli memorizzabili
							if (field.getType().getSimpleName().equals("String") || isWrapper(field)
									|| field.getType().isPrimitive()) {
								marshSimpleObjectChild(object, field, writer, true);
							} else {
								// is a custom object
								marshObjectChild(object, field.getName(), writer);
							}
						}
					}

				}
			}

			try {
				writer.append("£" + idField + "}");
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				throw new JONUnmarshallingException();
			} catch (JONUnmarshallingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// questo metodo scrive : wrapper,stringhe e anche tipi primitivi
	private static void marshSimpleObjectChild(Object object, Field field, BufferedWriter writer, boolean flag) {
		if (field.getType().getSimpleName().equals("String")) {
			// controllo se ci sono andate a capo

			String objectString = (String) object;
			if (objectString.contains("\n")) {
				// sostituisco con il simbolo apposito
				objectString = objectString.replaceAll("\n", "@n");
			}

			try {
				writer.append("£_" + field.getName() + " " + objectString);
				if (flag) {
					writer.newLine();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				writer.append("£_" + field.getName() + " " + object);
				if (flag) {
					writer.newLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method converts the file into the object
	 * 
	 * @param fileName
	 *            jon file name
	 * @param <T>
	 *            the type
	 * @return the object
	 * @throws ClassNotFoundException
	 *             1 exception
	 * @throws InstantiationException
	 *             2 exception
	 * @throws IllegalAccessException
	 *             3 exception
	 */
	@SuppressWarnings("static-access")
	public static <T> T unmarsh(String fileName)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Object object = null;
		if (£.extractFormatFromFile(new File(fileName)).equals("jon")) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String leggi;
			StringBuffer bufferText = new StringBuffer();
			try {
				while ((leggi = reader.readLine()) != null) {
					// dal momento che sappiamo che i commenti jon sono solo su una riga
					// anzi che or mettiamo and
					if (!leggi.startsWith(OPEN_COMMENT) || !leggi.endsWith(CLOSE_COMMENT)) {
						bufferText.append(leggi + "\n");
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// prendo il nome del package
			String[] split = bufferText.toString().split("\n");
			String package_ = split[0].replace("£_", "").trim();
			Class<?> class_ = Class.forName(package_);
			Constructor<?>[] constructors = class_.getDeclaredConstructors();
			boolean constr_is_void = false;
			for (int i = 0; i < constructors.length; i++) {
				constructors[i].setAccessible(true);
				if (constructors[i].getParameterCount() == 0) {
					constr_is_void = true;
					break;
				}
			}
			if (constr_is_void) {
				// 2 passo : mi serve creare una intanza di questa classe
				// per catturare le variabili,metodi
				object = class_.newInstance();
				// 3 passo alla ricerca dei fields sulla classe
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String id = field.getName();
					int len = id.length();
					// 4 passo controllo in quale riga è questo id
					for (int i = 0; i < split.length; i++) {
						if (split[i].contains("£_" + id)) {
							// okok siamo nella riga del campo
							String rowField = split[i];

							// abbiamo ottenuto la riga in cui vi è il campo
							// a questo punto in base a che tipo è gli do il valore

							// devo solo verificare se è un oggetto proprio
							if (field.getType().isPrimitive()) {
								// tipo primitivo

								String valueFieldPrimitive = rowField.substring(rowField.indexOf(" ")).trim();

								unmarshPrimitiveType(field, object, valueFieldPrimitive);

							} else if (field.getType().isArray()) {
								// array

								unmarshArray(field, rowField, id, object);
							} else {
								if (field.getType().getSimpleName().equals("String") || isWrapper(field)) {
									// so che il primo spazio è quello della sintassi
									// quindi prendo dal primo spazio in poi

									String valueField = rowField.substring(rowField.indexOf(" ")).trim();

									// okok posso settare il valore del field

									// controlliamo di che tipo si tratta

									unmarshSimpleObjectChild(field, valueField, object);
								} else {
									// custom object
									unmarshObjectChild(bufferText.toString(), field, object);
								}
							}
						}
					}
				}
			} else {
				try {
					throw new JONUnmarshallingException();
				} catch (JONUnmarshallingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				throw new JONInvalidFileFormatException(£.extractFormatFromFileName(fileName));
			} catch (JONInvalidFileFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (T) object;
	}

	/**
	 * This method converts the file into the object
	 * 
	 * @param file
	 *            the file
	 * @param <T>
	 *            the type
	 * @return the object
	 * @throws ClassNotFoundException
	 *             1 exception
	 * @throws InstantiationException
	 *             2 exception
	 * @throws IllegalAccessException
	 *             3 exception
	 */
	@SuppressWarnings("static-access")
	public static <T> T unmarsh(cloud.jgo.io.File file)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Object object = null;
		if (£.extractFormatFromFile(file).equals("jon")) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String leggi;
			StringBuffer bufferText = new StringBuffer();
			try {
				while ((leggi = reader.readLine()) != null) {
					// dal momento che sappiamo che i commenti jon sono solo su una riga
					// anzi che or mettiamo and
					if (!leggi.startsWith(OPEN_COMMENT) || !leggi.endsWith(CLOSE_COMMENT)) {
						bufferText.append(leggi + "\n");
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// prendo il nome del package
			String[] split = bufferText.toString().split("\n");
			String package_ = split[0].replace("£_", "").trim();
			Class<?> class_ = Class.forName(package_);
			Constructor<?>[] constructors = class_.getDeclaredConstructors();
			boolean constr_is_void = false;
			for (int i = 0; i < constructors.length; i++) {
				constructors[i].setAccessible(true);
				if (constructors[i].getParameterCount() == 0) {
					constr_is_void = true;
					break;
				}
			}
			if (constr_is_void) {
				// 2 passo : mi serve creare una intanza di questa classe
				// per catturare le variabili,metodi
				object = class_.newInstance();
				// 3 passo alla ricerca dei fields sulla classe
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String id = field.getName();
					int len = id.length();
					// 4 passo controllo in quale riga è questo id
					for (int i = 0; i < split.length; i++) {
						if (split[i].contains("£_" + id)) {
							// okok siamo nella riga del campo
							String rowField = split[i];

							// abbiamo ottenuto la riga in cui vi è il campo
							// a questo punto in base a che tipo è gli do il valore

							// devo solo verificare se è un oggetto proprio
							if (field.getType().isPrimitive()) {
								// tipo primitivo

								String valueFieldPrimitive = rowField.substring(rowField.indexOf(" ")).trim();

								unmarshPrimitiveType(field, object, valueFieldPrimitive);

							} else if (field.getType().isArray()) {
								// array

								unmarshArray(field, rowField, id, object);
							} else {
								if (field.getType().getSimpleName().equals("String") || isWrapper(field)) {
									// so che il primo spazio è quello della sintassi
									// quindi prendo dal primo spazio in poi

									String valueField = rowField.substring(rowField.indexOf(" ")).trim();

									// okok posso settare il valore del field

									// controlliamo di che tipo si tratta

									unmarshSimpleObjectChild(field, valueField, object);
								} else {
									// custom object
									unmarshObjectChild(bufferText.toString(), field, object);
								}
							}
						}
					}
				}
			} else {
				try {
					throw new JONUnmarshallingException();
				} catch (JONUnmarshallingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				throw new JONInvalidFileFormatException(£.extractFormatFromFile(file));
			} catch (JONInvalidFileFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (T) object;
	}

	private static void unmarshArray(Field field, String rowField, String id, Object object) {

		if (field.getType().getComponentType().isPrimitive()) {
			// è un array di tipi primitivi

			// devo prendere il valore

			if (rowField.startsWith("£_" + id + "[")) {

				// a questo punto sappiamo che la prima quadra
				// e della sintassi e che l'ultima anche
				// per cui facciamo, per cui ritaglio la stringa fra queste due parentesi

				int indexFirst = rowField.indexOf("[");
				int indexLast = rowField.lastIndexOf("]");

				String arrayValue = rowField.substring(indexFirst, indexLast + 1);

				arrayValue = arrayValue.replace("[", "").replace("]", "");

				// splitto per la virgola

				String[] stringArray = arrayValue.split(",");

				// verifico di che array si tratta per creare una nuovo tipo primitivo

				try {
					unmarshPrimitiveArrayChild(field, stringArray, object);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} else {
			// qui va ridefinito forse ...
			// è un array di oggetti

			if (isWrapper(field.getType().getComponentType())
					|| field.getType().getComponentType().getSimpleName().equals("String")) {
				int indexFirst = rowField.indexOf("[");
				int indexLast = rowField.lastIndexOf("]");

				String arrayValue = rowField.substring(indexFirst, indexLast + 1);

				arrayValue = arrayValue.replace("[", "").replace("]", "");

				try {
					unmarshWrapperOrStringArrayChild(field, arrayValue, object);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// objects custom array
			}
		}
	}

	// questo metodo estrae un array di wrapper o stringhe
	private static void unmarshWrapperOrStringArrayChild(Field field, String arrayValue, Object object)
			throws IllegalArgumentException, IllegalAccessException {
		if (field.getType().getComponentType().getSimpleName().equals("String")) {
			// qui devo verificare se sicuro tutto è funzionale
			String[] stringArray = arrayValue.split("\",");
			String[] arrayString = new String[stringArray.length];
			for (int j = 0; j < stringArray.length; j++) {
				stringArray[j] = stringArray[j].replaceAll("\"", "").trim();
				// controllo se ci sono andate a capo
				if (stringArray[j].contains("@n")) {
					stringArray[j] = stringArray[j].replaceAll("@n", "\n");
				}
				if (stringArray[j].equals("null")) {
					stringArray[j] = null;
				}
				arrayString[j] = stringArray[j];
			}
			// qui che ho init l'array string,posso settare quello del field

			field.set(object, arrayString);
		}

		else if (field.getType().getComponentType().getSimpleName().equals("Integer")) {
			String[] array = arrayValue.split(",");
			Integer[] integs = new Integer[array.length];
			for (int j = 0; j < array.length; j++) {
				array[j] = array[j].trim();
				integs[j] = Integer.parseInt(array[j]);
			}
			field.set(object, integs);
		}

		else if (field.getType().getComponentType().getSimpleName().equals("Double")) {
			String[] array = arrayValue.split(",");
			Double[] integs = new Double[array.length];
			for (int j = 0; j < array.length; j++) {
				array[j] = array[j].trim();
				integs[j] = Double.parseDouble(array[j]);
			}
			field.set(object, integs);
		} else if (field.getType().getComponentType().getSimpleName().equals("Float")) {
			String[] array = arrayValue.split(",");
			Float[] integs = new Float[array.length];
			for (int j = 0; j < array.length; j++) {
				array[j] = array[j].trim();
				integs[j] = Float.parseFloat(array[j]);
			}
			field.set(object, integs);
		} else if (field.getType().getComponentType().getSimpleName().equals("Long")) {
			String[] array = arrayValue.split(",");
			Long[] integs = new Long[array.length];
			for (int j = 0; j < array.length; j++) {
				array[j] = array[j].trim();
				integs[j] = Long.parseLong(array[j]);
			}
			field.set(object, integs);
		} else if (field.getType().getComponentType().getSimpleName().equals("Boolean")) {
			String[] array = arrayValue.split(",");
			Boolean[] integs = new Boolean[array.length];
			for (int j = 0; j < array.length; j++) {
				array[j] = array[j].trim();
				integs[j] = Boolean.parseBoolean(array[j]);
			}
			field.set(object, integs);
		} else if (field.getType().getComponentType().getSimpleName().equals("Byte")) {
			String[] array = arrayValue.split(",");
			Byte[] integs = new Byte[array.length];
			for (int j = 0; j < array.length; j++) {
				array[j] = array[j].trim();
				integs[j] = Byte.parseByte(array[j]);
			}
			field.set(object, integs);
		} else if (field.getType().getComponentType().getSimpleName().equals("Character")) {
			String[] array = arrayValue.split(",");
			Character[] integs = new Character[array.length];
			for (int j = 0; j < array.length; j++) {
				array[j] = array[j].trim();
				integs[j] = array[j].charAt(0);
			}
			field.set(object, integs);
		}
	}

	private static void unmarshPrimitiveArrayChild(Field field, String[] stringArray, Object object)
			throws IllegalArgumentException, IllegalAccessException {
		switch (field.getType().getComponentType().getSimpleName()) {
		case "int":
			int[] array_ = new int[stringArray.length];

			for (int j = 0; j < stringArray.length; j++) {
				array_[j] = Integer.parseInt(stringArray[j].trim());
			}
			// setto la variabile

			field.set(object, array_);
			break;

		case "double":

			double[] array_d = new double[stringArray.length];

			for (int j = 0; j < stringArray.length; j++) {
				array_d[j] = Double.parseDouble(stringArray[j].trim());
			}

			// setto la variabile

			field.set(object, array_d);

			break;

		case "float":

			float[] arry_f = new float[stringArray.length];
			for (int j = 0; j < arry_f.length; j++) {
				arry_f[j] = Float.parseFloat(stringArray[j].trim());
			}

			field.set(object, arry_f);

			break;

		case "long":

			long[] array_l = new long[stringArray.length];
			for (int j = 0; j < array_l.length; j++) {
				array_l[j] = Long.parseLong(stringArray[j].trim());
			}

			field.set(object, array_l);

			break;

		case "boolean":
			boolean[] array_b = new boolean[stringArray.length];
			for (int j = 0; j < array_b.length; j++) {
				array_b[j] = Boolean.parseBoolean(stringArray[j].trim());
			}
			field.set(object, array_b);
			break;
		case "byte":
			byte[] buffer = new byte[stringArray.length];
			for (int j = 0; j < buffer.length; j++) {
				buffer[j] = Byte.parseByte(stringArray[j].trim());
			}

			field.set(object, buffer);

			break;

		case "char":// dubbio

			char[] charats = new char[stringArray.length];

			for (int j = 0; j < charats.length; j++) {
				charats[j] = stringArray[j].trim().charAt(0);
			}

			field.set(object, charats);

			break;
		}
	}

	// questo metodo può estrarre sia un oggetto stringa e sia un wrapper, però a
	// differenza
	// del suo simile (marsh) non opera su i primitivi
	private static void unmarshSimpleObjectChild(Field field, String valueField, Object object)
			throws IllegalArgumentException, IllegalAccessException {
		switch (field.getType().getSimpleName()) {
		case "String":
			// controllo se ci sono spazi nella stringa
			if (valueField.contains("@n")) {
				// sostituisco il simbolo jgo
				valueField = valueField.replaceAll("@n", "\n");
			}
			field.set(object, valueField);

			break;

		case "Integer":

			field.set(object, Integer.parseInt(valueField));

			break;

		case "Double":

			field.set(object, Double.parseDouble(valueField));

			break;

		case "Float":

			field.set(object, Float.parseFloat(valueField));

			break;

		case "Long":

			field.set(object, Long.parseLong(valueField));

			break;

		case "Boolean":

			field.set(object, Boolean.parseBoolean(valueField));

			break;

		case "Byte":

			field.set(object, Byte.parseByte(valueField));

			break;

		case "Character": // dubbio

			field.set(object, valueField);

			break;
		}
	}

	private static void unmarshPrimitiveType(Field field, Object object, String valueFieldPrimitive)
			throws NumberFormatException, IllegalArgumentException, IllegalAccessException {
		switch (field.getType().getSimpleName()) {
		case "int":
			// int value = Integer.parseInt(valueField);
			// field2.setInt(obj, value);
			field.setInt(object, Integer.parseInt(valueFieldPrimitive));

			break;

		case "double":

			field.setDouble(object, Double.parseDouble(valueFieldPrimitive));

			break;

		case "float":

			field.setFloat(object, Float.parseFloat(valueFieldPrimitive));

			break;

		case "long":

			field.setLong(object, Long.parseLong(valueFieldPrimitive));

			break;

		case "boolean":

			field.setBoolean(object, Boolean.parseBoolean(valueFieldPrimitive));

			break;

		case "byte":

			field.setByte(object, Byte.parseByte(valueFieldPrimitive));

			break;

		case "char":// dubbio
			field.setChar(object, valueFieldPrimitive.charAt(0));

			break;
		}
	}

	private static String getValue(String jon_src, String varName) {
		String value = null;
		// stampo il code
		String[] split = jon_src.split("£_");

		for (int i = 0; i < split.length; i++) {
			if (split[i].length() > 1 && split[i].length() > 2) {
				// a questo punto verifico se l'elemento
				// inizia con il nome della var
				if (split[i].startsWith(varName)) {
					// prendo dal primo spazio dopo il nome della var in poi, fino alla fine
					// dell'elemento split
					value = split[i].substring(split[i].indexOf(" "));

					value = value.trim();

					break;
				}
			}
		}

		return value;
	}

	private static <T> T unmarshObjectChild(String bufferText, Field field, Object parent)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// vabè in tanto mi individuo il punto in cui inizia l'oggetto nel file
		String jgo_id = "£_" + field.getName();
		Object obj = null;
		if (bufferText.contains(jgo_id)) {
			int indexIdField = bufferText.indexOf(jgo_id);
			int lastIndex = bufferText.lastIndexOf("£" + field.getName() + "}");
			// ora prendo la stringa
			// che parte dall'indice dell'id
			// fino alla fine, per ora
			int calculate = field.getName().length() + 1 + 1;
			String code = bufferText.substring(indexIdField, lastIndex + calculate);

			String packet = field.getType().getName();

			Class<?> classField = Class.forName(packet);

			boolean constr_is_void = false;

			Constructor<?>[] cons = classField.getDeclaredConstructors();

			for (int i = 0; i < cons.length; i++) {
				cons[i].setAccessible(true);
				if (cons[i].getParameterCount() == 0) {
					constr_is_void = true;
					break;
				}
			}
			if (constr_is_void) {
				// creo l'oggetto

				obj = classField.newInstance();

				// okok scompongo la classe in fields

				Field[] fields = classField.getDeclaredFields();

				for (Field field2 : fields) {

					field2.setAccessible(true);

					String idCurrentField = "£_" + field2.getName();
					// verifico di che tipo di campo si tratta

					if (field2.getType().isPrimitive()) {

						// qui mi faccio uno split per andate a capo

						// tanto il codice rimane a disposizione
						String[] split = code.split("\n");
						String rowField = null;
						for (int i = 0; i < split.length; i++) {
							if (split[i].startsWith(idCurrentField)) {

								// ho trovato la riga che mi interessa
								rowField = split[i];
								break;
							}
						}
						if (rowField != null) {

							// prendo il valore

							// prendiamo dal primo spazio in poi
							int indexFirstSpace = rowField.indexOf(" ");
							String valueField = rowField.substring(indexFirstSpace).trim();

							// faccio il controllo approfondito del tipo

							unmarshPrimitiveType(field2, obj, valueField);
						}
					} else if (field2.getType().isArray()) {

						String[] split = code.split("\n");
						String rowField = null;
						for (int i = 0; i < split.length; i++) {
							if (split[i].startsWith(idCurrentField)) {

								// ho trovato la riga che mi interessa
								rowField = split[i];
								break;
							}
						}
						if (rowField != null) {
							unmarshArray(field2, rowField, field2.getName(), obj);
						}

					} else {

						if (isWrapper(field2)) {
							String[] split = code.split("\n");
							String row = null;
							for (int i = 0; i < split.length; i++) {
								if (split[i].startsWith(idCurrentField)) {

									// ho trovato la riga che mi interessa

									row = split[i];
									break;

								}
							}
							if (row != null) {
								int indexFirstSpace = row.indexOf(" ");
								String valueField = row.substring(indexFirstSpace).trim();
								unmarshSimpleObjectChild(field2, valueField, obj);
							}
						} else {
							/**
							 * @author Martire91 : continuare da qui ...
							 */
							// qui controllo se si tratta di una stringa oppure di un oggetto

							if (field2.getType().getSimpleName().equals("String")) {

								String[] split = code.split("\n");
								String row = null;
								for (int i = 0; i < split.length; i++) {
									if (split[i].startsWith(idCurrentField)) {
										// ho trovato la riga che mi interessa
										row = split[i];
										break;
									}
								}
								if (row != null) {

									int indexFirstSpace = row.indexOf(" ");
									String valueField = row.substring(indexFirstSpace).trim();
									// imposto il valore del field
									field2.set(obj, valueField);
								}
							} else {
								// da valutare
								// provo a richiamare il metodo per vedere cosa succede
								unmarshObjectChild(bufferText, field2, obj);
							}
						}
					}
				}
				// in qualsiasi di questi casi
				// almeno per il momento setto
				// la variabile dell'oggetto parent
				field.set(parent, obj);
			} else {
				try {
					throw new JONUnmarshallingException();
				} catch (JONUnmarshallingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return (T) obj;
	}

	/**
	 * This method returns true if the field is wrapper type
	 * 
	 * @param field
	 *            the field
	 * @return the flag
	 */
	private static boolean isWrapper(Field field) {
		if (field.getType().getSimpleName().equals("Integer") || field.getType().getSimpleName().equals("Double")
				|| field.getType().getSimpleName().equals("Float") || field.getType().getSimpleName().equals("Long")
				|| field.getType().getSimpleName().equals("Boolean")
				|| field.getType().getSimpleName().equals("Character")
				|| field.getType().getSimpleName().equals("Byte")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method returns true if the class is wrapper type
	 * 
	 * @param class_
	 *            the class
	 * @return the flag
	 */
	private static boolean isWrapper(Class<?> class_) {
		if (class_.getSimpleName().equals("Integer") || class_.getSimpleName().equals("Double")
				|| class_.getSimpleName().equals("Float") || class_.getSimpleName().equals("Long")
				|| class_.getSimpleName().equals("Boolean") || class_.getSimpleName().equals("Character")
				|| class_.getSimpleName().equals("Byte")) {
			return true;
		} else {
			return false;
		}
	}

}
