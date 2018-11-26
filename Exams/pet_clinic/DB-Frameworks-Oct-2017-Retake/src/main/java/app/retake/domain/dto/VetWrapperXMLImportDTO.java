package app.retake.domain.dto;


import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "vets")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetWrapperXMLImportDTO {

    @XmlElement(name = "vet")
    private List<VetXMLImportDTO> vets;

    public List<VetXMLImportDTO> getVets() {
        return vets;
    }

    public void setVets(List<VetXMLImportDTO> vets) {
        this.vets = vets;
    }
}
