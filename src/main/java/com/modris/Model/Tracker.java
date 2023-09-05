package com.modris.Model;

import java.time.LocalDate;

public class Tracker {

	private Integer id;
	private String name;
	private int age;
	private LocalDate birth_date;
	private int countdown_days;
	private LocalDate birthday_date;

	public Tracker(int id2, String name2, int age2, LocalDate birth_date2, int countdown_days2, LocalDate next_bday) {
		this.id = id2;
		this.name = name2;
		this.age = age2;
		this.birth_date = birth_date2;
		this.countdown_days = countdown_days2;
		this.birthday_date = next_bday;
	}

	public Tracker() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}

	public int getCountdown_days() {
		return countdown_days;
	}

	public void setCountdown_days(int countdown_days) {
		this.countdown_days = countdown_days;
	}

	public LocalDate getBirthday_date() {
		return birthday_date;
	}

	public void setBirthday_date(LocalDate birthday_date) {
		this.birthday_date = birthday_date;
	}

}
