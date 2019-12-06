package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author GHabtamu
 */
@Entity
@Table(name = "user")
public class User extends BaseModel{
    private Integer userId;
    private String uuid;
    private String firstName;
    private String lastName;
    private String grandFatherName;
    private String dateOfBirth;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private Boolean changePasswordRequired;
    private Boolean isLocked;
    private Boolean isEnabled;
    private String gender;
    private Integer DepartmentId;

    @Id
    @Column(name = "UserId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "UUID", nullable = false, length = 50)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "FirstName", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "GrandFatherName", nullable = true, length = 50)
    public String getGrandFatherName() {
        return grandFatherName;
    }

    public void setGrandFatherName(String grandFatherName) {
        this.grandFatherName = grandFatherName;
    }

    @Basic
    @Column(name = "DateOfBirth", nullable = false)
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    @Transient
    public Integer getId() {
        return getUserId();
    }

    @Override
    public void setId(Integer id) {
          setUserId(id);
    }

    @Basic
    @Column(name = "UserName", nullable = false, length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 300)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PhoneNumber", nullable = true, length = 15)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "ChangePasswordRequired", nullable = false)
    public Boolean getChangePasswordRequired() {
        return changePasswordRequired;
    }

    public void setChangePasswordRequired(Boolean changePasswordRequired) {
        this.changePasswordRequired = changePasswordRequired;
    }

    @Basic
    @Column(name = "IsLocked", nullable = false)
    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    @Basic
    @Column(name = "IsEnabled", nullable = false)
    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Basic
    @Column(name = "Gender", nullable = true, length = 7)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    @Basic
    @Column(name = "DepartmentId", nullable = false)
    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer DepartmentId) {
        this.DepartmentId = DepartmentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(uuid, user.uuid) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(grandFatherName, user.grandFatherName) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(createdDate, user.createdDate) &&
                Objects.equals(createdBy, user.createdBy) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(changePasswordRequired, user.changePasswordRequired) &&
                Objects.equals(isLocked, user.isLocked) &&
                Objects.equals(isEnabled, user.isEnabled) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(DepartmentId, user.DepartmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, uuid, firstName, lastName, grandFatherName, dateOfBirth, createdDate, createdBy, userName, password, email, phoneNumber, changePasswordRequired, isLocked, isEnabled, gender, DepartmentId);
    }
}
