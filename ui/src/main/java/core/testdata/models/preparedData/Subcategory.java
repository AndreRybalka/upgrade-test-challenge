package core.testdata.models.preparedData;

public class Subcategory {

	private String subcategoryTitle;
	private boolean isHasProducts;

	public Subcategory(String subcategoryTitle, boolean isHasProducts) {
		this.subcategoryTitle = subcategoryTitle;
		this.isHasProducts = isHasProducts;
	}

	public String getSubcategoryTitle() {
		return subcategoryTitle;
	}

	public void setSubcategoryTitle(String subcategoryTitle) {
		this.subcategoryTitle = subcategoryTitle;
	}

	public boolean isHasProducts() {
		return isHasProducts;
	}

	public void setHasProducts(boolean isHasProducts) {
		this.isHasProducts = isHasProducts;
	}

	@Override
	public String toString() {
		return "Subcategory: {" + subcategoryTitle + ", " + isHasProducts + "}";
	}
}
