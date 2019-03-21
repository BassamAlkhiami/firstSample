package com.mohammed.zwayen.ptotonbleapp.Model;

import java.sql.Struct;

public class User {
    private String name;
    private Options opt;

    public User(String nm)
    {
        name = nm;
        opt = new Options();
    }
    public String getName()
    {
        return name;
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

}
