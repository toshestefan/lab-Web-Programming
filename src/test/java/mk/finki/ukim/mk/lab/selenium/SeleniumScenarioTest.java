package mk.finki.ukim.mk.lab.selenium;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {


    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;
    


    @Autowired
    BalloonService balloonService;


    private HtmlUnitDriver driver;


    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;

    private static String user = "toshe";
    private static String admin = "admin";

    private static boolean dataInitialized = false;


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {

            m1 = manufacturerService.save("m1", "m1","m1");
            m2 = manufacturerService.save("m2", "m2","m2");


            regularUser = userService.register(user, user, user, LocalDate.now(), "a", Role.ROLE_USER);
            adminUser = userService.register(admin, admin, admin, LocalDate.now(), admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElemts(0, 0, 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        balloonsPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
        balloonsPage.assertElemts(0, 0, 0, 0, 1);

        balloonsPage = AddOrEditBalloon.addBalloon(this.driver, "test", "100", m2.getName());
        balloonsPage.assertElemts(1, 1, 1, 1, 1);

        balloonsPage = AddOrEditBalloon.addBalloon(this.driver, "test1", "200", m2.getName());
        balloonsPage.assertElemts(2, 2, 2, 2, 1);

        balloonsPage.getDeleteButtons().get(1).click();
        balloonsPage.assertElemts(1, 1, 1, 1, 1);

        balloonsPage = AddOrEditBalloon.editBalloon(this.driver, balloonsPage.getEditButtons().get(0), "test1", "200",  m2.getName());
        balloonsPage.assertElemts(1, 1, 1, 1, 1);

        loginPage = LoginPage.logout(this.driver);
        balloonsPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), "a");
        balloonsPage.assertElemts(1, 0, 0, 0, 0);





    }


}
