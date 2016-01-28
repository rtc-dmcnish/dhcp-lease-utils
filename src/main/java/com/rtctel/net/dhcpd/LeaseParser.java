package com.rtctel.net.dhcpd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LeaseParser {
	
	private Scanner scanner = null; 
	private Map<String,String> leases; 
	
	public LeaseParser(String leaseFile) {
		if (leaseFile != null && !leaseFile.isEmpty()) {
			try {
				this.scanner = new Scanner(new File(leaseFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Map<String,String> allActiveLeases() {
		leases = new HashMap<String,String>(); 
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
				if (!lease.isEmpty()) {
					leases.put(lease, bState.substring(0, bState.indexOf(";")));
				}
				scanner.nextLine(); 
			}
			scanner.nextLine(); 
		}
		return leases;
	}
}
