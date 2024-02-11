package com.sigma.dao;

import java.util.Arrays;

import com.sigma.beans.Bookings;

public class BookingDAOArrayImplementation implements BookingDAO {

	public static Bookings[] book = new Bookings[0];
	public int addTicket(Bookings booking) {
		book=Arrays.copyOf(book,book.length+1);
		book[book.length-1]=booking;
		return 0;
	}

}
