package com.aa.act.interview.org;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Organization {

	private Position root;
	
	Organization org ;
	public Organization() {
		root = createOrganization();
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	int i = 1;
	Map<String, Name> hm = new HashMap<>();
	public Optional<Position> hire(Name person, String title) {
		//your code here
		if(title != null) {
			Employee employee = new Employee(i++,person);
			Position position = new Position(title,employee);
			//position filled newly
			if(!hm.containsKey(title)) {
				position.addDirectReport(position);
				hm.put(title, person);
				System.out.println(Optional.of(position));
			}
			else{
				// already filled, but new hire replace position
				position.removePosition(position);
				hm.replace(title, person);
				System.out.println(Optional.of(position));
			}
			return Optional.of(position);
		}
		//show data when you remove above return Optional.of(position);
        // shows the people hired and replaced with new hire if already filled positions respective to title
		for (Name Person : hm.values()) {
			System.out.println("Person: " + Person);
			}
		System.out.println();
		 
		return Optional.empty();
	}
	

	@Override
	public String toString() {
		return printOrganization(root, "");
	}
	
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
