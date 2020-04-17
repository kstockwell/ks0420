import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

class HomeDepotRentalTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testRentalScenarioWithNoArguments() {
        try {
            System.out.println("testRentalScenarioWithNoArguments");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{null, null, null, null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithOneArguments() {
        try {
            System.out.println("testRentalScenarioWithOneArguments");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", null, null, null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithTwoArguments() {
        try {
            System.out.println("testRentalScenarioWithTwoArguments");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/13/15", null, null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithThreeArguments() {
        try {
            System.out.println("testRentalScenarioWithThreeArguments");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/13/15", "5", null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidRentalDays1() {
        try {
            System.out.println("testRentalScenarioWithInvalidRentalDays1");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/13/15", "0", "101"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidRentalDays2() {
        try {
            System.out.println("testRentalScenarioWithInvalidRentalDays2");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/13/15", "aa", "101"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidRentalDays3() {
        try {
            System.out.println("testRentalScenarioWithInvalidRentalDays3");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/13/15", "", "101"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidCode1() {
        try {
            System.out.println("testRentalScenarioWithInvalidCode1");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"FAKE", "9/13/15", "5", "101"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidCode2() {
        try {
            System.out.println("testRentalScenarioWithInvalidCode2");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"", "9/13/15", "5", "101"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidDate1() {
        try {
            System.out.println("testRentalScenarioWithInvalidDate1");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "FakeDay", "5", "101"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidDate2() {
        try {
            System.out.println("testRentalScenarioWithInvalidDate2");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "", "5", "101"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidPercent1() {
        try {
            System.out.println("testRentalScenarioWithInvalidPercent1");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/13/15", "5", "101"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidPercent2() {
        try {
            System.out.println("testRentalScenarioWithInvalidPercent2");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/13/15", "5", "fifty"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioWithInvalidPercent3() {
        try {
            System.out.println("testRentalScenarioWithInvalidPercent3");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/13/15", "5", ""});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioNormalRental1() {
        try {
            System.out.println("testRentalScenarioNormalRental1");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/3/15", "5", "50"});
            assertEquals( rentalAgreement.getFinalCharge(), new BigDecimal("2.99"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioNormalRental2() {
        try {
            System.out.println("testRentalScenarioNormalRental2");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"LADW", "7/2/20", "3", "10"});
            assertEquals( rentalAgreement.getFinalCharge(), new BigDecimal("3.58"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioNormalRental3() {
        try {
            System.out.println("testRentalScenarioNormalRental3");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"CHNS", "7/2/15", "5", "25"});
            assertEquals( rentalAgreement.getFinalCharge(), new BigDecimal("4.47"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioNormalRental4() {
        try {
            System.out.println("testRentalScenarioNormalRental4");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "9/3/15", "6", "0"});
            assertEquals( rentalAgreement.getFinalCharge(), new BigDecimal("8.97"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioNormalRental5() {
        try {
            System.out.println("testRentalScenarioNormalRental5");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "7/2/15", "9", "0"});
            assertEquals( rentalAgreement.getFinalCharge(), new BigDecimal("17.94"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRentalScenarioNormalRental6() {
        try {
            System.out.println("testRentalScenarioNormalRental6");
            RentalAgreement rentalAgreement = HomeDepotRental.main(new String[]{"JAKR", "7/2/20", "4", "50"});
            assertEquals( rentalAgreement.getFinalCharge(), new BigDecimal("2.99"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}