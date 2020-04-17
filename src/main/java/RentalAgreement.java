import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RentalAgreement {

    /*
    *
        * Tool code - Specified at checkout
          Tool type - From tool info
          Tool brand - From tool info
          Rental days - Specified at checkout
          Check out date  - Specified at checkout
          Due date - Calculated from checkout date and rental days.
          Daily rental charge - Amount per day, specified by the tool type.
          Charge days - Count of chargeable days, from day after checkout through and including due date, excluding “no charge” days as specified by the tool type.
          Pre-discount charge - Calculated as charge days X daily charge. Resulting total rounded half up to cents.
          Discount percent - Specified at checkout.
          Discount amount - calculated from discount % and pre-discount charge.  Resulting amount rounded half up to cents.
          Final charge - Calculated as pre-discount charge - discount amount.
     */

    private int rentalAgreementID;
    private String toolCode;
    private ToolType toolType;
    private Brand brand;
    private Integer rentalDays;
    private Calendar checkoutDate;
    private Calendar dueDate;
    private BigDecimal dailyRentalCharge;
    private Integer chargeDays;
    private BigDecimal preDiscountCharge;
    private Integer discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public RentalAgreement() {}

    public RentalAgreement( Tool tool, Integer rentalDays, Integer discountPercent,
                            Calendar checkoutDate) {
        this.setToolCode(tool.getToolCode());
        this.setToolType(tool.getToolType());
        this.setBrand(tool.getBrand());
        this.setDailyRentalCharge(tool.getToolType().getDailyCharge());
        this.setRentalDays(rentalDays);
        this.setDiscountPercent(discountPercent);
        this.setCheckoutDate(checkoutDate);
    }

    public int getRentalAgreementID() {
        return rentalAgreementID;
    }

    private void setRentalAgreementID(int rentalAgreementID) {
        this.rentalAgreementID = rentalAgreementID;
    }

    public String getToolCode() {
        return toolCode;
    }

    private void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    private void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    public Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    private void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Calendar getCheckoutDate() {
        return checkoutDate;
    }

    private void setCheckoutDate(Calendar checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    private void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    private void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public Integer getChargeDays() {
        return chargeDays;
    }

    private void setChargeDays(Integer chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    private void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    private void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    private void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    private void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public void processRentalAgreement() {
        /*
        * The following are already set:
        this.setToolCode(tool.getToolCode());
        this.setToolType(tool.getToolType());
        this.setBrand(tool.getBrand());
        this.setDailyRentalCharge(tool.getToolType().getDailyCharge());
        this.setRentalDays(rentalDays);
        this.setDiscountPercent(discountPercent);
        this.setCheckoutDate(checkoutDate);
        * */

        this.setDueDate(this.calculateDueDate(this.getRentalDays(), this.getCheckoutDate()));
        this.setChargeDays(this.calculateChargeDays(this.getCheckoutDate(), this.getDueDate(), this.toolType));
        this.setPreDiscountCharge(this.calculatePreDiscountCharge(this.getChargeDays(), this.getDailyRentalCharge()));
        this.setDiscountAmount(this.calculateDiscountAmount(this.getPreDiscountCharge(),this.getDiscountPercent()));
        this.setFinalCharge(this.calculateFinalCharge(this.getPreDiscountCharge(), this.getDiscountAmount()));

    }

    private Calendar calculateDueDate( Integer rentalDays, Calendar checkoutDate ) {
        Calendar dueDate = Calendar.getInstance();
        dueDate.setTimeInMillis( checkoutDate.getTimeInMillis() );
        dueDate.add(Calendar.DAY_OF_YEAR, rentalDays);
        return dueDate;
    }

    private int calculateChargeDays( Calendar checkoutDate, Calendar dueDate, ToolType toolType) {
        int daysToCharge = 0;
        Calendar dateToAnalyze = (Calendar) checkoutDate.clone();
        dateToAnalyze.add(Calendar.DAY_OF_YEAR, 1);
        do {
            if( !isHoliday(dateToAnalyze) ) {
                if( (( toolType.isWeekdayCharge() && isWeekday(dateToAnalyze) )
                        || (toolType.isWeekendCharge() && isWeekendDay(dateToAnalyze) ))
                ) {
                    daysToCharge++;
                }
            } else {
                if( toolType.isHolidayCharge() ) {
                    daysToCharge++;
                }
            }
            dateToAnalyze.add(Calendar.DAY_OF_YEAR, 1);
        } while( dateToAnalyze.before( dueDate ) || dateToAnalyze.equals(dueDate) );
        return daysToCharge;
    }

    private boolean isWeekday(Calendar calendar) {
        return ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) ||
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY)||
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)||
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)||
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY));
    }

    private boolean isWeekendDay(Calendar calendar) {
        return ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) ||
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY));
    }

    private boolean isHoliday(Calendar calendar) {
        return Holiday.isFourthOfJuly(calendar) || Holiday.isLaborDay(calendar);
    }

    private BigDecimal calculatePreDiscountCharge(Integer chargeDays, BigDecimal dailyRentalCharge) {
        // Base calculation - days charged time the charge per day
        BigDecimal preDiscountCharge = dailyRentalCharge.multiply( new BigDecimal(chargeDays) );
        // Round half-up to the nearest cents
        preDiscountCharge = preDiscountCharge.setScale(2, RoundingMode.HALF_UP);
        return preDiscountCharge;
    }

    private BigDecimal calculateDiscountAmount(BigDecimal preDiscountCharge, Integer discountPercent) {
        // convert disacountPercent from an int to a decimal percent
        BigDecimal percentAsDecimal = new BigDecimal( discountPercent ).movePointLeft(2);
        // Base calculation - pre-discount charge * discount percentage
        BigDecimal discountAmount = preDiscountCharge.multiply(percentAsDecimal);
        // Round half-up to the nearest cents
        discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);
        return discountAmount;
    }

    private BigDecimal calculateFinalCharge( BigDecimal preDiscountCharge, BigDecimal discountAmount ) {
        // Base calculation - pre-discount charge - discount
        return preDiscountCharge.subtract(discountAmount);
    }

    public void printReceipt() {

//        private String toolCode;
//        ToolType toolType;
//        Brand brand;
//        Integer rentalDays;
//        Calendar checkoutDate;
//        Calendar dueDate;
//        BigDecimal dailyRentalCharge;
//        Integer chargeDays;
//        BigDecimal preDiscountCharge;
//        Integer discountPercent;
//        BigDecimal discountAmount;
//        BigDecimal finalCharge;
        System.out.println( "Tool Code: " + this.getToolCode() );
        System.out.println( "Tool Type: " + this.getToolType().getToolTypeName() );
        System.out.println( "Brand: " + this.getBrand().getBrandName() );
        System.out.println( "Rental Days: " + this.getRentalDays() );
        System.out.println( "Checkout Date: " + formatReadableDate(this.getCheckoutDate()) );
        System.out.println( "Due Date: " + formatReadableDate(this.getDueDate()) );
        System.out.println( "Daily Rental Charge: " + formatDollarAmount(this.getDailyRentalCharge()) );
        System.out.println( "Charge Days: " + this.getChargeDays() );
        System.out.println( "Pre-Discount Charge: " + formatDollarAmount(this.getPreDiscountCharge()) );
        System.out.println( "Discount Percent: " + formatPercent(this.getDiscountPercent()) );
        System.out.println( "Discount Amount: " + formatDollarAmount(this.getDiscountAmount()) );
        System.out.println( "Final Charge: " + formatDollarAmount(this.getFinalCharge()) );

    }

    private String formatReadableDate( Calendar calendar ) {
        Date date = Calendar.getInstance().getTime();
        date.setTime(calendar.getTimeInMillis());
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        return dateFormat.format(date);
    }

    private String formatDollarAmount( BigDecimal dollarAmount ) {
        Locale unitedStates = Locale.US;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(unitedStates);
        return currencyFormatter.format( dollarAmount );
    }

    private String formatPercent(Integer discountPercent) {
        return discountPercent + "%";
    }

}
