package test;
import cloud.jgo.SMTPHosts;
import cloud.jgo.j£;
import cloud.jgo.£Func;
import cloud.jgo.SMTPHosts.SMTPEntry;
import cloud.jgo.£;
public class TestGeneral {
public static void main(String[] args) {
	
	
	
	String[]arr = {"Marco","Giovanni","Salvatore"};
	
	
	£.each(arr,new £Func() {
		
		@Override
		public Object function(Object e) {
			String obj = (String) e ;
			System.out.println("Ecco:"+obj);
			return false ;
		}
	});
	
	
	
	
}
}
