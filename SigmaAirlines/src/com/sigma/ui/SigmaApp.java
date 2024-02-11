package com.sigma.ui;

import java.util.*;

import com.sigma.beans.*;
import com.sigma.service.*;
import com.sigma.constants.*;

public class SigmaApp
{	
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) 

	{
		
		initialLoading();
	}

	public static void initialLoading()
	{	
		boolean loop=true;
		while(loop)
		{
			System.out.println("**************************************************");
			System.out.println("    WELCOME TO SIGMA AIRLINE MANAGEMENT SYSTEM    ");
			System.out.println("**************************************************");
			System.out.println("1.Admin Sign in");
			System.out.println("2.User Sign in");
			System.out.println("3.User Register");
			System.out.println("4.Exit");
			int choice=sc.nextInt();
			
			switch(choice)
			{
				case 1:
				{
					displayAdminFeatures();
					break;
				}
		
				case 2:
				{
					displayUserFeatures();
					break;
				}
		
				case 3:
				{
					registerUser();		
					break;
				}
				case 4 :
				{
					loop=false;
					break;
				}
				default:
					System.out.println("Invalid Choice. Please Try Again");
			}
		}
	}
	public static void displayAdminFeatures()
	{
		
		GenericConstants constants=new GenericConstants();	
		System.out.println("Enter Admin user name:");
		String name=sc.next();
		System.out.println("Enter Admin password:");
		String pwd=sc.next();
		if(name.equals(constants.getAdminUserName()) && pwd.equals(constants.getAdminPassword()))
		{
			
			boolean loop=true;
			while(loop)
			{
				System.out.println("--------------------------------------------");
				System.out.println("               WELCOME ADMIN                ");
				System.out.println("--------------------------------------------");
				System.out.println("1.Carrier");
				System.out.println("2.Flight");
				System.out.println("3.Conveyor Mappping");
				System.out.println("4.View/Resolve Complaint");
				System.out.println("5.Exit");
				
				int choice=sc.nextInt();
				
				switch(choice)
				{
					case 1:
					{
						displayCarrierOptions();
						break;
					}
					case 2:
					{
						displayFlightOptions();
						break;
					}
					case 3:
					{
						displayConveyorMapping();
						break;
					}
					
					case 4:
					{
						displayViewResolveComplaint();
						break;
					}
					case 5:
					{
						loop=false;
						break;
					}
					default:
					{
						System.out.println("Invalid Choice. Please try again");
					}
				}
			}
		}
		else
		{
			System.out.println("Please Enter Appropriate Admin Username and Password");
		}
		
	}
	public static void  displayUserFeatures()
	{
		ComplaintService complaintservice = new ComplaintService();
		BookingService bs = new BookingService();
		boolean loop=true;
		while(loop)
		{

			System.out.println("--------------------------------------------");
			System.out.println("               WELCOME USER               ");
			System.out.println("--------------------------------------------");
			System.out.println("1.Search Flight Details");
			System.out.println("2.Book Flight Details");
			System.out.println("3.view Flight Details");
			System.out.println("4.Cancel Flight Details");
			System.out.println("5.Complaint Register");
			System.out.println("6.Exit");
			int choice=sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					//System.out.println("**************************************************");
					  System.out.println("Enter Your Origin,Destination,Date");
    				  String searchOrigin=sc.nextLine();
    				  String searchDestination=sc.nextLine();
    				  String searchDate=sc.nextLine();
    				  FlightService fs = new FlightService();
    				  fs.searchFlightdetails(searchOrigin, searchDestination, searchDate);
				      break;
				}
				case 2:
				{
					System.out.println("Enter flight id:");
					int flightid=sc.nextInt();
					System.out.println("Enter the ticket counts:");
					int tc = sc.nextInt();
					System.out.println("Enter the Seat Category:");
					String scategory=sc.nextLine();
					sc.nextLine();
					System.out.println("Enter the Customer Category:");
					String ccategory=sc.nextLine();
					System.out.println("Enter travel date:");
					String tdate = sc.nextLine();
					System.out.println("Enter userid:");
					int userid=sc.nextInt();
					Bookings booking = new Bookings(flightid,tc,scategory,ccategory,tdate,userid);
					
					int flag = bs.addTicket(booking);
					if(flag==0)
						System.out.println("booked the ticket");
					else
						System.out.println("Unable to book the ticket");
										
					break;
				}
				case 3:
				{
					System.out.println("Enter your Booking Id:");
					int id=sc.nextInt();
					Bookings ticket=bs.viewTicket(id);
					System.out.println("Booking details:");
					System.out.println("Booking Id:"+ticket.getBookingId());
					System.out.println("Flight Id:"+ticket.getFlightid());
					System.out.println("User Name:"+ticket.getUsername());
					System.out.println("No Of Seats:"+ticket.getTcount());
					System.out.println("Seat Category:"+ticket.getScategory());
					System.out.println("Date Of Travel:"+ticket.getTdate());
					System.out.println("Booking Status:"+ticket.getBookingstatus());
					System.out.println("Booking Amount:"+ticket.getBookingamt());
					
					
					break;
				}
				case 4:
				{
					System.out.println("Enter your Booking Id to cancel ticket:");
					int id=sc.nextInt();
					int flag=bs.cancelTicket(id);
					if(flag==0)
					{
						System.out.println("Your Booking is successfully cancelled");
						
					}
					else
					{
						System.out.println("Invalid Booking Id");
					}
					break;
				}
				case 5:
				{
					boolean loop_5=true;
					while(loop_5)
					{
						System.out.println("1.Complaint Register");
						System.out.println("2.View Complaints");
						System.out.println("3.Reopen Complaints");			
						System.out.println("4.Exit");
						
						int ch1=sc.nextInt();
						
						switch(ch1)
						{
							case 1:
							{
								System.out.println("1.Register complaint");
								System.out.println("Enter complaint details: ");
								String status="Open";
								sc.nextLine();
								String reason=sc.nextLine();
								Complaint co=new Complaint(status,reason);
								
								int flag=complaintservice.RegisterComplaint(co);
								if(flag==0)
								{
									System.out.println("Information saved successfully in the system");
								}
								else
								{
									System.out.println("Couldn't add");
								}
								break;
							}
							case 2:
							{
								System.out.println("2.View complaint");
								complaintservice.ViewComplaint();
								break;
							}
							case 3:
							{
								System.out.println("3.Reopen Complaints");
								complaintservice.ViewComplaint();
								System.out.println("Select Complaint_Id:");
								int compid=sc.nextInt();
								Complaint complaint=complaintservice.resolvecomplaint(compid);
								sc.nextLine();
								complaint.setStatus("Open");
								complaintservice.ViewComplaint();
								System.out.println("Ticket Updated Successful");
								break;
							}
							case 4:
							{
								loop_5=false;
								break;
							}
							default:
								System.out.println("Invalid Choice. Please try again");
						}
					}
					break;
				}
				case 6:
				{
					loop=false;
					System.out.println("Thank you");
					break;
				}
				default:
					System.out.println("Invalid Choice. Please try again");
			}
		}		
	}
	public static void  registerUser()
	{
		UserService userservice = new UserService();
		System.out.println("Enter User Name :");
		String username=sc.nextLine();
		sc.nextLine();
		System.out.println("Enter User Password :");
		String pswd=sc.nextLine();
		System.out.println("Enter user category:");
		String category = sc.nextLine();
		System.out.println("Enter user mobile number:");
		long phnno = sc.nextLong();sc.nextLine();
		System.out.println("Enter email address:");
	    String email= sc.nextLine();
	    System.out.println("Enter Address1:");
	    String add1 = sc.nextLine();
	    System.out.println("Enter Address2:");
	    String add2 = sc.nextLine();
	    System.out.println("Enter city:");
	    String city = sc.nextLine();
	    System.out.println("Enter state:");
	    String state = sc.nextLine();
	    System.out.println("Enter country:");
	    String country = sc.nextLine();
	    System.out.println("Enter zipcode:");
	    int zipcode = sc.nextInt();sc.nextLine();
	    System.out.println("Enter DateOfBirth:");
	    String dob = sc.nextLine();
	    User us = new User(username, pswd, category, phnno, email, add1 , add2, city, state, country, zipcode, dob);
	    
	    int flag= userservice.addUser(us);
	    if(flag==0)
		{
			System.out.println("User Information saved successfully in the system");
			
		}
		else
		{
			System.out.println("Couldn't add User Information in the system");
		}
	}
	public static void displayCarrierOptions()
	{
		CarrierService carrierservice = new CarrierService();
		boolean loop=true;
		while(loop)
		{
			System.out.println("-------------------------------------------------");
			System.out.println("Welcome Admin here you can change Carrier Details");
			System.out.println("-------------------------------------------------");
			System.out.println("1.Add Carrier Details");
			System.out.println("2.View Carrier Details");
			System.out.println("3.Remove Carrier Details");
			System.out.println("4.Edit Carrier Details");
			System.out.println("5.Exit");
			
			int ch=sc.nextInt();
			
			switch(ch)
			{
				case 1:
				{
					
					System.out.println("Enter Carrier Name:");
					String carrierName=sc.next();
					System.out.println("Enter discount Percentage ThirtyDays AdvanceBooking:");
					int discountPercentageThirtyDaysAdvanceBooking=sc.nextInt();
					System.out.println("Enter discount Percentage Sixty Days AdvanceBooking:");
					int discountPercentageSixtyDaysAdvanceBooking=sc.nextInt();
					System.out.println("Enter discount Percentage NinetyDays AdvanceBooking:");
					int discountPercentageNinetyDaysAdvanceBooking=sc.nextInt();
					System.out.println("Enter bulkBooking Discount:");
					int bulkBookingDiscount=sc.nextInt();
					System.out.println("Enter refund Percentage For Ticket Cancellation 2Days Before Travel Date:");
					int refundPercentageForTicketCancellation2DaysBeforeTravelDate=sc.nextInt();
					System.out.println("Enter refund Percentage For Ticket Cancellation 10 Days Before Travel Date:");
					int refundPercentageForTicketCancellation10DaysBeforeTravelDate=sc.nextInt();
					System.out.println("Enter refund Percentage For Ticket Cancellation 20 Days Before Travel Date:");
					int refundPercentageForTicketCancellation20DaysBeforeTravelDate=sc.nextInt();
					System.out.println("Enter silver User Discount:");
					int silverUserDiscount=sc.nextInt();
					System.out.println("Enter Gold User Discount:");
					int goldUserDiscount=sc.nextInt();
					System.out.println("Enter platinum User Discount:");
					int platinumUserDiscount=sc.nextInt();
					Carrier c=new Carrier(carrierName,
							discountPercentageThirtyDaysAdvanceBooking,
							discountPercentageSixtyDaysAdvanceBooking,
							discountPercentageNinetyDaysAdvanceBooking,
							bulkBookingDiscount,
							refundPercentageForTicketCancellation2DaysBeforeTravelDate,
							refundPercentageForTicketCancellation10DaysBeforeTravelDate,
							refundPercentageForTicketCancellation20DaysBeforeTravelDate,
							silverUserDiscount,
							goldUserDiscount,
							platinumUserDiscount);
					
					int flag=carrierservice.addCarrier(c);
					if(flag==0)
					{
						System.out.println("Carrier Information saved successfully in the system");
					}
					else
					{
						System.out.println("Couldn't add Carrier Information in the system");
					}
					break;
				}
				case 2:
				{
					carrierservice.fetchAllCarriers();
					break;
					
				}
				case 3:
				{
					System.out.println("Enter Carrier Id to remove:");
					int carrierid=sc.nextInt();
					int flag=carrierservice.deleteCarrier(carrierid);
					if(flag==0)
					{
						System.out.println("Carrier Information successfully removed from the system");
						carrierservice.fetchAllCarriers();
					}
					else
					{
						System.out.println("Invalid carrier Id");
					}
					
					break;
				}
				case 4:
				{
					System.out.println("Enter Carrier Id to Edit:");
					int carrierid=sc.nextInt();
					Carrier carrier=carrierservice.fetchCarriersById(carrierid);
	
					if(carrier==null)
					{
						System.out.println("Invalid carrier id");
					}
					else
					{
						System.out.println("Carrier Id : "+carrier.getCarrierId()); 
						System.out.println("Carrier Name : "+carrier.getCarrierName());
						System.out.println("discountPercentageThirtyDaysAdvanceBooking : "+carrier.getDiscountPercentageThirtyDaysAdvanceBooking());
						System.out.println("DiscountPercentageSixtyDaysAdvanceBooking : "+carrier.getDiscountPercentageSixtyDaysAdvanceBooking());
						System.out.println("DiscountPercentageNinetyDaysAdvanceBooking : "+carrier.getDiscountPercentageNinetyDaysAdvanceBooking());
						System.out.println("BulkBookingDiscount : "+carrier.getBulkBookingDiscount());
						System.out.println("RefundPercentageForTicketCancellation2DaysBeforeTravelDate : "+carrier.getRefundPercentageForTicketCancellation2DaysBeforeTravelDate());
						System.out.println("RefundPercentageForTicketCancellation10DaysBeforeTravelDate : "+carrier.getRefundPercentageForTicketCancellation10DaysBeforeTravelDate());
						System.out.println("RefundPercentageForTicketCancellation20DaysBeforeTravelDate : "+carrier.getRefundPercentageForTicketCancellation20DaysBeforeTravelDate());
						System.out.println("SilverUserDiscount : "+carrier.getSilverUserDiscount());
						System.out.println("GoldUserDiscount : "+carrier.getGoldUserDiscount());
						System.out.println("PlatinumUserDiscount : "+carrier.getPlatinumUserDiscount());
						
						boolean loop_5=true;
						while(loop_5)
						{
							System.out.println("\n\n");
							System.out.println("Enter your choice to edit Carrier id "+carrier.getCarrierId());
							System.out.println("1.Edit discount Percentage Thirty Days Advance Booking");
							System.out.println("2.Edit Discount Percentage Sixty Days Advance Booking");
							System.out.println("3.Edit Discount Percentage NinetyDays Advance Booking");
							System.out.println("4.Edit Bulk Booking Discount");
							System.out.println("5.Edit Refund Percentage For Ticket Cancellation 2 Days Before Travel Date");
							System.out.println("6.Edit Refund Percentage For Ticket Cancellation 10 Days Before Travel Date");
							System.out.println("7.Edit Refund Percentage For Ticket Cancellation 20 Days Before Travel Date");
							System.out.println("8.Edit Silver User Discount");
							System.out.println("9.Edit Gold User Discount");
							System.out.println("10.Edit Platinum User Discount");
							System.out.println("11.Exit");
							
							int c=sc.nextInt();
							switch(c)
							{
								case 1:
								{
									System.out.println("Enter discount Percentage Thirty Days Advance Booking");
									int value=sc.nextInt();
									carrier.setDiscountPercentageThirtyDaysAdvanceBooking(value);
									System.out.println("Successfully edited");
									break;
								}
								case 2:
								{
									System.out.println("Enter Discount Percentage Sixty Days Advance Booking");
									int value=sc.nextInt();
									carrier.setDiscountPercentageSixtyDaysAdvanceBooking(value);
									System.out.println("Successfully edited");
									break;
								}
								case 3:
								{
									System.out.println("Enter Discount Percentage NinetyDays Advance Booking");
									int value=sc.nextInt();
									carrier.setDiscountPercentageNinetyDaysAdvanceBooking(value);
									System.out.println("Successfully edited");
									break;
								}
								case 4:
								{
									System.out.println("Enter Bulk Booking Discount");
									int value=sc.nextInt();
									carrier.setBulkBookingDiscount(value);
									System.out.println("Successfully edited"); 
									break;
								}
								case 5:
								{
									System.out.println("Enter Refund Percentage For Ticket Cancellation 2 Days Before Travel Date");
									int value=sc.nextInt();
									carrier.setRefundPercentageForTicketCancellation2DaysBeforeTravelDate(value);
									System.out.println("Successfully edited");
									break;
								}
								case 6:
								{
									System.out.println("Enter Refund Percentage For Ticket Cancellation 10 Days Before Travel Date");
									int value=sc.nextInt();
									carrier.setRefundPercentageForTicketCancellation10DaysBeforeTravelDate(value);
									System.out.println("Successfully edited");
									break;
								}
								case 7:
								{
									System.out.println("Enter Refund Percentage For Ticket Cancellation 20 Days Before Travel Date");
									int value=sc.nextInt();
									carrier.setRefundPercentageForTicketCancellation20DaysBeforeTravelDate(value);
									System.out.println("Successfully edited");
									break;
								}
								case 8:
								{
									System.out.println("Enter Silver User Discount");
									int value=sc.nextInt();
									carrier.setSilverUserDiscount(value);
									System.out.println("Successfully edited");
									break;
								}
								case 9:
								{
									System.out.println("Enter Gold User Discount");
									int value=sc.nextInt();
									carrier.setGoldUserDiscount(value);
									System.out.println("Successfully edited");
									break;
								}
								case 10:
								{
									System.out.println("Enter Platinum User Discount");
									int value=sc.nextInt();
									carrier.setPlatinumUserDiscount(value);
									System.out.println("Successfully edited");
									break;
								}
								case 11:
								{
									loop_5=false;
									break;
								}
								default:
									System.out.println("Invalid Choice. Please try again");
							}
						}
					
					System.out.println("Successfully updated all your details in "+carrier.getCarrierId());
					System.out.println("**************************************************");
					}
					break;
				}
				case 5:
				{
					loop=false;
					System.out.println("Thank you");
					break;
				}
				default:
					System.out.println("Invalid Choice. Please try again");
			}
		}
		
	
	}
	public static void displayFlightOptions()
	{
		FlightService flightservice = new FlightService();
		CarrierService carrierservice = new CarrierService();
		boolean loop=true;
		while(loop)
		{
			System.out.println("-------------------------------------------------");
			System.out.println("Welcome Admin here you can change Flight Details");
			System.out.println("-------------------------------------------------");
			System.out.println("1.Add Flight Details");
			System.out.println("2.View Flight Details");
			System.out.println("3.Remove Flight Details");
			System.out.println("4.Edit Flight Details");
			System.out.println("5.Exit");

			int choice=sc.nextInt();

			switch(choice)
			{
				case 1:
				{
					System.out.println("**************************************************");
					System.out.println("These are the carries you have\n");
					carrierservice.fetchAllCarriers();
				
					System.out.println("Enter Carrier Id:");
					int carrier_id=sc.nextInt();
					System.out.println("Enter Origin of the flight:");
					String origin=sc.next();
					System.out.println("Enter Destination of the flight:");
					String destination=sc.next();
					System.out.println("Enter Airfare:");
					int airfare=sc.nextInt();
					System.out.println("Enter Seat Capacity of Business Class:");
					int business_seat=sc.nextInt();
					System.out.println("Enter Seat Capacity of Economy Class:");
					int economy_seat=sc.nextInt();
					System.out.println("Enter Seat Capacity of Executive Class:");
					int executive_seat=sc.nextInt();
					Flight f=new Flight(carrier_id,origin,destination,airfare,business_seat,economy_seat,executive_seat);
					
					int flag=flightservice.addFlight(f);
					if(flag==0)
					{
						System.out.println("Flight Information saved successfully in the system");
					}
					else
					{
						System.out.println("Couldn't add Flight Information in the system");
					}
					
					break;
				}
				case 2:
				{
					Flight[] flightdetails=flightservice.fetchAllFlights();
					
					System.out.println("*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*");
					System.out.println("                                          FLIGHT DETAILS                                     ");
					System.out.println("*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*");
					System.out.println("FLIGHTID     CARRIERID     ORIGIN     DESTINATION     AIRFARE     BUSINESS CLASS SEAT     ECONOMY CLASS SEAT     EXECUTIVE CLASS SEAT	 DATE");
					
					for(Flight flight:flightdetails)
					{
						if(flight.getFlightId()!=0)
						{
							System.out.println("   "+flight.getFlightId()+"            "+flight.getCarrierId()+"        "+flight.getOrigin()+"       "+flight.getDestination()+"         "+flight.getAirfare()+"            "+flight.getBusinessSeat()+"                "+flight.getEconomySeat()+"                "+flight.getExecutiveSeat());
							
						}
					}
					break;
				}
				case 3:
				{
					System.out.println("Enter Flight Id to remove:");
					int flightid=sc.nextInt();
					flightservice.deleteFlight(flightid);
					flightservice.fetchAllFlights();
					break;
				}
				case 4:
				{
					System.out.println("Enter Flight Id to Edit:");
					int flightid=sc.nextInt();
					Flight flight=flightservice.fetchFlightsbyId(flightid);
	
					if(flight==null)
					{
						System.out.println("Invalid flight id");
					}
					else
					{
						System.out.println("Flight Id : "+flight.getFlightId());
						System.out.println("Carrier Id : "+flight.getCarrierId()); 
						System.out.println("Origin : "+flight.getOrigin());
						System.out.println("Destination : "+flight.getDestination());
						System.out.println("Airfare : "+flight.getAirfare());
						System.out.println("SeatCapacityBusinessClass : "+flight.getBusinessSeat());
						System.out.println("SeatCapacityEconomyClass : "+flight.getEconomySeat());
						System.out.println("SeatCapacityExecutiveClass : "+flight.getExecutiveSeat());
					
						boolean loop_5=true;
						while(loop_5)
						{
							System.out.println("\n\n");
							System.out.println("Enter your choice to edit in flight "+flight.getFlightId());
							System.out.println("1.Edit Airfare");
							System.out.println("2.Edit Seat Capacity of Business Class");
							System.out.println("3.Edit Seat Capacity of Economy Class");
							System.out.println("4.Edit Seat Capacity of Executive Class");
							System.out.println("5.Exit");
							
							int c=sc.nextInt();
							switch(c)
							{
								case 1:
								{
									System.out.println("Enter airfare you want to edit:");
									int value=sc.nextInt();
									flight.setAirfare(value);
									System.out.println("Successfully edited");
									break;
								}
								case 2:
								{
									System.out.println("Enter seat capacity of business class you want to edit:");
									int value=sc.nextInt();
									flight.setBusinessSeat(value);
									System.out.println("Successfully edited");
									break;
								}
								case 3:
								{
									System.out.println("Enter seat capacity of economy class you want to edit:");
									int value=sc.nextInt();
									flight.setEconomySeat(value);
									System.out.println("Successfully edited");
									break;
								}
								case 4:
								{
									System.out.println("Enter seat capacity of executive class you want to edit:");
									int value=sc.nextInt();
									flight.setExecutiveSeat(value);
									System.out.println("Successfully edited"); 
									break;
								}
								case 5:
								{
									loop_5=false;
									break;
								}
								default:
									System.out.println("Invalid Choice. Please try again");
							}
						}
					
					System.out.println("Successfully updated all your details in "+flight.getFlightId());
					System.out.println("**************************************************");
					}
					break;
				}
				case 5:
				{
					loop=false;
					System.out.println("Thank you");
					break;
				}
				default:
					System.out.println("Invalid Choice. Please try again");
			}
		}
		
	
	}
	public static void displayConveyorMapping()
	{
		ConveyorService conveyorservice=new ConveyorService();
		boolean loop=true;
		while(loop)
		{

			System.out.println("--------------------------------------------");
			System.out.println("               WELCOME ADMIN              ");
			System.out.println("--------------------------------------------");
			System.out.println("1.Map Conveyor");
			System.out.println("2.View Conveyor Details");
			System.out.println("3.View Conveyor Details by FlightID");

			System.out.println("4.Exit");
			int choice=sc.nextInt();

			switch(choice)
			{
			case 1:
			{

				System.out.println("Enter Conveyor Id:");
				int conveyor_id=sc.nextInt();
				System.out.println("Enter Flight Id:");
				int flightid=sc.nextInt();
				System.out.println("Enter Date");
				String date=sc.next();
				Conveyor c=new Conveyor(conveyor_id,flightid,date);

				int flagg=conveyorservice.mapConveyor(c);
				if(flagg==0)
				{
					System.out.println("Conveyor Information saved successfully in the system");
				}
				else
				{
					System.out.println("Couldn't map Conveyor Information in the system");
				}

				break;									
			}
			case 2:
			{
				conveyorservice.fetchAllConveyors();
				break;
			}
			
			case 3:
			{
				System.out.println("Enter flight Id to Search:");
				int flightid=sc.nextInt();
				Conveyor conveyor=conveyorservice.fetchConveyorsById(flightid);
				if(conveyor==null)
				{
					System.out.println("Invalid flight id");

				}
				else
				{
					System.out.println("Conveyor Id : "+conveyor.getConveyorId());
					System.out.println("Flight Id : "+conveyor.getFlightId());
					System.out.println("Date : "+conveyor.getDate());
				}
				break;
			}
			case 4:
			{
				loop=false;
				System.out.println("Thank you");
				break;
			}
			default:
				System.out.println("Invalid Choice. Please try again");
			}
			
		}
	
	}
	public static void displayViewResolveComplaint()
	{
		ComplaintService complaintservice = new ComplaintService();
		System.out.println("View/Resolve Complaint");
		complaintservice.ViewComplaint();
		System.out.println("Enter Complaint_Id:");
		int compid=sc.nextInt();
		Complaint complaint=complaintservice.resolvecomplaint(compid);
		sc.nextLine();
		System.out.println("Status:");
		String value=sc.nextLine();
		complaint.setStatus(value);
		System.out.println("Changed succesfully");
	}
}

