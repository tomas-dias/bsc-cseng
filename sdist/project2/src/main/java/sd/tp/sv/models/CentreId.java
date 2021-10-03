package sd.tp.sv.models;

import java.io.Serializable;

public class CentreId implements Serializable
{
    private String name;
    private String date;

    public CentreId(){}

    public CentreId(String name, String date)
    {
        this.name = name;
        this.date = date;
    }
}
