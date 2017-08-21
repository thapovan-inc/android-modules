package com.thapovan.android.commonutils.contacts;

import com.thapovan.android.commonutils.text.TextUtil;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String id;
    private String displayName;
    private List<Phone> mPhoneList = new ArrayList<>();
    private List<Email> mEmailList = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public List<Email> getEmailList() {
        return mEmailList;
    }

    public void setEmailList(List<Email> mEmailList) {
        this.mEmailList = mEmailList;
    }

    public void addEmail(Email e) {
        this.mEmailList.add(e);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String dName) {
        this.displayName = dName;
    }

    public List<Phone> getPhoneList() {
        return mPhoneList;
    }

    public void setPhoneList(List<Phone> mPhoneList) {
        this.mPhoneList = mPhoneList;
    }

    public void addPhone(Phone phone) {
        this.mPhoneList.add(phone);
    }

    public boolean hasEmail() {
        return TextUtil.isListNotEmpty(getEmailList());
    }
    public boolean hasPhone(){
        return TextUtil.isListNotEmpty(getPhoneList());
    }
    public boolean hasAddress(){
        return TextUtil.isListNotEmpty(getAddresses());
    }

    public Email getFirstEmail(){
        if(hasEmail()){
            return getEmailList().get(0);
        }
        return null;
    }

    public Phone getFirstPhone(){
        if(hasPhone()){
            return getPhoneList().get(0);
        }
        return null;
    }

    public Address getFirstAddress(){
        if(hasAddress()){
            return getAddresses().get(0);
        }
        return null;
    }

    public static class Address {
        private String mPOBox= "";
        private String mStreet= "";
        private String mCity= "";
        private String mState= "";
        private String mPostalCode= "";
        private String mCountry= "";

        public String getPoBox() {
            return mPOBox;
        }
        public void setPoBox(String poBox) {
            this.mPOBox = poBox;
        }
        public String getStreet() {
            return mStreet;
        }
        public void setStreet(String street) {
            this.mStreet = street;
        }
        public String getCity() {
            return mCity;
        }
        public void setCity(String city) {
            this.mCity = city;
        }
        public String getState() {
            return mState;
        }
        public void setState(String state) {
            this.mState = state;
        }
        public String getPostalCode() {
            return mPostalCode;
        }
        public void setPostalCode(String postalCode) {
            this.mPostalCode = postalCode;
        }
        public String getCountry() {
            return mCountry;
        }
        public void setCountry(String country) {
            this.mCountry = country;
        }

        public Address(String poBox, String street, String city, String state,
                       String postal, String country) {
            this.setPoBox(poBox);
            this.setStreet(street);
            this.setCity(city);
            this.setState(state);
            this.setPostalCode(postal);
            this.setCountry(country);
        }

        public Address() {

        }

        @Override
        public String toString() {
            return "Address{" +
                    "mPOBox='" + mPOBox + '\'' +
                    ", mStreet='" + mStreet + '\'' +
                    ", mCity='" + mCity + '\'' +
                    ", mState='" + mState + '\'' +
                    ", mPostalCode='" + mPostalCode + '\'' +
                    ", mCountry='" + mCountry + '\'' +
                    '}';
        }
    }

    public static class Email {
        private String mAddress;
        private String mType;
        public String getAddress() {
            return mAddress;
        }
        public void setAddress(String address) {
            this.mAddress = address;
        }
        public String getType() {
            return mType;
        }
        public void setType(String t) {
            this.mType = t;
        }

        public Email(String address, String type) {
            this.mAddress = address;
            this.mType = type;
        }
    }

    public static class Phone {
        private String mNumber;
        private String mType;
        private boolean isPrimary;

        public String getNumber() {
            return mNumber;
        }

        public void setNumber(String number) {
            this.mNumber = number;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            this.mType = type;
        }

        public boolean isPrimary() {
            return isPrimary;
        }

        public void setPrimary(boolean primary) {
            isPrimary = primary;
        }

        public Phone(String n, String t) {
            this.mNumber = n;
            this.mType = t;
        }

        @Override
        public String toString() {
            return ""+ mNumber;
        }

        public String getCleanNumber() {
            return getNumber().replace("+91","").replace(" ","");
        }
    }

}
