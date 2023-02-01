package com.philf;

public class Labrador extends Dog {

    public Labrador(String name) {
        super(name);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(this == obj) {
//            return true;
//        }
//
//        if(obj instanceof Labrador) {
//            String objName = ((Labrador) obj).getName();
//            return this.getName().equals(objName);
//        }
//
//        //Not the same
//        return false;
//    }

}
