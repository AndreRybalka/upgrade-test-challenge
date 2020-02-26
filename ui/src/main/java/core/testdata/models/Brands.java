package core.testdata.models;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

import static core.utils.ConfigVariables.*;

public abstract class Brands {
    private static final String SIZE_AGACI = "A'gaci";

    public abstract String getRandomProductWithSize();

    @Profile(CA)
    @Component
    public static class ProductsCA extends Brands {

        private enum ProductsWithSize {
            AGACI {
                public String getProductName() {
                    return SIZE_AGACI;
                }
            };

            public abstract String getProductName();
        }

        @Override
        public String getRandomProductWithSize() {
            return ProductsWithSize.values()[new Random().nextInt(ProductsWithSize.values().length)].getProductName();
        }

    }

}
