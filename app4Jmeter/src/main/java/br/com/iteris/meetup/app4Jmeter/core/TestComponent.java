package br.com.iteris.meetup.app4Jmeter.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {

	private List<String> itemsList = new ArrayList<String>();

	private Set<String> itemsSet = new HashSet<String>();

	public List<String> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<String> itemsList) {
		this.itemsList = itemsList;
	}

	public Set<String> getItemsSet() {
		return itemsSet;
	}

	public void setItemsSet(Set<String> itemsSet) {
		this.itemsSet = itemsSet;
	}

	public String findAtListByString(String value) {

		return itemsList.contains(value) ? value : null;
	}

	public String findAtSetByString(String value) {

		return itemsSet.contains(value) ? value : null;

	}

	public void addOnSet(String[] values) {

		itemsSet.addAll(Arrays.asList(values));
	}

	public void addOnList(String[] values) {

		itemsList.addAll(Arrays.asList(values));
	}

	public void removeItemAtList(String value) {
		itemsList.remove(value);
	}

	public void removeItemAtSet(String value) {
		itemsSet.remove(value);
	}

	public String updateItemAtList(String value, String newValue) {

		if (itemsList.contains(value) && itemsList.remove(value)) {
			itemsList.add(newValue);
			return newValue;
		}

		return null;
	}

	public String updateItemAtSet(String value, String newValue) {

		if (itemsSet.contains(value) && itemsSet.remove(value)) {
			itemsSet.add(newValue);
			return newValue;
		}
		return null;
	}
}
