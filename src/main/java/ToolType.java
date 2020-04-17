import java.math.BigDecimal;
import java.util.ArrayList;

public class ToolType {

    public static ArrayList<ToolType> toolTypes = new ArrayList<ToolType>();

    private int toolTypeID;
    private String toolTypeName;
    private BigDecimal dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    private ToolType() {
    }

    private ToolType( final int toolTypeID,
            final String toolTypeName,
            final BigDecimal dailyCharge,
            final boolean weekdayCharge,
            final boolean weekendCharge,
            final boolean holidayCharge) {
        this.setToolTypeID(toolTypeID);
        this.setToolTypeName(toolTypeName);
        this.setDailyCharge(dailyCharge);
        this.setWeekdayCharge(weekdayCharge);
        this.setWeekendCharge(weekendCharge);
        this.setHolidayCharge(holidayCharge);
    }

    public static void loadInToolTypes() {
        toolTypes.add( new ToolType( 1, "Ladder", new BigDecimal("1.99"), true, true, false ) );
        toolTypes.add( new ToolType( 2, "Chainsaw", new BigDecimal("1.49"), true, false, true ) );
        toolTypes.add( new ToolType( 3, "Jackhammer", new BigDecimal("2.99"), true, false, false ) );
    }

    public static ToolType getToolTypeByID(int toolTypeID) {
        for(ToolType toolType : toolTypes) {
           if(toolType.getToolTypeID() == toolTypeID) {
               return toolType;
           }
        }
        return null;
    }

    public int getToolTypeID() {
        return toolTypeID;
    }

    private void setToolTypeID(int toolTypeID) {
        this.toolTypeID = toolTypeID;
    }

    public String getToolTypeName() {
        return toolTypeName;
    }

    private void setToolTypeName(String toolTypeName) {
        this.toolTypeName = toolTypeName;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    private void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    private void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    private void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    private void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

}
