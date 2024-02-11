package com.sigma.service;

import com.sigma.beans.Bookings;
import com.sigma.beans.User;
import com.sigma.dao.*;
public class bookingService {
	BookingDAO bookingdao = new BookingDAOArrayImplementation();
	public int addTicket(Bookings booking)
	{
		bookingdao.addTicket(booking);
		return 0;
	}

}
