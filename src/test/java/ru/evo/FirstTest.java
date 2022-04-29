package ru.evo;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import static io.qameta.allure.Allure.step;

@Tag("demoqaTest")
public class FirstTest extends TestBase {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();

    // Значения
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = "Other",
            day = "01",
            month = "April",
            year = "2002",
            phone = faker.phoneNumber().subscriberNumber(10),
            subjects = "Math",
            hobby = "Sports",
            img = "testimg.png",
            address = faker.address().streetAddress(),
            state = "Uttar Pradesh",
            city = "Merrut";


    @Test
    @DisplayName("Full fill reg form test")
    void actions() {
        step("Open registration form", () -> {
            registrationFormPage.openPage("/automation-practice-form");

        });
        step("Fill form", () -> {
            registrationFormPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(phone)
                    .setBirthDate(day, month, year)
                    .setSubjects(subjects)
                    .setUserHobbies(hobby)
                    .upLoadPicture(img)
                    .setAddress(address)
                    .setState(state)
                    .setCity(city)
                    .submitClick();
        });

        step("Check from data", () -> {
            registrationFormPage.checkCompletedForm("Student Name", firstName + " " + lastName)
                    .checkCompletedForm("Student Email", userEmail)
                    .checkCompletedForm("Gender", gender)
                    .checkCompletedForm("Mobile", phone)
                    .checkCompletedForm("Date of Birth", day + " " + month + "," + year)
                    .checkCompletedForm("Subjects", subjects)
                    .checkCompletedForm("Hobbies", hobby)
                    .checkCompletedForm("Picture", img)
                    .checkCompletedForm("Address", address)
                    .checkCompletedForm("State and City", state + " " + city);
        });
    }
}

