package core.testdata.provider;

import core.testdata.models.preparedData.CreditCard;
import core.testdata.models.preparedData.User;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static core.testdata.provider.TestDataProviderHelper.getDataProviderHelper;
import static core.utils.Utils.generateMailAddress;


public class TestDataProvider {

    @DataProvider
    public static synchronized Iterator<Object[]> userDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        User user = new User(getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getUsers())) ;
        data.add(new Object[]{user});
        return data.iterator();
    }

    @DataProvider
    public static synchronized Iterator<Object[]> userStoreAndEmailDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        User user = new User(getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getUsers())) ;
        data.add(new Object[]{generateMailAddress(), user,
                getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getAddress())});
        return data.iterator();
    }

    @DataProvider
    public static synchronized Iterator<Object[]> emailAndUserAndCreditCardDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        User user = new User(getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getUsers())) ;
        CreditCard creditCard = getDataProviderHelper().getCurrentLocaleDataList(getDataProviderHelper().getCreditCards()).get(0);
            data.add(new Object[]{user, generateMailAddress(), creditCard, getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getAddress())});
        return data.iterator();
   }

    @DataProvider
    public static synchronized Iterator<Object[]> emailAndUserAndCreditCardDataProviderRegisteredUser(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        User user = new User(getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getUsers())) ;
        CreditCard creditCard = getDataProviderHelper().getCurrentLocaleDataList(getDataProviderHelper().getCreditCards()).get(0);
            data.add(new Object[]{user,creditCard, getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getAddress())});
        return data.iterator();
    }

    @DataProvider
    public static synchronized Iterator<Object[]> emailAndUserAndPayPalDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        User user = new User(getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getUsers())) ;
        data.add(new Object[]{user, generateMailAddress(),
                getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getPayPals()),
                getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getAddress())});
        return data.iterator();
    }

    @DataProvider
    public static synchronized Iterator<Object[]> visaDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        User user = new User(getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getUsers())) ;
        data.add(new Object[]{getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getVisaCheckoutAccounts()),
                user, generateMailAddress(),
                getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getAddress())});
        return data.iterator();
    }

    @DataProvider
    public static synchronized Iterator<Object[]> payPalDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getPayPals())});
        return data.iterator();
    }

    @DataProvider
    public static synchronized Iterator<Object[]> emailAndUserDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        User user = new User(getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getUsers())) ;
        data.add(new Object[]{user, generateMailAddress()});
        return data.iterator();
    }

    @DataProvider
    public static synchronized Iterator<Object[]> emailAndUserAndInteracDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        User user = new User(getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getUsers())) ;
        data.add(new Object[]{user,
                getDataProviderHelper().getCanadaInterac(), generateMailAddress(),
                getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getAddress())});
        return data.iterator();
    }

    @DataProvider
    public static synchronized Iterator<Object[]> storeDataProvider(ITestContext testContext, Method method) {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{getDataProviderHelper().getCurrentLocaleData(getDataProviderHelper().getAddress())});
        return data.iterator();
    }

}
