package cloud.jgo.net.tcp.http.jor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE})
/**
 * @author Martire91<br>
 *	This annotation allows the definition of the URL of the object
 */
public @interface JOR{
	/**
	 * This method gets the url pattern
	 * @return the url pattern
	 */
  public String url_Pattern();
  /**
   * This method gets the response type
   * @return the response type
   */
  public cloud.jgo.net.tcp.http.jor.ResponseType responseType()default ResponseType.HTML_TYPE;
  /**
   * This method gets the field id
   * @return the field id
   */
  public String field_id(); // da spiegare a cosa serve, deve essere un field stringa
}
