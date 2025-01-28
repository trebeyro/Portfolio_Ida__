/*
* Copyright 2024 David Navarre &lt;David.Navarre at irit.fr&gt;.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package tp04.metier;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tp04.metier.ActionSimple;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */

public class AcheterActionComposeeTest {

    public AcheterActionComposeeTest() {
    }

    @Test
    void testAcheterActionComposee() {
        // Créer une action composée et 3 actions simples
        ActionSimple bnp;
        ActionSimple axa;
        ActionSimple bitcoin;
        Portefeuille testPortefeuille = new Portefeuille();
        Jour j1, j2;
        j1 = new Jour(2024, 1);
        j2 = new Jour(2024, 2);
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bitcoin = new ActionSimple("BITCOIN");

        ActionComposee bqAss;
        bqAss = new ActionComposee("Banque-AssuranceGrpe");

        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 5000);

        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 50);

        bitcoin.enrgCours(j1, 100);
        bitcoin.enrgCours(j2, 300);

        bqAss.enrgComposition(bnp, 0.40f);
        bqAss.enrgComposition(axa, 0.35f);
        bqAss.enrgComposition(bitcoin, 0.25f);

        testPortefeuille.acheter(bqAss, 10);
        Assertions.assertTrue(testPortefeuille.containAction(bqAss),
                "L'action composée n'a pas été ajoutée au portefeuille.");
    }
}