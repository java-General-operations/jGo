package cloud.jgo.utils.command.annotations;

public class InvalidClassException extends Exception {
	public InvalidClassException(Class<?>clazz) {
		// TODO Auto-generated constructor stub
		super("\n - Invalid class > "+clazz.getName());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in " + getStackTrace()[0].getClassName() + "" + getMessage() + " : \n"
				+ "The \"Command\" annotation is not present #. " + getClass().getName();
	}
}
