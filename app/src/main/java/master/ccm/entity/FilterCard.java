package master.ccm.entity;

import master.ccm.types.AttributeType;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;
import master.ccm.types.CategorizedType;

public class FilterCard {
    private String reference;

    private String name;

    private CardType cardType;

    private CardTypeSub cardSubType;

    private CategorizedType categorized;

    private AttributeType attribute;

    private String description;

    private int atk;

    private int def;

    private int level;
}
