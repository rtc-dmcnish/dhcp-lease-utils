package com.rtctel.net.dhcpd;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo; 

public class DHCPSubnet {
	
	private Integer freeLeases = 0;
	private Integer activeLeases = 0; 
	
	private SubnetUtils su; 
	
	private SubnetInfo subnet; 
	
	public DHCPSubnet(String network, String prefixLen) {
		this.su = new SubnetUtils(network + "/" + prefixLen); 
		this.subnet = su.getInfo(); 
	}
	
	public Integer getFreeLeases() {
		return this.freeLeases;
	}
	
	public boolean hasMemberIp(String ip) {
		return this.subnet.isInRange(ip); 
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
	
	@Override
	public String toString() {
		return "DHCPSubnet [freeLeases=" + freeLeases + ", activeLeases=" + activeLeases + ", getFreeLeases()="
				+ getFreeLeases() + ", getActiveLeases()=" + getActiveLeases() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
