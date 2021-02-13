package com.ujwal;

/**
 * 
 * @author Ujwal's XPS
 * an interface can have only 1 default method
 * Default methods enable the enhancement of existing interfaces without breaking compatibility.
 * In this sense, they build the foundation for new features like stream() 
 */

public class DefaultFunction {
	public static void main(String[] args) {
		new printerFromA().print();
		new printerFromAB().print();
	}
}


class printerFromA implements printA {}
class printerFromAB implements printA, printB {

	@Override
	public void print() {
		System.out.println("cannot implement multiple interfaces with homonymous methods without overriding it.");
		printA.super.print();
	}}

interface printA {
	default void print() {
		System.out.println("This is from interface A");
	}
}

interface printB {
	default void print() {
		System.out.println("This is from interface B");
	}
}