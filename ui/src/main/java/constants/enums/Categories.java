package constants.enums;

import core.testdata.models.preparedData.Category;

import static constants.Constants.*;


public enum Categories {

    MEN_CATEGORY {
        public Category getCategory() {
            return BANNER_MEN_CATEGORY;
        }
    },
    WOMEN_CATEGORY {
        public Category getCategory() {
            return BANNER_WOMEN_CATEGORY;
        }
    },
    HANDBAGS_CATEGORY {
        public Category getCategory() {
            return BANNER_HAND_BAGS_CATEGORY;
        }
    },
    ACCESSORIES_CATEGORY {
        public Category getCategory() {
            return BANNER_ACCESSORIES_CATEGORY;
        }
    },
    SALE_CATEGORY {
        public Category getCategory() {
            return BANNER_SALE_CATEGORY;
        }
    };

    private final static Category BANNER_MEN_CATEGORY = new Category(MEN);
    private final static Category BANNER_WOMEN_CATEGORY = new Category(WOMEN);
    private final static Category BANNER_HAND_BAGS_CATEGORY = new Category(HANDBAGS);
    private final static Category BANNER_ACCESSORIES_CATEGORY = new Category(ACCESSORIES);
    private final static Category BANNER_SALE_CATEGORY = new Category(SALE);

    public abstract Category getCategory();
}
