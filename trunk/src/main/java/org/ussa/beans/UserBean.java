package org.ussa.beans;

import java.io.Serializable;

public class UserBean implements Serializable{
    
    private String ussaId;
    private String ussaIdAlternates;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String zipCode;
    private String birthDate;
    private boolean primaryMemberOnAccount;

    public String getUssaId() {
	return ussaId;
    }

    public void setUssaId(String ussaId) {
	this.ussaId = ussaId;
    }

    public String getUssaIdAlternates() {
        return ussaIdAlternates;
    }

    public void setUssaIdAlternates(String ussaIdAlternates) {
        this.ussaIdAlternates = ussaIdAlternates;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getZipCode() {
	return zipCode;
    }

    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

    public String getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
    }

    public boolean isPrimaryMemberOnAccount() {
        return primaryMemberOnAccount;
    }

    public void setPrimaryMemberOnAccount(boolean primaryMemberOnAccount) {
        this.primaryMemberOnAccount = primaryMemberOnAccount;
    }

}
