/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;

import java.util.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class ActionSimple extends Action {

    // attribut lien
    private Map<Jour, Cours> mapCours;

    // constructeur
    public ActionSimple(String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap();
    }

    // enrg possible si pas de cours pour ce jour
    public void enrgCours(Jour j, float v) {
        if (this.mapCours.containsKey(j) == false)
            this.mapCours.put(j, new Cours(j, v));
    }

    @Override
    public float valeur(Jour j) {
        if (this.mapCours.containsKey(j) == true)
            return this.mapCours.get(j).getValeur();
        else
            return 0; // definition d'une constante possible
    }

    /*
     * L'objectif est de récupérer la première valeur d'une action simple.
     * On commence par récupérer l'année de l'action la plus ancienne,
     * ensuite on va récupérer pour cette année l'action la plus ancienne
     * avec sa valeur que l'on va retourner.
     * 
     */
    public float getFirstValue() {

        Calendar c = Calendar.getInstance();
        int premiereAnnee = c.get(Calendar.YEAR);
        int premierJour = 366;
        float valeurAction = 0;
        for (Map.Entry<Jour, Cours> entry : mapCours.entrySet()) {
            if (entry.getKey().getAnnee() < premiereAnnee) {
                premiereAnnee = entry.getKey().getAnnee();
            }
        }
        for (Map.Entry<Jour, Cours> entry : mapCours.entrySet()) {
            if (entry.getKey().getAnnee() == premiereAnnee) {
                if (entry.getKey().getNoJour() < premierJour) {
                    premierJour = entry.getKey().getNoJour();
                    valeurAction = entry.getValue().getValeur();
                }
            }
        }
        return valeurAction;
    }

    /*
     * L'objectif est de récupérer la dernière valeur d'une action simple.
     * On commence par récupérer l'année de l'action la plus récente,
     * ensuite on va récupérer pour cette année l'action la plus récente
     * avec sa valeur que l'on va retourner.
     * 
     */
    public float getLastValue() {
        Calendar c = Calendar.getInstance();
        int derniereAnnee = 0;
        int dernierJour = 0;
        float valeurAction = 0;
        for (Map.Entry<Jour, Cours> entry : mapCours.entrySet()) {
            if (entry.getKey().getAnnee() > derniereAnnee) {
                derniereAnnee = entry.getKey().getAnnee();
            }
        }
        for (Map.Entry<Jour, Cours> entry : mapCours.entrySet()) {
            if (entry.getKey().getAnnee() == derniereAnnee) {
                if (entry.getKey().getNoJour() > dernierJour) {
                    dernierJour = entry.getKey().getNoJour();
                    valeurAction = entry.getValue().getValeur();
                }
            }
        }
        return valeurAction;
    }

}