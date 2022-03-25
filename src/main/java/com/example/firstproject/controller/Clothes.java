package com.example.firstproject.controller;

import java.util.Vector;

public class Clothes {

	String combName;	// ���ո�
	int minTemp;		// �������
	int maxTemp;		// �ְ���
	String type;		// ����
	
	private static Vector <Clothes> typeA = new Vector <Clothes>();
	private static Vector <Clothes> typeB = new Vector <Clothes>();
	private static Vector <Clothes> typeC = new Vector <Clothes>();
	
	public Clothes(String comb, int min, int max, String type) {
		this.combName = comb;		this.minTemp = min;
		this.maxTemp = max;			this.type = type;
		
		if (this.type == "A")
			typeA.add(this);
		else if (this.type == "B")
			typeB.add(this);
		else
			typeC.add(this);

	}
	
	public String toString() {
		return "���ո�:" + this.combName + ", Ÿ��:" + this.type;
		
	}
	
	public static void main(String[] args) {
		
		Clothes c1 = new Clothes("������+û����", 17, 19, "A");
		Clothes c2 = new Clothes("�����+û����", 17, 19, "B");
		Clothes c3 = new Clothes("�β�����Ʈ+����", -100, 4, "C");
		Clothes c4 = new Clothes("�е�+����", -100, 4, "A");
		
		for(int i=0; i<typeA.size(); i++)
			System.out.println(typeA.get(i).toString());
		
		for(int i=0; i<typeB.size(); i++)
			System.out.println(typeB.get(i).toString());
		
		for(int i=0; i<typeC.size(); i++)
			System.out.println(typeC.get(i).toString());
		
	}
	
}
