package xjtu.bean;

import javax.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String filename;

    @Column(nullable = true)
    private Boolean hasPicture;

    @Column(nullable = true)
    private Boolean hasXml;

    public Boolean getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }

    public Boolean getHasXml() {
        return hasXml;
    }

    public void setHasXml(Boolean hasXml) {
        this.hasXml = hasXml;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Picture() {
    }
}
