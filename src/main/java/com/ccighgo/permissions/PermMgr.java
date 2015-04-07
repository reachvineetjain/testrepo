package com.ccighgo.permissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PermMgr {
	
	Map<ControlledRsc, Set<Triplet>> byRscMap = new HashMap<ControlledRsc, Set<Triplet>>();
	Map<RscUser, Set<Triplet>> byUserMap = new HashMap<RscUser, Set<Triplet>>();
	Map<String, Set<Triplet>> byRscUserMap = new HashMap<String, Set<Triplet>>();

	Set<Triplet> permSet = new HashSet<Triplet>();

	public static String rscUserKey(ControlledRsc _rsc, RscUser _user) {
		return _rsc + ":" + _user;
	}

	public void addPerm(ControlledRsc _rsc, RscPerm _perm, RscUser _user) {
		List<RscPerm> permList = new ArrayList<RscPerm>();
		permList.add(_perm);
		addTriplet(new Triplet( _rsc, permList, _user));
	}
	
	public void addTriplet( Triplet t) {
		Set<Triplet> rscTrips = byRscMap.get(t.rsc);
		if (null==rscTrips) rscTrips = new HashSet<Triplet>();
		rscTrips.add(t);
		byRscMap.put(t.rsc, rscTrips);
		
		Set<Triplet> userTrips = byUserMap.get(t.user);
		if (null==userTrips) userTrips = new HashSet<Triplet>();
		userTrips.add(t);
		byUserMap.put(t.user, userTrips);

		String rscUserKey = rscUserKey( t.rsc, t.user);
		Set<Triplet> resourceUserTrips = byRscUserMap.get(rscUserKey);
		if (null==resourceUserTrips) resourceUserTrips = new HashSet<Triplet>();
		resourceUserTrips.add(t);
		byRscUserMap.put(rscUserKey, resourceUserTrips);

		permSet.add(t);		
	}
	
	public boolean removePerm(ControlledRsc _rsc, RscPerm _perm, RscUser _user) {
		List<RscPerm> permList = new ArrayList<RscPerm>();
		permList.add(_perm);
		return removeTriplet(new Triplet( _rsc, permList, _user));
	}
	
	public boolean removeTriplet( Triplet t) {
		boolean wasInRsc=false;
		boolean wasInUser=false;
		boolean wasInRscUser=false;
		
		Set<Triplet> rscTrips = byRscMap.get(t.rsc);
		if (null != rscTrips) {
			if ( ! rscTrips.remove(t)) {
				System.err.println (t+ " missing from Rsc Map");
			} else {
				wasInRsc = true;
			}
		}

		rscTrips = byUserMap.get(t.user);
		if (null != rscTrips) {
			if ( ! rscTrips.remove(t)) {
				System.err.println (t+ " missing from User Map");
			} else {
				wasInUser = true;
			}
		}

		String rscUserKey = rscUserKey( t.rsc, t.user);
		rscTrips = byRscUserMap.get(rscUserKey);
		if (null != rscTrips) {
			if ( ! rscTrips.remove(t)) {
				System.err.println (t+ " missing from Rsc+User Map");
			} else {
				wasInRscUser = true;
			}
		}
		return (wasInRsc && wasInUser && wasInRscUser);		
	}
	
	public boolean hasPerm(ControlledRsc _resource, RscPerm _perm, RscUser _user ) {
		String rscUserKey = rscUserKey( _resource, _user);
		Set<Triplet> rscUserTrips = byRscUserMap.get(rscUserKey);
		if (null != rscUserTrips && rscUserTrips.size() >0) {
			for (Triplet t: rscUserTrips) {
				if (t.hasPerm(_perm)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public List<Triplet> permByResourceAndUser(ControlledRsc _resource, RscUser _user) {
		String rscUserKey = rscUserKey( _resource, _user);
		Set<Triplet> rscUserTrips = byRscUserMap.get(rscUserKey);
		if (null==rscUserTrips) rscUserTrips = new HashSet<Triplet>();
		List<Triplet> retList = new ArrayList<Triplet>();
		for (Triplet t: permSet) {
			retList.add(t.clone());
		}
		return retList;
	}
	
	public List<Triplet> permByUser(RscUser _user) {
		Set<Triplet> userTrips = byUserMap.get(_user);
		if (null==userTrips) userTrips = new HashSet<Triplet>();
		List<Triplet> retList = new ArrayList<Triplet>();
		for (Triplet t: permSet) {
			retList.add(t.clone());
		}
		return retList;
	}
	
	public List<Triplet> wherePermUsed(RscPerm perm) {
		List<Triplet> retList = new ArrayList<Triplet>();
		for (Triplet t: permSet) {
			if (t.equals(perm)) {
				retList.add(t.clone());
			}
		}
		return retList;
	}
	
	public synchronized List<Triplet> allManagedPerms() {
		List<Triplet> retList = new ArrayList<Triplet>();
		for (Triplet t: permSet) {
				retList.add(t.clone());
		}
		return retList;
	}
	
}
