package com.ccighgo.permissions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RscPerm {

	private static Set<RscPerm> perms;
	
	String perm;

	public RscPerm(String perm) {
		super();
		this.perm = perm;
	}

	public void addPerm (RscPerm perm) {
		perms.add(perm);
	}
	
	public RscPerm clone() {
		return new RscPerm(this.perm);
	}
		
	public static List<RscPerm> cloneList( List<RscPerm> aList) {
		List<RscPerm> retList = new ArrayList<RscPerm>();
		for (RscPerm p :aList) {
			retList.add( new RscPerm(p.getPerm()));
		}
		return retList;
	}
	
	public static List<RscPerm> cloneList( Set<RscPerm> aList) {
		List<RscPerm> retList = new ArrayList<RscPerm>();
		for (RscPerm p :aList) {
			retList.add( new RscPerm(p.getPerm()));
		}
		return retList;
	}
	
	public static Set<RscPerm> getPerms() {
		if (null==perms) {
			perms = new HashSet<RscPerm>();
		}
		return perms;
	}

	public static void setPerms(Set<RscPerm> perms) {
		RscPerm.perms = perms;
	}

	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	public boolean isaPerm(String _perm) {
		for (RscPerm r: perms) {
			if (r.perm.equalsIgnoreCase(_perm)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isaPerm(RscPerm _perm) {
		return perms.contains(_perm);
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perm == null) ? 0 : perm.hashCode());
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
		if (!(obj instanceof RscPerm)) {
			return false;
		}
		RscPerm other = (RscPerm) obj;
		if (perm == null) {
			if (other.perm != null) {
				return false;
			}
		} else if (!perm.equalsIgnoreCase(other.perm)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RscPerm [perm=" + perm + "]";
	}
	
	
}
