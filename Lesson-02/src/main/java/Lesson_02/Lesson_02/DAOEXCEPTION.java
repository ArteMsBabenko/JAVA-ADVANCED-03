package Lesson_02.Lesson_02;

@SuppressWarnings("serial")
public class DAOEXCEPTION extends Exception {
	
	
		public DAOEXCEPTION() {
			super();
		}
		
		public DAOEXCEPTION(String message) {
			super(message);
		}
		
		public DAOEXCEPTION(String message, Throwable cause) {
			super(message, cause);
		}
}
