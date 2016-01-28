package com.rtctel.net.dhcpd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LeaseParser {
	
	private Scanner scanner = null; 
	private Set<String> activeLeases; 
	
	public LeaseParser(String leaseFile) {
		try {
			this.scanner = new Scanner(new File(leaseFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	public Set<String> allActiveLeases() {
		activeLeases = new HashSet<String>(); 
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
				if (bState.equals("active;") && !lease.isEmpty() ) {
					activeLeases.add(lease);
				}
				scanner.nextLine(); 
			}
			scanner.nextLine(); 
		}
		return activeLeases;
	}
}
