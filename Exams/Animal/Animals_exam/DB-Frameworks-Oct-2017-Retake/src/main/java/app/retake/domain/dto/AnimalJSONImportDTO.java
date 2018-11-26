package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AnimalJSONImportDTO implements Serializable {
    @Expose
    @Size(min = 3, max = 20)
    @NotNull
    private String name;
    @Expose
    @Size(min = 3, max = 20)
    @NotNull
    private String type;
    @Min(1)
    @Expose
    private Integer age;
    @Valid
    @Expose
    private PassportJSONImportDTO passport;

    public AnimalJSONImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
