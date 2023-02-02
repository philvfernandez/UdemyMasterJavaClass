package com.philf;

import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {
    private final String name;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    //private final Set<BodyType> bodyTypes;
    private final BodyTypes bodyType;



    public enum BodyTypes {
        STAR,
        PLANET,
        DWARF_PLANET,
        MOON,
        COMET,
        ASTEROID
    }



    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
        this.bodyType = bodyType;
    }


    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public boolean addSatellite(HeavenlyBody moon) {
        return this.satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }

    public BodyTypes getBodyType() {
        return bodyType;
    }

    /*
        Doc https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-
         */
    @Override
    public final boolean equals(Object obj) {

        //checks to see if it is compared to itself
        if(this == obj) {
            return true;
        }

//        System.out.println("obj.getClass() is " + obj.getClass());
//        System.out.println("this.getClass() is " + this.getClass());
        if(obj instanceof HeavenlyBody) {
            HeavenlyBody theObject = (HeavenlyBody) obj;
            if(this.name.equals(theObject.getName())) {
                return this.bodyType == theObject.getBodyType();
            }
        }

        return false;
    }

    @Override
    public final int hashCode() {
        //System.out.println("hascode called");
        return this.name.hashCode() + 57 + this.bodyType.hashCode();
    }

    @Override
    public String toString() {
        return this.name + ": " + this.bodyType + "," + this.orbitalPeriod;
    }
}
