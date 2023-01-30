package item;

import java.io.InputStream;

public interface ItemIF {

    public String getType();

    public String getUrl();

    public Double getPrice();

    public String getName();


    public Double getMinPrice();

    public String getDescription();

    public Integer getDiscount();

    public Double getShippingPrice();

    public InputStream getImg();

    public void setName(String name);

    public void setMinPrice(Double minPrice);
}
