package master.ccm.entity;

import master.ccm.types.AttributeType;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;
import master.ccm.types.CategorizedType;

public class Card {
    private String reference;
    private String name;

    private CardType cardType; //monstre, magie, piège
    private CardTypeSub cardSubType; // normal,effet, fusion syncrhro, continue etc...
    private CategorizedType categorized ; // magicien/dragon/guerrier
    private AttributeType attribut; // Divin/lumière,terre
    private String Url;
    private String description;
    private int limit;



}
