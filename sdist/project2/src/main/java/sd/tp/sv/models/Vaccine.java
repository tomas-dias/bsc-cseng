package sd.tp.sv.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vaccine")
public class Vaccine
{
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Vaccine(){}

    public Vaccine(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return String.format("Vaccine[name='%s']", name);
    }
}
