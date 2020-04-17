import java.util.ArrayList;

public class Tool {

    public static ArrayList<Tool> tools = new ArrayList<Tool>();

    private Integer toolID;
    private ToolType toolType;
    private Brand brand;
    private String toolCode;

    private Tool() {
    }

    public static void loadInTools() {
        tools.add( new Tool( 1, ToolType.getToolTypeByID(1), Brand.getBrandByID(1), "LADW" ) );
        tools.add( new Tool( 2, ToolType.getToolTypeByID(2), Brand.getBrandByID(2), "CHNS" ) );
        tools.add( new Tool( 3, ToolType.getToolTypeByID(3), Brand.getBrandByID(3), "JAKR" ) );
        tools.add( new Tool( 4, ToolType.getToolTypeByID(3), Brand.getBrandByID(4), "JACKD" ) );
    }

    public static Tool getToolByID(int toolID) {
        for(Tool tool : tools) {
            if(tool.getToolID() == toolID) {
                return tool;
            }
        }
        return null;
    }

    public static Tool getToolByCode(String toolCode) {
        for(Tool tool : tools) {
            if(tool.getToolCode().equals(toolCode)) {
                return tool;
            }
        }
        return null;
    }

    public Tool( Integer toolID, ToolType toolType, Brand brand, String toolCode ) {
        this.setToolID(toolID);
        this.setToolType(toolType);
        this.setBrand(brand);
        this.setToolCode(toolCode);
    }

    public Integer getToolID() {
        return toolID;
    }

    public void setToolID(Integer toolID) {
        this.toolID = toolID;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

}
