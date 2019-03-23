package com.mohammed.zwayen.ptotonbleapp.Model;

import java.sql.Struct;

public class User {
    private String name;
    private Options opt;

    public User(String nm)
    {
        name = nm;
        opt = new Options();
        opt.L = true;
        opt.p1 = false;
        opt.p2 = false;
        opt.p3 = false;
        opt.p4 = false;
        opt.p5 = false;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String nm)
    {
        name = nm;
        opt.L = true;
        opt.p1 = false;
        opt.p2 = false;
        opt.p3 = false;
        opt.p4 = false;
        opt.p5 = false;
    }

    public void setLinearOptionValue(boolean val)
    {
        opt.L = val;
        opt.p1 = !val;
        opt.p2 = !val;
        opt.p3 = !val;
        opt.p4 = !val;
        opt.p5 = !val;
    }
    public void setP1OptionValue(boolean val)
    {
        opt.L = !val;
        opt.p1 = val;
        opt.p2 = !val;
        opt.p3 = !val;
        opt.p4 = !val;
        opt.p5 = !val;
    }
    public void setP2OptionValue(boolean val)
    {
        opt.L = !val;
        opt.p1 = !val;
        opt.p2 = val;
        opt.p3 = !val;
        opt.p4 = !val;
        opt.p5 = !val;
    }
    public void setP3OptionValue(boolean val)
    {
        opt.L = !val;
        opt.p1 = !val;
        opt.p2 = !val;
        opt.p3 = val;
        opt.p4 = !val;
        opt.p5 = !val;
    }
    public void setP4OptionValue(boolean val)
    {
        opt.L  = !val;
        opt.p1 = !val;
        opt.p2 = !val;
        opt.p3 = !val;
        opt.p4 = val;
        opt.p5 = !val;
    }
    public void setP5OptionValue(boolean val)
    {
        opt.L  = !val;
        opt.p1 = !val;
        opt.p2 = !val;
        opt.p3 = !val;
        opt.p4 = !val;
        opt.p5 = val;
    }

    private class Options
    {
        boolean L;
        boolean p1;
        boolean p2;
        boolean p3;
        boolean p4;
        boolean p5;
    };

    @Override
    public String toString() {

        String retVal = name+" , ";

        if (opt.L) retVal+="L , ";
        else retVal+="!L , ";

        if (opt.p1) retVal+="P1 , ";
        else retVal+="!P1 , ";

        if (opt.p2) retVal+="P2 , ";
        else retVal+="!P2 , ";

        if (opt.p3) retVal+="P3";
        else retVal+="!P3 , ";

        if (opt.p4) retVal+="P4";
        else retVal+="!P4 , ";

        if (opt.p5) retVal+="P5 , ";
        else retVal+="!P5";

        return retVal;
    }
}
