package com.modris.Model;

import java.time.LocalDate;
import java.time.Year;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertService {
	private TrackerDao dao;
	private int birthYear;

	public InsertService(TrackerDao dao2) {
		this.birthYear = 2000;
		this.dao = dao2;
	}

	public void insert(int age, String name, int month, int day) {
		Integer id = java.sql.Types.NULL;
		LocalDate birth = LocalDate.of(getBirthYear(age, month, day), month, day);
		LocalDate next_bday = LocalDate.of(1000, 1, 1);
		Tracker track = new Tracker(id, name, age, birth, 0, next_bday);
		dao.insertTracker(track);
	}

	public int getBirthYear(int age, int month, int day) {
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		LocalDate tempBirthDate = LocalDate.of(currentYear, month, day);
		if (currentDate.compareTo(tempBirthDate) >= 0) {
			// if currDate is equal or bigger than birthday date in this year then its this
			// year
			return birthYear = currentDate.getYear() - age;
		}
		// if we will have bday in this year then birthyear is:
		birthYear = currentDate.getYear() - age - 1;
		return birthYear;
	}

	public int setValidDay(int month, int day) {
		// if month day is bigger than max month day.
		if (month == 2 && day >= 29) {
			return 28;
		}
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
			return 30;
		}
		return day;

	}
}
