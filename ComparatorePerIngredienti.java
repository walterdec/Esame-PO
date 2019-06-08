package polichef;

import java.util.*;

public class ComparatorePerIngredienti implements Comparator<Piatto>{

	@Override
	public int compare(Piatto p1, Piatto p2) {
		return p1.getIngredienti().size()-p2.getIngredienti().size();
	}

}
