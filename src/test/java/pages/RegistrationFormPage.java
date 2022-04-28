package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendarComponent = new CalendarComponent();

    // locators
    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userGenderClick = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendar = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            userHobbies = $("#hobbiesWrapper"),
            upLoadPicture = $("#uploadPicture"),
            userAddress = $("#currentAddress"),
            setState = $("#react-select-3-input"),
            setCity = $("#react-select-4-input"),
            submitClick = $("#submit"),
            resultsTable = $(".table-responsive");

    // actions
    public RegistrationFormPage openPage(String secondUrl) {
        open(secondUrl);
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationFormPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);

        return this;
    }

    public RegistrationFormPage setGender(String userGender) {
        userGenderClick.$(byText(userGender)).click();

        return this;
    }

    public RegistrationFormPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);

        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        calendar.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationFormPage setSubjects(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();

        return this;
    }

    public RegistrationFormPage setUserHobbies(String hobbies) {
        userHobbies.$(byText(hobbies)).click();

        return this;
    }

    public RegistrationFormPage upLoadPicture(String namePicture) {
        upLoadPicture.uploadFromClasspath("img/" + namePicture);

        return this;
    }

    public RegistrationFormPage setAddress(String address) {
        userAddress.setValue(address);

        return this;
    }

    public RegistrationFormPage setState(String state) {
        setState.setValue(state).pressEnter();

        return this;
    }

    public RegistrationFormPage setCity(String City) {
        setCity.setValue(City).pressEnter();

        return this;
    }

    public RegistrationFormPage submitClick() {
        submitClick.click();

        return this;
    }

    public RegistrationFormPage checkCompletedForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName))
                .parent().shouldHave(text(value));

        return this;
    }

}

