package com.rtctel.net.dhcpd;

import java.util.ArrayList;
import java.util.List;

public class DHCPLeaseStats {
	
	public static void main(String [] args) {
		String inFile = System.getProperty("lease.file"); 
		System.out.println("Reading " + inFile); 
		List<String> leases = 
				(ArrayList<String>)(new DHCPLeaseParser(inFile)).allActiveLeases(); 
		
		if (!leases.isEmpty()) {
			System.out.println("Success somehow");
		}
	}
}
