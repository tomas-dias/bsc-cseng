package sd.tp.sv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notification
{
    @Id
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    @Column(name = "message")
    private String message;

    public Notification(){}

    public Notification(Long id, User user, String message)
    {
        this.id = id;
        this.user = user;
        this.message = message;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return String.format("Notification[id=%d, user='%s', message='%s']",
                id, user.toString(), message);
    }
}