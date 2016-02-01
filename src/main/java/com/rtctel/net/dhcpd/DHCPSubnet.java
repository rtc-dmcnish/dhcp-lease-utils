package com.rtctel.net.dhcpd;

import com.rtctel.net.util.Subnet; 

public class DHCPSubnet extends Subnet {
	
	Integer freeLeases = 0; 
	Integer activeLeases = 0; 
	
	public DHCPSubnet(String network, int prefixLen) {
		super(network,prefixLen); 
	}
	
	public Integer getFreeLeases() {
		return this.freeLeases;
	}
	
	@Override
	public String toString() {
		return "DHCPSubnet [freeLeases=" + freeLeases + ", activeLeases=" + activeLeases + ", getFreeLeases()="
				+ getFreeLeases() + ", getActiveLeases()=" + getActiveLeases() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public Integer getActiveLeases() {
		return this.activeLeases; 
	}
	
	public void incrFree() {
		this.freeLeases = this.freeLeases + 1; 
	}
	
	public void incrActive() {
		this.activeLeases = this.activeLeases + 1; 
	}
	
}
