package sd.tp.sv.models;

import javax.persistence.*;

@Entity
@Table(name = "vaccinated")
public class Vaccinated
{
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "date")
    private String date;

    @Column(name = "vaccine")
    private String vaccine;

    public Vaccinated(){}

    public Vaccinated(Long id, String email, String date, String vaccine)
    {
        this.id = id;
        this.email = email;
        this.date = date;
        this.vaccine = vaccine;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getVaccine()
    {
        return vaccine;
    }

    public void setVaccine(String vaccine)
    {
        this.vaccine = vaccine;
    }

    @Override
    public String toString()
    {
        return String.format("Vaccinated[id=%d, email='%s', date='%s', vaccine='%s']",
                id, email, date, vaccine);
    }
}