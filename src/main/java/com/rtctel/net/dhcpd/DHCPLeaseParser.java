package com.rtctel.net.dhcpd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DHCPLeaseParser {
	
	private Scanner scanner = null; 
	private List<String> activeLeases; 
	
	public DHCPLeaseParser(String leaseFile) {
		try {
			this.scanner = new Scanner(new File(leaseFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	public List<String> allActiveLeases() {
		activeLeases = new ArrayList<String>(); 
		String lease = null; 
		while (scanner.hasNextLine()) {
			if (scanner.hasNext("lease")) {
				scanner.next(); 
				lease = scanner.next(); 
				scanner.nextLine(); 
			} else if (scanner.hasNext("binding")) {
				scanner.next();
				scanner.next();
				String bState = scanner.next(); 
				if (bState.equals("active;") && !lease.isEmpty()) {
					activeLeases.add(lease);
				}
				scanner.nextLine(); 
			}
			scanner.nextLine(); 
		}
		return activeLeases; 
	}
}
