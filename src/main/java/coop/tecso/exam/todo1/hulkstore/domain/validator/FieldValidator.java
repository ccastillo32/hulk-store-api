package coop.tecso.exam.todo1.hulkstore.domain.validator;

public class FieldValidator {
	
	private FieldValidator() {
	}
	
	public static void notNull(Object object, String fieldName) {
		if(object == null) {
			throw new InvalidFieldException(String.format("'%s cannot be null'", fieldName));
		}
	}
	
	public static void notEmpty(String value, String fieldName) {
		
		notNull(value, fieldName);
		
		if(value.trim().isEmpty()) {
			throw new InvalidFieldException(String.format("'%s' cannot be empty", fieldName));
		}
		
	}
	
	public static void notZeroOrBelow(Integer value, String fieldName) {
		
		notNull(value, fieldName);
		
		if(value.intValue() < 1) {
			throw new InvalidFieldException(String.format("'%s' cannot be zero or negative", fieldName));
		}
		
	}

}
