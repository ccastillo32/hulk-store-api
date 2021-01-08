package coop.tecso.exam.todo1.hulkstore.application.data;

import java.util.Arrays;
import java.util.List;

import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;

public class FranchiseData {
	
	private FranchiseData() {
	}
	
	public static List<Franchise> allFranchises() {
		return Arrays.asList(marvelComics(), dcComics(), others());
	}
	
	public static Franchise marvelComics() {
		return Franchise.of("9878cdc6-d089-405f-9f4d-5d53dcc79726", "Marvel");
	}
	
	public static Franchise dcComics() {
		return Franchise.of("f3d0a258-ab7a-4a2f-864a-f3acff3450e3", "DC");
	}
	
	public static Franchise others() {
		return Franchise.of("4e5d622d-895b-429e-b564-63ed7ebc7820", "Others");
	}

}
