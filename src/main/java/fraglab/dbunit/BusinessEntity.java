package fraglab.dbunit;

import java.util.List;

public class BusinessEntity {

    private Integer id;
    private String value;
    private List<String> listValues;

    public BusinessEntity() { }

    public BusinessEntity(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getListValues() {
        return listValues;
    }

    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    @Override
    public String toString() {
        Integer valsSize = null;
        if (listValues != null) {
            valsSize = listValues.size();
        }
        return "BusinessEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", listValues=" + valsSize +
                '}';
    }

}
