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
		if(person == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		Optional<Employee> newEmployee = Optional.of(new Employee(i++, person));
		if(root.getTitle() == title) {
			root.setEmployee(newEmployee);
			return Optional.of(root);
			}
		else {
			for(Position l1: root.getDirectReports()) {
				if(l1.getTitle() != title) {
					for(Position l2: l1.getDirectReports()) {
						if(l2.getTitle() != title) {
							for(Position l3: l2.getDirectReports()) {
								if(l3.getTitle() == title) {
									l3.setEmployee(newEmployee);
								}
							}
						}
						else {
							l2.setEmployee(newEmployee);
							return Optional.of(l2);
						}
					}
				}
				else {
					l1.setEmployee(newEmployee);
					return Optional.of(l1);
				}
			}
		}
		 
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
