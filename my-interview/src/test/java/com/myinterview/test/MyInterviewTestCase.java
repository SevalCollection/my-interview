package com.myinterview.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyInterviewTestCase {
	final int MAX = 1000;
	List<Integer> firstList;
	Set<Integer> firstDuplicateSet;

	List<Integer> secondList;
	Set<Integer> secondDuplicateSet;
	Set<Integer> intersectionSet;

	@Before
	public void setup() {
		firstList = new ArrayList<Integer>();
		firstDuplicateSet = new HashSet<Integer>();

		secondList = new ArrayList<Integer>();
		secondDuplicateSet = new HashSet<Integer>();
		intersectionSet = new HashSet<Integer>();
	}

	@Test
	public void testUsingUserDefinedDuplicateAlgorithm() {
		// generate the first random number list
		createRandomList(firstList);
		Assert.assertNotNull(firstList);
		Assert.assertEquals(firstList.size(), MAX);

		System.out.println();
		System.out.println();
		System.out.println("**** Using UserDefined function*******");
		System.out.println("FIRST LIST");
		if (CollectionUtils.isNotEmpty(firstList)) {
			System.out.println("Random Generated Numbers of first List are:");
			firstList.stream().forEach( number -> {
					System.out.print(number + ",");
				});
				System.out.println();
			}
		
		List<Integer> firstDuplicateList = retrieveDuplicateList(firstList);
		// print the FirstList's Duplicate Values
		if (CollectionUtils.isNotEmpty(firstDuplicateList)) {
		System.out.println("First List Contain " + firstDuplicateList.size() + " Duplicates and values are:");
			firstDuplicateList.stream().forEach( number -> {
				System.out.print(number + ",");
			});
			System.out.println();
		}

		//generate the second random number list
		createRandomList(secondList);
		Assert.assertNotNull(secondList);
		Assert.assertEquals(secondList.size(), MAX);
		
		System.out.println();
		System.out.println("SECOND LIST");
		if (CollectionUtils.isNotEmpty(firstList)) {
			System.out.println("Random Generated Numbers of second List are:");
			secondList.stream().forEach( number -> {
					System.out.print(number + ",");
				});
				System.out.println();
			}
		System.out.println();
		// populate an array with intersection elements of the two lists
		List<Integer> intersectionList = retrieveIntersectionValues(firstList, secondList);
		if (CollectionUtils.isNotEmpty(intersectionList)) {
		System.out
				.println("The two lists have " + intersectionList.size() + " non-repetitive Numbers and the values are:");
		intersectionList.stream().forEach( number -> {
			System.out.print(number + ",");
		});
		System.out.println();
		} else {
			System.out
			.println("The two lists have no non-repetitive Numbers");
		}
	}

	@Test
	public void testUsingJavaDataStructureFeatures() {
		// generate the first list
		createRandomListAndItsDuplicates(firstList, firstDuplicateSet);
		Assert.assertNotNull(firstList);
		Assert.assertEquals(firstList.size(), MAX);
		System.out.println("**** Using Java Data Structures*******");
		System.out.println("FIRST LIST");
		printToConsole(firstList, firstDuplicateSet);
		
		
		// generate the second list
		createRandomListAndItsDuplicates(secondList, secondDuplicateSet);
		Assert.assertNotNull(secondList);
		Assert.assertEquals(secondList.size(), MAX);
		System.out.println("SECOND LIST");
		printToConsole(secondList, secondDuplicateSet);

		// retain all intersection list by retaining all the values between two lists
		firstList.retainAll(secondList);
		Assert.assertNotNull(secondList);
		// List out the size of the Intersection of the values
		System.out.println("Intersection of Both List : TotalSize of Intersection of Numbers=" + firstList.size());

		// add the retained list to Set to provide sorting
		intersectionSet.addAll(firstList);
		intersectionSet.stream().forEach( number -> {
			System.out.print(number + ",");
		});
		System.out.println();
	}

	private void printToConsole(List<Integer> numberList, Set<Integer> duplicateSet) {
		// print the FirstList and the duplicateSet
		System.out.println("JavaDataStructure - RandomNumbers : TotalSize=" + numberList.size());
		numberList.stream().forEach( number -> {
			System.out.print(number + ",");
		});
		System.out.println();
		System.out.println("JavaDataStructure -  Duplicate Numbers : TotalSize=" + duplicateSet.size());
		duplicateSet.stream().forEach( number -> {
			System.out.print(number + ",");
		});
		System.out.println();
		System.out.println();
	}

	/**
	 * User Defined Duplicate search Algorithm
	 * 
	 * @param firstList
	 *            1st random generated List of 1000 numbers
	 * @param secondList
	 *            2nd random generated List of 1000 numbers
	 * @return
	 */
	private List<Integer> retrieveIntersectionValues(List<Integer> firstList, List<Integer> secondList) {
		// Sort the Collections in their Natural Order before finding duplicates
		System.out.println("Distinct values in the First List:");
		List<Integer>distinctFirstList = getDistinctList(firstList);
		System.out.println("Distinct values in the Second List:");
		List<Integer>distinctSecondList = getDistinctList(secondList);
		System.out.println();
		List<Integer> nonRepetitiveDeDupList = null;
		
		if (CollectionUtils.isNotEmpty(distinctFirstList) && CollectionUtils.isNotEmpty(distinctSecondList)) {
			int secondListCntrlIndex = 0;
			for (int firstListIndex = 0; firstListIndex < distinctFirstList.size(); firstListIndex++) {
				for (int secondListIndex = secondListCntrlIndex; secondListIndex < distinctSecondList
						.size(); secondListIndex++) {
					if (firstList.get(firstListIndex).intValue() == secondList.get(secondListIndex).intValue()) {
						// if found in the second list  secondListIndex+1 is the next starting point during next iteration
						secondListCntrlIndex = secondListIndex + 1;
						// update the Duplicate List and continue to next firtstList Value.
						if (null == nonRepetitiveDeDupList) {
							nonRepetitiveDeDupList = new ArrayList<Integer>();
						}
						nonRepetitiveDeDupList.add(distinctFirstList.get(firstListIndex));
						break;
					}
				}
			}
		}
		return nonRepetitiveDeDupList;
	}

	/**
	 * retrieve the distinct elements in the given array
	 * 
	 * @param listWithDuplicates
	 * @return distinctList
	 */
	private List<Integer> getDistinctList(List<Integer> listWithDuplicates) {
		List<Integer> distinctList = null;
		if (CollectionUtils.isNotEmpty(listWithDuplicates)) {
			Collections.sort(listWithDuplicates);
			for (int i = 0; i < listWithDuplicates.size(); i++) {
				//keep moving the index until we reach a distinct value , check until end of list if needed
				while(i< listWithDuplicates.size()-1 && listWithDuplicates.get(i).intValue() == listWithDuplicates.get(i+1)) {
					i++;
				}
				//once traversed to an value other than the initial index value that had been selected
				if(null == distinctList) {
					distinctList = new ArrayList<Integer>();
				}
				distinctList.add(listWithDuplicates.get(i));
			}
			
			if(CollectionUtils.isNotEmpty(distinctList)) {
				distinctList.stream().forEach( number -> {
					System.out.print(number + ",");
				});
				System.out.println();
			}
		}
		return distinctList;
	}

	/**
	 * Create Random Numbers
	 * 
	 * @param randomNumbers
	 */
	private void createRandomList(List<Integer> randomNumbers) {
		int[] arraysList = new Random().ints(MAX, 1, MAX + 1).toArray();
		for (int num : arraysList) {
			randomNumbers.add(num);
		}
	}

	/**
	 * Retrieve Duplicate from List of Numbers
	 * 
	 * @param randomNumbers
	 */
	private List<Integer> retrieveDuplicateList(List<Integer> randomNumbers) {
		List<Integer> duplicateList = null;
		if (CollectionUtils.isNotEmpty(randomNumbers)) {
			// Natural Ordering Sort
			Collections.sort(randomNumbers);
			for (int i = 0; i < randomNumbers.size() - 1; i++) {
				if (randomNumbers.get(i).intValue() == randomNumbers.get(i + 1).intValue()) {
					if (null == duplicateList) {
						duplicateList = new ArrayList<Integer>();
					}
					if (!duplicateList.contains(randomNumbers.get(i))) {
						duplicateList.add(randomNumbers.get(i));
					}
				}
			}
			// sort by natural order
			if (null != duplicateList) {
				Collections.sort(duplicateList);
			}
		}
		return duplicateList;
	}

	/**
	 * Create the random and a set to contain non-duplicate values of the random
	 * Numbers
	 * 
	 * @param randomNumbers
	 * @param duplicateSet
	 */
	private void createRandomListAndItsDuplicates(List<Integer> randomNumbers, Set<Integer> duplicateSet) {
		Assert.assertNotNull(randomNumbers);
		Assert.assertNotNull(duplicateSet);
		int[] arraysList = new Random().ints(MAX, 1, MAX + 1).toArray();
		for (int num : arraysList) {
			duplicateSet.add(num);
			randomNumbers.add(num);
		}
	}

	@After
	public void tearDown() {
		firstList = null;
		firstDuplicateSet = null;
		secondList = null;
		secondDuplicateSet = null;
		intersectionSet = null;
	}
}
