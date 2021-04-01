package model;

public class paymentCard {

    private String CardNumber;
    private String MM;
    private String YY;
    private String CVC;
    private String CardHolderName;
    private String Country;
    private String Adr1;
    private String Adr2;
    private String City;

    public paymentCard(String cardNumber, String MM, String YY, String CVC, String cardHolderName, String country, String adr1, String adr2, String city, String customerID) {
        CardNumber = cardNumber;
        this.MM = MM;
        this.YY = YY;
        this.CVC = CVC;
        CardHolderName = cardHolderName;
        Country = country;
        Adr1 = adr1;
        Adr2 = adr2;
        City = city;
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    private String customerID;

    public paymentCard(String cardNumber, String MM, String YY, String CVC, String cardHolderName, String country, String adr1, String adr2, String city) {
        CardNumber = cardNumber;
        this.MM = MM;
        this.YY = YY;
        this.CVC = CVC;
        CardHolderName = cardHolderName;
        Country = country;
        Adr1 = adr1;
        Adr2 = adr2;
        City = city;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getMM() {
        return MM;
    }

    public void setMM(String MM) {
        this.MM = MM;
    }

    public String getYY() {
        return YY;
    }

    public void setYY(String YY) {
        this.YY = YY;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public String getCardHolderName() {
        return CardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        CardHolderName = cardHolderName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAdr1() {
        return Adr1;
    }

    public void setAdr1(String adr1) {
        Adr1 = adr1;
    }

    public String getAdr2() {
        return Adr2;
    }

    public void setAdr2(String adr2) {
        Adr2 = adr2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

}
