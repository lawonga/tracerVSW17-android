package tracer.vsw17;

import org.parceler.Parcel;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@Parcel
public class FundRaiseModel {
    String title;
    String imageUrl;
    String description;
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

    public void setEthCost(double ethCost) {
        this.ethCost = ethCost;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
