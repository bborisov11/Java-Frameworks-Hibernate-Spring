package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryExportDTO {
    @XmlElement
    private String name;
    @XmlElement(name = "most-popular-item")
    private List<MostPopularItemDTO> items;

    public CategoryExportDTO() {
    }

    public CategoryExportDTO(String name, List<MostPopularItemDTO> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MostPopularItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MostPopularItemDTO> items) {
        this.items = items;
    }
}
