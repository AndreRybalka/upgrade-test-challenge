package constants.enums;


import core.testdata.models.preparedData.Subcategory;

public enum Subcategories {

    SHOES {
        public Subcategory getSubcategory() {
            return SHOES_SUBCATEGORY;
        }
    },
    CASUAL_SHOES {
        public Subcategory getSubcategory() {
            return CASUAL_SHOES_SUBCATEGORY;
        }
    },
    MENS_SALE {
        public Subcategory getSubcategory() {
            return MENS_SALE_SUBCATEGORY;
        }
    },
    WOMENS_SALE {
        public Subcategory getSubcategory() {
            return WOMENS_SALE_SUBCATEGORY;
        }
    },
    BAGS_WALLETS {
        public Subcategory getSubcategory() {
            return BAGS_WALLETS_SUBCATEGORY;
        }
    },
    HANDBAGS {
        public Subcategory getSubcategory() {
            return HANDBAGS_SUBCATEGORY;
        }
    },
    FOOTWEAR {
        public Subcategory getSubcategory() {
            return FOOTWEAR_SUBCATEGORY;
        }
    },
    SANDALS {
        public Subcategory getSubcategory() {
            return SANDALS_SUBCATEGORY;
        }
    },
    HEELS {
        public Subcategory getSubcategory() {
            return HEELS_SUBCATEGORY;
        }
    },
    FLATS {
        public Subcategory getSubcategory() {
            return FLATS_SUBCATEGORY;
        }
    },
    SHOE_CARE {
        public Subcategory getSubcategory() {
            return SHOE_CARE_SUBCATEGORY;
        }
    },
    BACKPACKS {
        public Subcategory getSubcategory() {
            return BACKPACKS_SUBCATEGORY;
        }
    },
    WALLETS {
        public Subcategory getSubcategory() {
            return WALLETS_SUBCATEGORY;
        }
    },
    CHOKERS {
        public Subcategory getSubcategory() {
            return CHOKERS_SUBCATEGORY;
        }
    },
    BAGS_AND_BACKPACKS {
        public Subcategory getSubcategory() {
            return BAGS_AND_BACKPACKS_SUBCATEGORY;
        }
    },
    BOOTS {
        public Subcategory getSubcategory() {
            return BOOTS_SUBCATEGORY;
        }
    },
    MEN_SUBCATEGORY_LABEL {
        public Subcategory getSubcategory() {
            return MEN_SUBCATEGORY;
        }
    },
    WOMEN_SUBCATEGORY_LABEL {
        public Subcategory getSubcategory() {
            return WOMEN_SUBCATEGORY;
        }
    },
    BAGS {
        public Subcategory getSubcategory() {
            return BAGS_SUBCATEGORY;
        }
    },
    WOMAN_S_SUBCATEGORY_LABEL {
        public Subcategory getSubcategory() {
            return WOMEN_S_SUBCATEGORY;
        }
    },
    MAN_S_SUBCATEGORY_LABEL {
        public Subcategory getSubcategory() {
            return MEN_S_SUBCATEGORY;
        }
    },
   CLUTCHES {
        public Subcategory getSubcategory() {
            return CLUTCHES_SUBCATEGORY;
        }
    },

    HATS_SCARVES_GLOVERS {
        public Subcategory getSubcategory() {
            return HATS_SCARVES_GLOVERS_SUBCATEGORY;
        }
    },
    CROSSBODY {
        public Subcategory getSubcategory() {
            return CROSSBODY_SUBCATEGORY;
        }
    };

    private final static Subcategory CASUAL_SHOES_SUBCATEGORY = new Subcategory("Casual Shoes", true);
    private final static Subcategory HATS_SCARVES_GLOVERS_SUBCATEGORY = new Subcategory("Hats, Scarves & Gloves", true);
    private final static Subcategory CROSSBODY_SUBCATEGORY = new Subcategory("Crossbody", true);
    private final static Subcategory CLUTCHES_SUBCATEGORY = new Subcategory("Clutches", true);
    private final static Subcategory HANDBAGS_SUBCATEGORY = new Subcategory("Handbags", true);
    private final static Subcategory FOOTWEAR_SUBCATEGORY = new Subcategory("Footwear", true);
    private final static Subcategory SHOES_SUBCATEGORY = new Subcategory("Shoes", true);
    private final static Subcategory HEELS_SUBCATEGORY = new Subcategory("Heels", true);
    private final static Subcategory FLATS_SUBCATEGORY = new Subcategory("Flats", true);
    private final static Subcategory MENS_SALE_SUBCATEGORY = new Subcategory("Men's Sale", true);
    private final static Subcategory WOMENS_SALE_SUBCATEGORY = new Subcategory("Women's Sale", true);
    private final static Subcategory BAGS_WALLETS_SUBCATEGORY = new Subcategory("Bags & Wallets", true);
    private final static Subcategory SANDALS_SUBCATEGORY = new Subcategory("Sandals", true);
    private final static Subcategory SHOE_CARE_SUBCATEGORY = new Subcategory("Shoe Care", true);
    private final static Subcategory BACKPACKS_SUBCATEGORY = new Subcategory("Backpacks", true);
    private final static Subcategory WALLETS_SUBCATEGORY = new Subcategory("Wallets", true);
    private final static Subcategory CHOKERS_SUBCATEGORY = new Subcategory("Chokers", true);
    private final static Subcategory BAGS_AND_BACKPACKS_SUBCATEGORY = new Subcategory("Bags & Backpacks", true);
    private final static Subcategory BOOTS_SUBCATEGORY = new Subcategory("Boots", true);
    private final static Subcategory MEN_SUBCATEGORY = new Subcategory("Men", true);
    private final static Subcategory MEN_S_SUBCATEGORY = new Subcategory("Men's", true);
    private final static Subcategory WOMEN_SUBCATEGORY = new Subcategory("Women", true);
    private final static Subcategory WOMEN_S_SUBCATEGORY = new Subcategory("Women's", true);
    private final static Subcategory BAGS_SUBCATEGORY = new Subcategory("Bags", true);

    public abstract Subcategory getSubcategory();

}
