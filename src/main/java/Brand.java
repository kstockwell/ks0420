import java.util.ArrayList;

public class Brand {

    public static ArrayList<Brand> brands = new ArrayList<Brand>();

    private Integer brandID;
    private String brandName;

    private Brand() {}

    private Brand( Integer brandID, String brandName ) {
        this.setBrandID(brandID);
        this.setBrandName(brandName);
    }

    public static void loadInBrands() {
        brands.add( new Brand( 1, "Werner" ) );
        brands.add( new Brand( 2, "Stihl" ) );
        brands.add( new Brand( 3, "Ridgid" ) );
        brands.add( new Brand( 4, "DeWalt" ) );
    }

    public static Brand getBrandByID(int brandID) {
        for(Brand brand : brands) {
            if(brand.getBrandID() == brandID) {
                return brand;
            }
        }
        return null;
    }

    public Integer getBrandID() {
        return brandID;
    }

    private void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    private void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}
