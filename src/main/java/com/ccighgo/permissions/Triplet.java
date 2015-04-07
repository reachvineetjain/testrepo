package com.ccighgo.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Triplet {

	public ControlledRsc rsc; 
	public List<RscPerm> perms; 
	public RscUser user;
	
	public Triplet(ControlledRsc rsc, List<RscPerm> perms, RscUser user) {
		super();
		this.rsc = rsc;
		this.perms = perms;
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Triplet [rsc=" + rsc + ", perms=" + perms + ", user=" + user
				+ "]";
	}

	public void addPerm(RscPerm perm) {
		perms.add(perm);
	}
	
	public Triplet clone() {
		return new Triplet(this.rsc, RscPerm.cloneList(this.perms), this.user);
	}
	
	public static List<Triplet> cloneList( List<Triplet> aList) {
		List<Triplet> retList = new ArrayList<Triplet>();
		for (Triplet p :aList) {
			retList.add( new Triplet(p.rsc, p.perms, p.user));
		}
		return retList;
	}
	
	public static List<Triplet> cloneList( Set<Triplet> aList) {
		List<Triplet> retList = new ArrayList<Triplet>();
		for (Triplet p :aList) {
			retList.add( new Triplet(p.rsc, p.perms, p.user));
		}
		return retList;
	}
	

	public boolean isUser(RscUser _user) {
		return user.equals(_user);
	}
	
//	public boolean isResource(ControlledRsc _rsc) {
//		return rsc.getC equalsIgnoreCase(_rsc);
//	}
//		
	public boolean hasPerm(RscPerm _perm) {
		for (RscPerm r: perms) {
			if (r.equals(_perm)) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perms == null) ? 0 : perms.hashCode());
		result = prime * result + ((rsc == null) ? 0 : rsc.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (!(obj instanceof Triplet)) {
			return false;
		}
		Triplet other = (Triplet) obj;
		if (perms == null) {
			if (other.perms != null) {
				return false;
			}
		} else if (!perms.equals(other.perms)) {
			return false;
		}
		if (rsc == null) {
			if (other.rsc != null) {
				return false;
			}
		} else if (!rsc.equals(other.rsc)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}


	
	
}
