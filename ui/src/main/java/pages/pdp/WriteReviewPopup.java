package pages.pdp;

import core.base.AbstractBaseComponent;
import core.base.controls.BaseWebElement;
import core.base.controls.WebCheckbox;
import core.base.controls.WebInput;
import core.base.controls.WebSelect;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static constants.Constants.*;
import static core.SLF4JLogger.log;
import static core.contsants.Constants.Attributes.CLASS;
import static core.helpers.ComparisonHelper.verifyStringContains;
import static core.helpers.WebElementsActionsHelper.clickOnTheBaseWebElement;
import static core.helpers.WebElementsActionsHelper.hoverOnElement;
import static core.utils.ConfigVariables.CA;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Profile({CA})
public class WriteReviewPopup extends AbstractBaseComponent {


    @FindBy(xpath = "//div[contains(@class,'content-submission-review')]")
    private BaseWebElement writeReviewPopUp;

    @FindBy(className = "bv-mbox-breadcrumb-item")
    private BaseWebElement reviewTitle;

    @FindBy(xpath = "//div[@class = 'bv-review-field-content-wrapper']//textarea")
    private WebInput reviewTextArea;

    @FindBy(className = "bv-form-actions-submit")
    private BaseWebElement postReviewButton;

    @FindBy(xpath = "//input[@id='bv-text-field-title']")
    private WebInput reviewTitleInput;

    @FindBy(xpath = "//span[@aria-labelledby='bv-fieldset-label-rating']//span[contains(@class,'star-rating-control')]/span/a[@id]")
    private List<BaseWebElement> overallRating;

    @FindBy(xpath = "//span[contains(@aria-labelledby,'rating_Comfort')]//span[contains(@class,'star-rating-control')]/span/a[@id]")
    private List<BaseWebElement> comfortRating;

    @FindBy(xpath = "//span[contains(@aria-labelledby,'rating_Width')]//span[contains(@class,'star-rating-control')]/span/a[@id]")
    private List<BaseWebElement> widthRating;

    @FindBy(xpath = "//span[contains(@aria-labelledby,'rating_Size')]//span[contains(@class,'star-rating-control')]/span/a[@id]")
    private List<BaseWebElement> sizeRating;

    @FindBy(xpath = "//span[contains(@class,'rating-helper-1')]")
    private BaseWebElement overallRateViewer;

    @FindBy(xpath = "//span[contains(@class,'Comfort_2-helper-1')]")
    private BaseWebElement comfortViewer;

    @FindBy(xpath = "//span[contains(@class,'Width-helper-1')]")
    private BaseWebElement widthViewer;

    @FindBy(xpath = "//span[contains(@class,'Size_1-helper-1')]")
    private BaseWebElement sizeViewer;

    @FindBy(className = "bv-mbox-close")
    private BaseWebElement closePopupButton;

    @FindBy(xpath = "//label[@class='bv-helper-label']")
    private List<BaseWebElement> warningCharTextMessages;

    @FindBy(xpath = "//input[contains(@id,'usernickname')]")
    private WebInput userNameField;

    @FindBy(xpath = "//input[contains(@id,'userlocation')]")
    private WebInput userLocationFiled;

    @FindBy(xpath = "//input[contains(@id,'authenticationemail')]")
    private WebInput userMailFiled;

    @FindBy(xpath = "//select[contains(@id,'contextdatavalue_Age')]")
    private WebSelect ageSelection;

    @FindBy(xpath = "//select[contains(@id,'contextdatavalue_Gender')]")
    private WebSelect genderSelection;

    @FindBy(xpath = "//select[contains(@id,'contextdatavalue_Size')]")
    private WebSelect ShoeFitSelection;

    @FindBy(xpath = "//ul[contains(@class,'fieldset-tags-group')]/li")
    private List<WebCheckbox> categoryCheckboxes;

    @FindBy(xpath = "//span[contains(@class,'netpromoterscore')]//ul[@role='presentation']/li")
    private List<WebCheckbox> friendsRecomendRatingCheckbox;

