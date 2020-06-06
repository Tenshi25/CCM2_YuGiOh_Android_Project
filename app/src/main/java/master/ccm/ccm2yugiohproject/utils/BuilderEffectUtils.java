package master.ccm.ccm2yugiohproject.utils;

import java.util.ArrayList;
import java.util.List;

import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.entity.Card;
import master.ccm.entity.Effects.DestroyAllMagiesPieges;
import master.ccm.entity.Effects.DestroyAllMonsters;
import master.ccm.entity.Effects.DestroyBetterAtk;
import master.ccm.entity.Effects.DestroyBetterDef;
import master.ccm.entity.Effects.DestroyWorstAtk;
import master.ccm.entity.Effects.DestroyWorstDef;
import master.ccm.entity.Effects.EffectCard;
import master.ccm.entity.Effects.EffectGainPertePV;
import master.ccm.entity.Effects.EffectPioche;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.effect.Effect;
import master.ccm.entity.subcard.effect.FilterCard;

public class BuilderEffectUtils {

    public EffectCard createEffect(Effect effect) {
        String typeEffect = effect.getAction().toString();
        EffectCard effectCard = null;

        switch (typeEffect) {
            case "GAGNER_PV":
                effectCard = new EffectGainPertePV();
                break;
            case "INFLIGER_PV":
                effectCard = new EffectGainPertePV();
                break;
            case "PIOCHER_CARTE":
                effectCard = new EffectPioche();
                break;
            case "DETRUIRE_CARTE":
                if (effect.getSubAction() != null) {
                    switch (effect.getSubAction()) {
                        case "MAGIES_PIEGES":
                            effectCard = new DestroyAllMagiesPieges();
                            break;
                        case "MONSTRES":
                            effectCard = new DestroyAllMonsters();
                            break;
                        case "MEILLEUR_DEF":
                            effectCard = new DestroyBetterDef();
                            break;
                        case "MEILLEUR_ATK":
                            effectCard = new DestroyBetterAtk();
                            break;
                        case "PIRE_DEF":
                            effectCard = new DestroyWorstDef();
                            break;
                        case "PIRE_ATK":
                            effectCard = new DestroyWorstAtk();
                            break;
                            default:
                                break;
                    }
                }
                break;
            default:
                break;
        }

        return effectCard;
    }

    public int knowQuota(Effect effect) {
        Integer value = Integer.valueOf(effect.getQuota());

        if (effect.getAction().toString().equals("INFLIGER_PV")){
            value = value * (-1);
        }

        return value;
    }

    public int knowJoueurCible(Effect effect, Player ownerCard) {
        if (ownerCard.getName().equals(Game_activity.myContext.getAllPLayers().get(0).getName())) {

            if (effect.getTarget().equals("Me")) {
                return 0;
            } else {
                if (effect.getTarget().equals("Ennemy")) {
                    return 1;
                } else {
                    return 2;
                }
            }
        } else {
            if (effect.getTarget().equals("Me")) {
                return 1;
            } else {
                if (effect.getTarget().equals("Ennemy")) {
                    return 0;
                } else {
                    return 2;
                }
            }
        }
    }

    public ArrayList<CardInGame> knowFilterCard(Effect effect, List<Player> playerList) {
        if (effect.getFilter() != null) {
            FilterCard filter = effect.getFilter();

            Card card = new Card();
            if (filter.getName() != null) {
                card.setName(filter.getName());
            }
            if (filter.getDescription() != null) {
                card.setDescription(filter.getDescription());
            }
            if (filter.getReference() != null) {
                card.setReference(filter.getReference());
            }
            if (filter.getAttribute() != null) {
                card.setAttribut(filter.getAttribute());
            }
            if (filter.getCardSubType() != null) {
                card.setCardSubType(filter.getCardSubType());
            }
            if (filter.getCardType() != null) {
                card.setCardType(filter.getCardType());
            }
            if (filter.getCategorized() != null) {
                card.setCategorized(filter.getCategorized());
            }

            card.setAtk(filter.getAtk());
            card.setDef(filter.getDef());
            card.setLevel(filter.getLevel());

            ArrayList<CardInGame> cardInGames = new ArrayList<>();
            cardInGames.add(card.transformToCardInGame(playerList.get(0)));

            return cardInGames;
        } else {
            return null;
        }
    }

    public PileCarte knowPileA(Effect effect) {

        String typeEffect = effect.getAction().toString();
        PileCarte pileCarte = null;

        switch (typeEffect) {
            case "GAGNER_PV":
                break;
            case "PIOCHER_CARTE":
                break;
            case "INFLIGER_PV":
                break;
            default:
                break;
        }

        return pileCarte;
    }

    public PileCarte knowPileB(Effect effect) {
        String typeEffect = effect.getAction().toString();
        PileCarte pileCarte = null;

        switch (typeEffect) {
            case "GAGNER_PV":
                break;
            case "INFLIGER_PV":
                break;
            case "PIOCHER_CARTE":
                break;
            default:
                break;
        }

        return pileCarte;
    }
}
