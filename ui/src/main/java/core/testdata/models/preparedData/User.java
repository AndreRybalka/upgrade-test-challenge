package core.testdata.models.preparedData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "firstName",
        "lastName",
        "email",
        "password",
        "address",
        "address2",
        "company",
        "city",
        "state",
        "zip",
        "phoneFirstField",
        "phoneSecondField",
        "phoneThirdField",
        "phoneExtField",
        "gender",
        "dayOfBirth",
        "monthOfBirth",
        "yearOfBirth",
        "userMailForPasswordRecovering"
})
public class User implements ICountry {

    @JsonProperty("countries")
    private String countries;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("emailAddress")
    private String emailAddress;
    @JsonProperty("password")
    private String password;
    @JsonProperty("address")
    private String address;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("company")
    private String company;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("phoneFirstField")
    private String phoneFirstField;
    @JsonProperty("phoneSecondField")
    private String phoneSecondField;
    @JsonProperty("phoneThirdField")
    private String phoneThirdField;
    @JsonProperty("phoneExtField")
    private String phoneExtField;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("dayOfBirth")
    private String dayOfBirth;
    @JsonProperty("monthOfBirth")
    private String monthOfBirth;
    @JsonProperty("yearOfBirth")
    private String yearOfBirth;
    @JsonProperty("userMailForPasswordRecovering")
    private String userMailForPasswordRecover;
    @JsonProperty("facebookUser")
    private String facebookUser;
    @JsonProperty("facebookPassword")
    private String facebookPassword;


    public User(){

    }

    public User(User user){
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmailAddress(user.getEmailAddress());
        setPassword(user.getPassword());
        setCountries(user.getCountries());
        setAddress2(user.getAddress2());
        setCompany(user.getCompany());
        setCity(user.getCity());
        setState(user.getState());
        setZip(user.getZip());
        setPhoneFirstField(user.getPhoneFirstField());
        setPhoneSecondField(user.getPhoneSecondField());
        setPhoneThirdField(user.getPhoneThirdField());
        setPhoneExtField(user.getPhoneExtField());
        setGender(user.getGender());
        setDayOfBirth(user.getDayOfBirth());
        setMonthOfBirth(user.getMonthOfBirth());
        setYearOfBirth(user.getYearOfBirth());
        setUserMailForPasswordRecover(user.getUserMailForPasswordRecover());
        setAddress(user.getAddress());
        setFacebookUser(user.getFacebookUser());
        setFacebookPassword(user.getFacebookPassword());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhoneFirstField(String phoneFirstField) {
        this.phoneFirstField = phoneFirstField;
    }

    public void setPhoneSecondField(String phoneSecondField) {
        this.phoneSecondField = phoneSecondField;
    }

    public void setPhoneThirdField(String phoneThirdField) {
        this.phoneThirdField = phoneThirdField;
    }

    public void setPhoneExtField(String phoneExtField) {
        this.phoneExtField = phoneExtField;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setUserMailForPasswordRecover(String userMailForPasswordRecover) { this.userMailForPasswordRecover = userMailForPasswordRecover; }

    public void setFacebookUser(String facebookUser) { this.facebookUser = facebookUser; }

    public String getFacebookPassword() { return facebookPassword; }

    public String getFacebookUser() { return facebookUser; }

    public void setFacebookPassword(String facebookPassword) { this.facebookPassword = facebookPassword; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getUserMailForPasswordRecover() { return userMailForPasswordRecover; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }

    public String getAddress2() { return address2; }

    public String getCompany() { return company; }

    public String getCity() { return city; }

    public String getZip() { return zip; }

    public String getPhoneFirstField() { return phoneFirstField; }

    public String getPhoneSecondField() { return phoneSecondField; }

    public String getPhoneThirdField() { return phoneThirdField; }

    public String getPhoneExtField() { return phoneExtField; }

    public String getState() { return state; }

    public String getGender() { return gender; }

    public String getDayOfBirth() { return dayOfBirth; }

    public String getMonthOfBirth() { return monthOfBirth; }

    public String getYearOfBirth() { return yearOfBirth; }

    public String getCountries() { return countries; }

    /*@Override
    public String toString() {
        return "User{" +
                "countries='" + countries + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", company='" + company + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phoneFirstField='" + phoneFirstField + '\'' +
                ", phoneSecondField='" + phoneSecondField + '\'' +
                ", phoneThirdField='" + phoneThirdField + '\'' +
                ", phoneExtField='" + phoneExtField + '\'' +
                ", gender='" + gender + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", monthOfBirth='" + monthOfBirth + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", userMailForPasswordRecover='" + userMailForPasswordRecover + '\'' +
                '}';
    }*/
}

