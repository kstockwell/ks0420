import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeDepotRental {

    public static RentalAgreement main( String[] args ) {

        RentalAgreement rentalAgreement = new RentalAgreement();

        try {

            // Load in the tools, brands, and toolTypes
            ToolType.loadInToolTypes();
            Brand.loadInBrands();
            Tool.loadInTools();

            //Parameters provided with the sale:
            // Tool Code
            // Days rented
            // Percent of discount
            // Check-out date

            // Check that the proper number of arguments were provided
            String toolCode = args[0];
            if( toolCode == null || toolCode.trim().equals("") ) {
                System.err.println("Must provide a tool code");
                return rentalAgreement;
            }
            // Check that the tool is valid
            Tool tool = Tool.getToolByCode(toolCode);
            if( tool == null ) {
                System.err.println("Must provide valid tool code");
                return rentalAgreement;
            }

            String checkoutDateStr = args[1];
            if( checkoutDateStr == null || checkoutDateStr.trim().equals("") ) {
                System.err.println("Must provide a checkout date");
                return rentalAgreement;
            }
            String datePattern = "\\d{1,2}/\\d{1,2}/\\d{2}";
            if( !checkoutDateStr.matches( datePattern ) ) {
                System.err.println("Please provide a date in a valid format (mm/dd/yy)");
                return rentalAgreement;
            }
            Calendar checkoutDate = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy", Locale.ENGLISH);
            checkoutDate.setTime(sdf.parse(checkoutDateStr));// all done

            String rentalDaysStr = args[2];
            if( rentalDaysStr == null || rentalDaysStr.trim().equals("") ) {
                System.err.println("Must provide number of rental days");
                return rentalAgreement;
            }
            String allDigits = "\\d+";
            if( !rentalDaysStr.matches( allDigits ) ) {
                System.err.println("Rental days must be a whole number");
                return rentalAgreement;
            }
            int rentalDays = Integer.parseInt(rentalDaysStr);
            // Check that the rental days are valid
            if( rentalDays <= 0 ) {
                System.err.println("Rental must be for at least one day");
                return rentalAgreement;
            }

            String discountPercentString = args[3];
            if( discountPercentString == null || discountPercentString.trim().equals("") ) {
                System.err.println("Must provide a discount percent");
                return rentalAgreement;
            }
            if( !discountPercentString.matches( allDigits ) ) {
                System.err.println("Discount percent must be a whole number");
                return rentalAgreement;
            }
            int discountPercent = Integer.parseInt(discountPercentString);
            // Check that the discount percentage is valid
            if( discountPercent < 0 || discountPercent > 100 ) {
                System.err.println("Discount can only be between 0% and 100%");
                return rentalAgreement;
            }

            // Produce Rental Agreement
            rentalAgreement = new RentalAgreement(tool, rentalDays, discountPercent, checkoutDate);

            // Process the agreement
            rentalAgreement.processRentalAgreement();

            // Print out the receipt
            rentalAgreement.printReceipt();

            return rentalAgreement;

        } catch ( Exception e) {
            e.printStackTrace();
        }

        return rentalAgreement;

    }

}
