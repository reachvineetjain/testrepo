package com.ccighgo.permissions;


public class RscUser {

	String userName;

	public RscUser(String perm) {
		super();
		this.userName = perm;
	}

	public RscUser clone() {
		return new RscUser(this.userName);
	}
		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RscUser)) {
			return false;
		}
		RscUser other = (RscUser) obj;
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equalsIgnoreCase(other.userName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RscUser [userName=" + userName + "]";
	}
	
	
}
