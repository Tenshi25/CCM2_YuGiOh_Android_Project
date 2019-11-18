package master.ccm.mappers;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import master.ccm.entity.Card;
import master.ccm.entity.Effect;
import master.ccm.types.AttributeType;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;
import master.ccm.types.CategorizedType;

public class MapperCard {

    public Card mapDocumentToCard(DocumentSnapshot document) {
        Card card = new Card();

        if (document.contains("reference")) {
            card.setReference(document.getString("reference"));
        }

        if (document.contains("name")) {
            card.setName(document.getString("name"));
        }

        if (document.contains("cardType")) {
            card.setCardType(CardType.valueOf(document.getString("cardType")));
        }

        if (document.contains("cardSubType")) {
            card.setCardSubType(CardTypeSub.valueOf(document.getString("cardSubType")));
        }

        if (document.contains("categorized")) {
            card.setCategorized(CategorizedType.valueOf(document.getString("categorized")));
        }

        if (document.contains("attribute")) {
            card.setAttribute(AttributeType.valueOf(document.getString("attribute")));
        }

        if (document.contains("description")) {
            card.setDescription(document.getString("description"));
        }

        if (document.contains("imageUrl")) {
            card.setImageUrl(document.getString("imageUrl"));
        }

        if (document.contains("atk")) {
            card.setAtk(document.getLong("atk").intValue());
        }

        if (document.contains("def")) {
            card.setDef(document.getLong("def").intValue());
        }

        if (document.contains("level")) {
            card.setLevel(document.getLong("level").intValue());
        }

        if (document.contains("effects")) {
            card.setEffects((List<Effect>) document.get("effects"));
        }

        if (document.contains("limit")) {
            card.setLimit(document.getLong("limit").intValue());
        }

        return card;
    }
}
