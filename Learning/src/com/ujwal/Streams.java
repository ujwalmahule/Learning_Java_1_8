package com.ujwal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Streams {

	public static void main(String args[]) {
		long accumulatorExample = LongStream.range(0, 1000).reduce(0, (a, c) -> (a + c) * c);
		System.out.println(accumulatorExample);
		
		List<Person> persons = getPersons();
		
		//searial grouping
		Map<Integer, List<Person>> ageMap = persons.stream().collect(Collectors.groupingBy(Person::getAge));
		System.out.println("Group by age");
		System.out.println(ageMap);
		
		//parallel grouping
		ConcurrentMap<Integer, List<Person>> ageMapPar = persons.parallelStream().collect(Collectors.groupingByConcurrent(Person::getAge));
		System.out.println("Group by age, parallely");
		System.out.println(ageMapPar);
		
		//fibonacci series
		List<Long> fibonacci = new ArrayList<>();
		Stream.iterate(new long[] {0,1}, p -> new long[] {p[1], p[0]+p[1]})
	    .limit(10)
	    .forEach(p -> fibonacci.add(p[0]));
		System.out.println(fibonacci);
	
		//get all female
		List<Person> females = persons.stream()
		.filter(Person::isFemale)
		.collect(Collectors.toList());
		System.out.println("All females");
		System.out.println(females);
		
		//get all females < 20 yrs
		females = persons.parallelStream()
		.filter(Person::isFemale)
		.filter(p -> p.getAge() < 20)
		.collect(Collectors.toList());
		System.out.println("Females < 20yrs");
		System.out.println(females);
	}

	private static List<Person> getPersons() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Ujwal", 24, Sex.Male));
		persons.add(new Person("abc", 25, Sex.Female));
		persons.add(new Person("xyz", 19, Sex.Female));
		persons.add(new Person("a12", 19, Sex.Female));
		return persons;
	}
}


enum Sex {
	Male, Female;
}

class Person {
	String name;
	int age;
	Sex sex;
	
	public Person(String name, int age, Sex sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public boolean isFemale() {
		return this.sex == Sex.Female;
	}
	
	@Override
	public String toString() {
		return "{" + name + ", " + sex + ", " + age + "}";
	}
}