package com.hope.phones.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneList {
	Scanner sc = new Scanner(System.in);
	List<ShoppingList> li = new ArrayList<ShoppingList>(); 
	List<ShoppingList> cl = new ArrayList<ShoppingList>(); 
	public void getData() throws SQLException {
		System.out.println("product list");
		Connection con = DBConnection.getConnection();
		if(con != null) {
			Statement st =con.createStatement();
		    ResultSet rs = st.executeQuery("select * from phones");
			while (rs.next()) {
				ShoppingList sl = new ShoppingList();
				System.out.print(rs.getInt(1)+"\t");
				sl.setId(rs.getInt(1));
				System.out.print(rs.getString(2)+"\t");
				sl.setBrand(rs.getString(2));
				System.out.print(rs.getString(3)+"\t");
				sl.setModel(rs.getString(3));
				System.out.print(rs.getInt(4));
				sl.setPrice(rs.getInt(4));
				sl.setCount(1);
				System.out.println("");
				li.add(sl);
			}
			st.close();
			con.close();
			
		}
	}
	
	public void customerNeeds() {
		System.out.println("Do you Want to buy:(Y/N)");
		if(sc.next().equalsIgnoreCase("y"));{
			int x=0;
			while(true) {
				System.out.println("Enter mobile id:");
			    x =sc.nextInt();
				for(ShoppingList sl:li) {
					if(sl.getId()==x) {
						if(cl.contains(sl)) {
							sl.setCount(sl.getCount()+1);
						} else {
						cl.add(sl);
					}
					}
				}
				for(ShoppingList cs:cl) {
					System.out.println(cs.getBrand()+"\t"+cs.getModel()+"\t"+cs.getPrice()+"\t"+cs.getCount());
				}
				System.out.print("do you wish to continue:y/n");
				if(sc.next().equalsIgnoreCase("n")) {
					int sum=0;
					for(ShoppingList ss:cl) {
						sum=sum+(ss.getPrice()*ss.getCount());
					}
					System.out.println("Total Price to be paid:"+sum);
					System.out.println("Thank you");
					break;
				}	
			}
		}
	}
	
	public static void main(String args[]) {
		PhoneList ph = new PhoneList();
		try {
			ph.getData();
			ph.customerNeeds();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
