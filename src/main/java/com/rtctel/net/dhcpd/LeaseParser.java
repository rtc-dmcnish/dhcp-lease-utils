package com.rtctel.net.dhcpd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LeaseParser {
	
	private Scanner scanner = null; 
	private Map<String,String> leases; 
	private DHCPSubnet subnet; 
	
	public LeaseParser(String leaseFile, DHCPSubnet subnet) {
		this.subnet = subnet; 
		if (leaseFile != null && !leaseFile.isEmpty()) {
			try {
				this.scanner = new Scanner(new File(leaseFile));
				parse(); 
				sort();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			
	public LeaseParser(String leaseFile, String network, int mask) {
		this(leaseFile, new DHCPSubnet(network, mask)); 
	}
	
	public Map<String,String> getLeases() {
		return leases; 
	}
	
	public double getFreeLeases() {
		return this.subnet.getFreeLeases().doubleValue(); 
	}

	public double getActiveLeases() {
		return this.subnet.getActiveLeases().doubleValue(); 
	}
	
	
	private void parse() {
		this.leases = new HashMap<String,String>(); 
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
					this.leases.put(lease, bState.substring(0, bState.indexOf(";")));
				}
				scanner.nextLine(); 
			}
			scanner.nextLine(); 
		}
	}
	
	private void sort() {
		for (String ip: this.leases.keySet()) {
			if (subnet.hasMemberIp(ip)) {
				System.out.println(this.leases.get(ip));
				if (this.leases.get(ip).equals("active")) {
					this.subnet.incrActive();
				} else {
					this.subnet.incrFree(); 
				}
			}
		}
	}
	
	
}
