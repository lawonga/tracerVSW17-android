package tracer.vsw17.model;

import org.parceler.Parcel;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@Parcel
public class FundRaiseModel {
    String title, subTitle, imageUrl, description, host, partner;
    double ethCost;
    float percentage;

    public FundRaiseModel() {
    }

    public FundRaiseModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEthCostDescription() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return "Eth to donate: " + formatter.format(ethCost);
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public double getEthCost() {
        return ethCost;
    }

    public void setEthCost(double ethCost) {
        this.ethCost = ethCost;
    }
}
