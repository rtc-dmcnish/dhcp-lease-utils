package com.rtctel.net.dhcpd;

import com.rtctel.net.util.Subnet; 

public class DHCPSubnet extends Subnet {
	
	int freeLeases = 0; 
	int activeLeases = 0; 
	
	public DHCPSubnet(String network, int prefixLen) {
		super(network,prefixLen); 
	}
	
	public int getFreeLeases() {
		return this.freeLeases;
	}
	
	public int getActiveLeases() {
		return this.activeLeases; 
	}
	
	public void incrFree() {
		this.freeLeases = this.freeLeases + 1; 
	}
	
	public void incrActive() {
		this.activeLeases = this.activeLeases + 1; 
	}
	
}
