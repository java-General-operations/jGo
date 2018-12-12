package cloud.jgo.net.tcp.http.jor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
/**
 * @author Martire91<br>
 *         This annotation allows the definition of the URL of the object
 */
public @interface JOR {
	/**
	 * This method gets the url pattern
	 * 
	 * @return the url pattern
	 */
	public String url_Pattern();

	/**
	 * This method gets the response type
	 * 
	 * @return the response type
	 */
	public cloud.jgo.net.tcp.http.jor.ResponseType responseType() default ResponseType.HTML;

	/**
	 * This method gets the field id
	 * 
	 * @return the field id
	 */
	public String field_id(); // da spiegare a cosa serve, deve essere un field stringa

	/**
	 * This method returns the URL JOR separator
	 * 
	 * @return the URL JOR separator
	 */
	public String separator() default "-";

	/**
	 * This method allows you to choose <br>
	 * whether or not to save the files created by JOR
	 * 
	 * @return the flag
	 */
	public boolean SaveFiles() default false;
}