    @FindBy(xpath = "//hgroup[@class='bv-submission-message']")
    private BaseWebElement reviewSendingNotification;




    public BaseWebElement getWriteReviewPopUp() {
        return writeReviewPopUp;
    }

    public BaseWebElement getReviewTitle() {
        return reviewTitle;
    }

    public WebInput getReviewTextArea() {
        return reviewTextArea;
    }

    public BaseWebElement getPostReviewButton() {
        return postReviewButton;
    }

    public WebInput getReviewTitleInput() {
        return reviewTitleInput;
    }

    public List<BaseWebElement> getOverallRating() {
        return overallRating;
    }

    public BaseWebElement getOverallRateViewer() {
        return overallRateViewer;
    }

    public BaseWebElement getClosePopupButton() {
        return closePopupButton;
    }

    public List<BaseWebElement> getWarningCharTextMessages() {
        return warningCharTextMessages;
    }

    public WebInput getUserNameField() {
        return userNameField;
    }

    public WebInput getUserLocationFiled() {
        return userLocationFiled;
    }

    public WebInput getUserMailFiled() {
        return userMailFiled;
    }

    public WebSelect getAgeSelection() {
        return ageSelection;
    }

    public WebSelect getGenderSelection() {
        return genderSelection;
    }

    public WebSelect getShoeFitSelection() {
        return ShoeFitSelection;
    }

    public List<BaseWebElement> getComfortRating() {
        return comfortRating;
    }

    public List<BaseWebElement> getWidthRating() {
        return widthRating;
    }

    public List<BaseWebElement> getSizeRating() {
        return sizeRating;
    }

    public BaseWebElement getComfortViewer() {
        return comfortViewer;
    }

    public BaseWebElement getWidthViewer() {
        return widthViewer;
    }

    public BaseWebElement getSizeViewer() {
        return sizeViewer;
    }

    public List<WebCheckbox> getCategoryCheckboxes() {
        return categoryCheckboxes;
    }

    public List<WebCheckbox> getFriendsRecomendRatingCheckbox() {
        return friendsRecomendRatingCheckbox;
    }

    public BaseWebElement getReviewSendingNotification() {
        return reviewSendingNotification;
    }



    public void selectRatingStar(List<BaseWebElement> stars, BaseWebElement viewer){
        Random random = new Random();
        int randomElementIndex = random.nextInt(stars.size()-1);
        hoverOnElement(stars.get(randomElementIndex));
        clickOnTheBaseWebElement(stars.get(randomElementIndex));
        verifyStringContains(stars.get(randomElementIndex).getAttribute(TITLE), viewer.getTrimText() , "current rating viewer");
    }

    public boolean verifyCharTextWarningMessage(List<BaseWebElement> labels){
        boolean status = false;
        for (BaseWebElement label: labels){
          if (label.getTrimText().contains(CHARS_TOO_SHORT)){
              status = true;
              log("Chars error warning is present");
              break;
          }
        }
        return status;
    }

    public void selectProductCategory(List<WebCheckbox> productCategories){
        for (WebCheckbox paramCheckbox: productCategories){
            if (paramCheckbox.getAttribute(ARIA_CHECKED).contains(FALSE)){
                paramCheckbox.setName(paramCheckbox.getTrimText());
                log("Selected parameter is: " + paramCheckbox.getName());
                paramCheckbox.check();
                break;
            }
        }
    }

    public void selectRandomRecomendRate(List<WebCheckbox> rates){
            Random random = new Random();
        for (int i = 0; i < rates.size() - 1; i++) {
            if(!rates.get(random.nextInt(rates.size()-1)).getAttribute(CLASS).contains(ACTIVE)){
                log("Selected parameter is: " + rates.get(random.nextInt(rates.size()-1)).getTrimText());
                rates.get(random.nextInt(rates.size()-1)).check();
                break;
            }
        }
    }


}
