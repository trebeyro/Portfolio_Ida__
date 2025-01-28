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

public class PerformancesIndividuellesTest {

    public PerformancesIndividuellesTest() {
    }

    @Test
    public void testSomeMethod() {
    }

    @Test
    void testPerformancesIndividuelles() {
        // Créer une action composée et 3 actions simples
        ActionSimple bnp;
        ActionSimple axa;
        ActionSimple bitcoin;
        ArrayList<String> currentPerformances = new ArrayList<String>();
        ArrayList<String> expectedPerformances = new ArrayList<String>();

        Jour j1, j2;
        j1 = new Jour(2014, 1);
        j2 = new Jour(2015, 2);
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bitcoin = new ActionSimple("BITCOIN");
        ActionComposee bqAss;
        bqAss = new ActionComposee("Banque-AssuranceGrpe");
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 5000);
        bqAss.enrgComposition(bnp, 0.7f);

        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 50);
        bqAss.enrgComposition(axa, 0.7f);

        bitcoin.enrgCours(j1, 100);
        bitcoin.enrgCours(j2, 300);
        bqAss.enrgComposition(bitcoin, 0.7f);

        expectedPerformances.add("L'action BITCOIN a réalisé une performance de 200.0%.");
        expectedPerformances.add("L'action BNP a réalisé une performance de 4900.0%.");
        expectedPerformances.add("L'action AXA a réalisé une performance de -75.0%.");

        currentPerformances = bqAss.afficherPerformancesIndividuelles();
        Assertions.assertEquals(expectedPerformances, currentPerformances,
                "Méthode afficherPerformancesIndividuelles ne fonctionne pas");
    }

}