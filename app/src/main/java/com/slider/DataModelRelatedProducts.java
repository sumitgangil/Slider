package com.slider;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by sumit on 6/2/2017.
 */

public class DataModelRelatedProducts implements Parcelable {

    String ProductId, imageUrl, Productname, Price, DummyPrice;

    public DataModelRelatedProducts(String ProductId, String imageUrl, String Productname, String Price, String DummyPrice) {


        this.ProductId = ProductId;
        this.imageUrl = imageUrl;
        this.Productname = Productname;
        this.Price = Price;
        this.DummyPrice = DummyPrice;

    }


    public DataModelRelatedProducts(Parcel in) {

        ProductId = in.readString();
        imageUrl = in.readString();
        Productname = in.readString();
        Price = in.readString();
        DummyPrice = in.readString();


    }


    public static final Creator<DataModelRelatedProducts> CREATOR = new Creator<DataModelRelatedProducts>() {
        @Override
        public DataModelRelatedProducts createFromParcel(Parcel in) {
            return new DataModelRelatedProducts(in);
        }

        @Override
        public DataModelRelatedProducts[] newArray(int size) {
            return new DataModelRelatedProducts[size];
        }
    };


    public String getImageUrl() {
        return "http://wonderhomes.co/" + imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDummyPrice() {
        return DummyPrice;
    }

    public void setDummyPrice(String dummyPrice) {
        DummyPrice = dummyPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ProductId);
        parcel.writeString(imageUrl);
        parcel.writeString(Productname);
        parcel.writeString(Price);
        parcel.writeString(DummyPrice);

    }

    public static class AppResult {
        private List<DataModelRelatedProducts> results;

        public List<DataModelRelatedProducts> getResults() {
            return results;
        }
    }


}
