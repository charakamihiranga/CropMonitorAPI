package lk.ijse.springboot.CropMonitorAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class Staff implements SuperEntity{
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date joinedDate;
    private Date dob;
    private String addressLine01;
    private String addressLine02;
    private String addressLine03;
    private String addressLine04;
    private String addressLine05;
    private String contactNo;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "staff")
    private List<Field> fields;
    @ManyToMany
    @JoinTable(name = "monitoring_log_staff",
            joinColumns = @JoinColumn(name = "staffId", referencedColumnName = "staffId"),
            inverseJoinColumns = @JoinColumn(name = "logCode", referencedColumnName = "logCode"))
    private List<MonitoringLog> monitoringLogs;
    @OneToOne(mappedBy = "staff", optional = true)
    private Equipment equipment;
    @OneToMany(mappedBy = "staff")
    private List<Vehicle> vehicles;
}
