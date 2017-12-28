package com.devopsbuddy.backend.persistence.domains.backend;

import com.devopsbuddy.backend.persistence.converters.LocalDateTimeAttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Jnwanya on
 * Thu, 28 Dec, 2017.
 */
@Entity
public class PasswordResetToken implements Serializable{
    /** The serialversionUID for a serializabled class **/
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetToken.class);

    private final int DEFAULT_TOKEN_LENGTH_IN_MINUTES = 120;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiry_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime expiryDate;

    public PasswordResetToken(){

    }

    public PasswordResetToken(String token, User user, LocalDateTime localDateTime, int expirationInMinutes){
       if(token == null || user == null || localDateTime == null){
           throw new IllegalArgumentException("token, user and creation time cannot be null");
       }
       if( expirationInMinutes == 0){
           LOG.warn("The token expiration time is zero. Assign expiration date {}", DEFAULT_TOKEN_LENGTH_IN_MINUTES);
           expirationInMinutes = DEFAULT_TOKEN_LENGTH_IN_MINUTES;
       }
       this.token = token;
       this.user = user;
       this.expiryDate = localDateTime.plusMinutes(expirationInMinutes);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordResetToken that = (PasswordResetToken) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
