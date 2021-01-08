package coop.tecso.exam.todo1.hulkstore.application.data;

import java.util.Arrays;
import java.util.List;

import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;

public class FranchiseData {
	
	private FranchiseData() {
	}
	
	public static List<Franchise> allFranchises() {
		
		Franchise marvel = Franchise.of("9878cdc6-d089-405f-9f4d-5d53dcc79726", "Marvel");
		Franchise dc = Franchise.of("f3d0a258-ab7a-4a2f-864a-f3acff3450e3", "DC");
		Franchise others = Franchise.of("4e5d622d-895b-429e-b564-63ed7ebc7820", "Others");
		
		return Arrays.asList(marvel, dc, others);
	}

}
