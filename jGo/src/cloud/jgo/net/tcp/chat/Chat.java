package cloud.jgo.net.tcp.chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chat {

	
	private static List<Mexg>messages = new ArrayList<>();
	
	
	public void add(Mexg mex){
		
		this.messages.add(mex);
	}
	
	public int getChatSize(){
		
		return this.messages.size();
		
		
	}
	
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Iterator<Mexg>iterator = this.messages.iterator();
		while(iterator.hasNext()){
			buffer.append(iterator.next()+"\n");
		}
		return buffer.toString() ;
	}
	
	
	
}
