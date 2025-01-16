package uit.ssy.resumegenerator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 13:06
 * Project Name : ResumeGenerator
 **/

@Entity
@Table(name = "EDUCATION")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "COLLEGE")
    private String college;

    @Column(name = "QUALIFICATION")
    private String qualification;

    @Column(name = "MAJOR")
    private String major;

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
