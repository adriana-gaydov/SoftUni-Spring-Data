package exam.model.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
public class ImportShopRootDTO {

    @XmlElement(name = "shop")
    List<ImportShopDTO> shops;

    public ImportShopRootDTO() {
    }

    public List<ImportShopDTO> getShops() {
        return shops;
    }
}
