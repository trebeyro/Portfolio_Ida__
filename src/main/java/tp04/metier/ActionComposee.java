/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class ActionComposee extends Action {
    // attribut lien
    Map<ActionSimple, Float> mapPanier;

    public ActionComposee(String libelle) {
        super(libelle);
        this.mapPanier = new HashMap();
    }

    public void enrgComposition(ActionSimple as, float pourcentage) {
        this.mapPanier.put(as, pourcentage);
    }

    @Override
    public float valeur(Jour j) {
        float valeur;

        valeur = 0;
        for (ActionSimple as : this.mapPanier.keySet()) {
            valeur = valeur + (as.valeur(j) * this.mapPanier.get(as));
        }

        return valeur;
    }

    /*
     * L'objectif ici était de parcourir les actions simples qui composent l'action
     * composée
     * et de fournir pour chacune d'elle, la performance entre le premier et le
     * dernier cours
     * pour avoir les performances individuelles de chaque action simple de cette
     * action composée.
     * 
     */
    public ArrayList<String> afficherPerformancesIndividuelles() {
        ArrayList<String> perfGroupe = new ArrayList<String>();
        float perf = 0;
        float premiereValeur, derniereValeur = 0;
        for (Map.Entry<ActionSimple, Float> entry : mapPanier.entrySet()) {
            premiereValeur = entry.getKey().getFirstValue();
            derniereValeur = entry.getKey().getLastValue();
            perf = (derniereValeur - premiereValeur) / premiereValeur * 100;
            perfGroupe.add("L'action " + entry.getKey().getLibelle() + " a réalisé une performance de " + perf + "%.");
        }
        return perfGroupe;

    }
}
