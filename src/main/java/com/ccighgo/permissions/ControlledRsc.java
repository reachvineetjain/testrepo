package com.ccighgo.permissions;


public class ControlledRsc {

	String rsc;

	public ControlledRsc(String perm) {
		super();
		this.rsc = perm;
	}

	public ControlledRsc clone() {
		return new ControlledRsc(this.rsc);
	}
		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rsc == null) ? 0 : rsc.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ControlledRsc [rsc=" + rsc + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ControlledRsc)) {
			return false;
		}
		ControlledRsc other = (ControlledRsc) obj;
		if (rsc == null) {
			if (other.rsc != null) {
				return false;
			}
		} else if (!rsc.equalsIgnoreCase(other.rsc)) {
			return false;
		}
		return true;
	}
	
	
}
