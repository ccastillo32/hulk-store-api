package coop.tecso.exam.todo1.hulkstore.application.data;

import java.util.Arrays;
import java.util.List;

import coop.tecso.exam.todo1.hulkstore.domain.model.Category;

public class CategoryData {
	
	private CategoryData() {
	}
	
	public static List<Category> allCategories() {
		Category tShirts = Category.of("f3559fb4-ea4a-4c86-b889-e0838a0719c5", "T-shirts");
		Category toys    = Category.of("7e7937a6-e008-42f9-b619-d15a41108b8a", "Toys");
		return Arrays.asList( tShirts, toys );
	}

}
