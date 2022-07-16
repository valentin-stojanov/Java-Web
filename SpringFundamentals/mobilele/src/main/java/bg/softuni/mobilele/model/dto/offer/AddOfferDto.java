package bg.softuni.mobilele.model.dto.offer;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

import javax.validation.constraints.*;

public class AddOfferDto {

    @NotNull
    @Min(1)
    private Long modelId;
    @NotNull
    private EngineEnum engine;

    @Positive
    @NotNull
    private Integer price;

    @Min(1900)
    @Positive
    @NotNull
    private Integer year;

    @NotEmpty
    private String description;

    @NotNull
    private TransmissionEnum transmission;

    @NotEmpty
    private String imageUrl;

    @Min(0)
    @Max(900000)
    private int mileage;

    public EngineEnum getEngine() {
        return engine;
    }

    public AddOfferDto setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOfferDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public AddOfferDto setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public AddOfferDto setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public AddOfferDto setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public AddOfferDto setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOfferDto setDescription(String description) {
        this.description = description;
        return this;
    }


    public int getMileage() {
        return mileage;
    }

    public AddOfferDto setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }
}
