package cloud.jgo.jjdom.jquery;
// version 1.0.7
public final class jQuerySelector {
	private jQuerySelector(String selector,String description) {
		this.selector = selector ;
		this.description = description;
		if(!this.selector.startsWith(":"))
		this.selector = ":"+this.selector ;
	}
	private String description ;
	private String selector ;
	
	// mi creo le costanti 
	public final static jQuerySelector VISIBLE = new jQuerySelector("visible",null);
	public final static jQuerySelector HIDDEN = new jQuerySelector("hidden",null);
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.selector ;
	}
}
