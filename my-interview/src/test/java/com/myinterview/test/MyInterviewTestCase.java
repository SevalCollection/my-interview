package com.myinterview.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class MyInterviewTestCase {

	final int MAX = 1000;
	List<Integer> firtList = new ArrayList<Integer>();
	Set<Integer> firstDuplicateSet = new HashSet<Integer>();
	
	List<Integer> secondList = new ArrayList<Integer>();
	Set<Integer> secondDuplicateSet = new HashSet<Integer>();
	Set<Integer> intersectionSet = new HashSet<Integer>();
	@Test
	public void test() {
		//generate the two lists
		createRandomListAndItsDuplicates(firtList, firstDuplicateSet);
		createRandomListAndItsDuplicates(secondList, secondDuplicateSet);
		
		
		// retain all intersection list by retaining all the values between two lists
		firtList.retainAll(secondList);
		System.out.println("Intersection of Both List : TotalSize of Intersection of Numbers="+ firtList.size());
		
		// add the retained list to Set to provide sorting
		intersectionSet.addAll(firtList);
		intersectionSet.stream().forEach(System.out::println);
	}
	
	
	private void createRandomListAndItsDuplicates(List<Integer> randomNumbers, Set<Integer> duplicateSet) {
		int[] arraysList = new Random().ints(MAX,1, MAX+1).toArray();
		for(int num: arraysList) {
			duplicateSet.add(num);
			randomNumbers.add(num);
		}
		// print the FirstList and the duplicateSet
		System.out.println("RandomNumbers : TotalSize=" + randomNumbers.size());
		firtList.stream().forEach(System.out::println);
		System.out.println();
		System.out.println("List of Duplicate Numbers : TotalSize=" + duplicateSet.size());
		firstDuplicateSet.stream().forEach(System.out::println);
	}

}
