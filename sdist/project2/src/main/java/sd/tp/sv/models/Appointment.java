package sd.tp.sv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "date"), @JoinColumn(name = "centre_name")})
    private Centre centre;

    public Appointment(){}

    public Appointment(String name, int age, User user, Centre centre)
    {
        this.name = name;
        this.age = age;
        this.user = user;
        this.centre = centre;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Centre getCentre()
    {
        return centre;
    }

    public void setCentre(Centre centre)
    {
        this.centre = centre;
    }

    @Override
    public String toString()
    {
        return String.format(
                "Appointment[id=%d, name='%s', age=%d, user='%s', centre='%s']",
                id, name, age, user.toString(), centre.toString());
    }
}