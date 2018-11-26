package org.softuni.mostwanted.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesXMLwrapperImportDTO {

    @XmlElement(name = "race-entry")
    private List<RaceEntriesXMLImportDTO> entries;

    public List<RaceEntriesXMLImportDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntriesXMLImportDTO> entries) {
        this.entries = entries;
    }
}
