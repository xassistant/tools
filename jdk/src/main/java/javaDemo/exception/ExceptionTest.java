package javaDemo.exception;

class MyException extends Exception {

	public MyException(String me) {
		super(me);
	}

}

public class ExceptionTest {
	public static void main(String[] args) throws MyException {
		throw new MyException("asdasdasdads");

	}
}