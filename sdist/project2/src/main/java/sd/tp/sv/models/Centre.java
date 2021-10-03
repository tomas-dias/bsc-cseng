package sd.tp.sv.models;

import javax.persistence.*;
import java.util.List;

@Entity
@IdClass(CentreId.class)
@Table(name = "centre")
public class Centre
{
    @Id
    @Column(name = "centre_name")
    private String name;

    @Id
    @Column(name = "date")
    private String date;

    @Column(name = "total_vaccines")
    private int totalVaccines;

    @Column(name = "availability")
    private boolean availability;

    @OneToMany(mappedBy = "centre")
    private List<Appointment> appointments;

    public Centre(){}

    public Centre(String name, int totalVaccines, String date, boolean availability)
    {
        this.name = name;
        this.totalVaccines = totalVaccines;
        this.date = date;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getTotalVaccines()
    {
        return totalVaccines;
    }

    public void setTotalVaccines(int totalVaccines)
    {
        this.totalVaccines = totalVaccines;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public boolean getAvailability()
    {
        return availability;
    }

    public void setAvailability(boolean availability)
    {
        this.availability = availability;
    }

    public List<Appointment> getAppointments()
    {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments)
    {
        this.appointments = appointments;
    }

    @Override
    public String toString()
    {
        return String.format("Centre[name='%s', totalVaccines=%d, date='%s', availability=%b]",
                name, totalVaccines, date, availability);
    }
}