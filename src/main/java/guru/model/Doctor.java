package guru.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    // Define possible status values as an enum
    public enum Status {
        AVAILABLE,
        NOT_AVAILABLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Lob
    @Column(name = "address")
    private String address;

    @Column(name = "department")
    private String department;

    @Column(name = "experience")
    private Integer experience;  // Allows null (better than primitive int)

    @Column(name = "status")
    @Enumerated(EnumType.STRING)  // Stores enum name as a string in DB
    private Status status;  // Uses the enum instead of String

    // Constructors
    public Doctor() {
        this.status = Status.NOT_AVAILABLE;  // Default status
    }

    public Doctor(String fullname, String specialization, String email, String phone, 
                 String address, String department, Integer experience, Status status) {
        this.fullname = fullname;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.department = department;
        this.experience = experience;
        this.status = status != null ? status : Status.NOT_AVAILABLE;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status != null ? status : Status.NOT_AVAILABLE;
    }

    @Override
    public String toString() {
        return "Doctor [id=" + id + ", fullname=" + fullname + ", specialization=" + specialization 
               + ", email=" + email + ", phone=" + phone + ", address=" + address 
               + ", department=" + department + ", experience=" + experience 
               + ", status=" + status + "]";
    }
}