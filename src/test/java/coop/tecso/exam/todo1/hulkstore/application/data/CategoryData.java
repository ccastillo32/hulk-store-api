package coop.tecso.exam.todo1.hulkstore.application.data;

import java.util.Arrays;
import java.util.List;

import coop.tecso.exam.todo1.hulkstore.domain.model.Category;

public class CategoryData {
	
	private CategoryData() {
	}
	
	public static List<Category> allCategories() {
		return Arrays.asList( tShirts(), toys() );
	}
	
	public static Category tShirts() {
		return Category.of("f3559fb4-ea4a-4c86-b889-e0838a0719c5", "T-shirts");
	}
	
	public static Category toys() {
		return Category.of("7e7937a6-e008-42f9-b619-d15a41108b8a", "Toys");
	}
	
	public static Category comics() {
		return Category.of("8c534d71-e4a3-490c-9d6d-8c15ed2569ad", "Comics");
	}

}
