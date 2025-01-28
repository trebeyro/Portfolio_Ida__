/*
 * Copyright 2024 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionComposeeTest {

    public ActionComposeeTest() {
    }

    @Test
    public void testActionComposee() {

        // Créer 3 actions simples et 1 action composée
        ActionSimple apple = new ActionSimple("Apple");
        ActionSimple samsung = new ActionSimple("Samsung");
        ActionSimple google = new ActionSimple("Google");

        Jour j1 = new Jour(2024, 1);
        Jour j2 = new Jour(2025, 1);

        apple.enrgCours(j1, 150);
        apple.enrgCours(j2, 200);

        samsung.enrgCours(j1, 300);
        samsung.enrgCours(j2, 250);

        google.enrgCours(j1, 100);
        google.enrgCours(j2, 120);

        ActionComposee groupe = new ActionComposee("Groupe");

        groupe.enrgComposition(apple, 0.4f);
        groupe.enrgComposition(samsung, 0.35f);
        groupe.enrgComposition(google, 0.25f);

        Assertions.assertEquals(3, groupe.mapPanier.size(),
                "Le nombre d'actions simples dans l'action composée n'est pas correct.");
        Assertions.assertEquals(0.4f, groupe.mapPanier.get(apple),
                "Le % de l'action Apple n'est pas correct.");
        Assertions.assertEquals(0.35f, groupe.mapPanier.get(samsung),
                "Le % de l'action Samsung n'est pas correct.");
        Assertions.assertEquals(0.25f, groupe.mapPanier.get(google),
                "Le % de l'action Google n'est pas correct.");

        float expectedValue = (apple.valeur(j2) * 0.4f) + (samsung.valeur(j2) * 0.35f) + (google.valeur(j2) * 0.25f);
        Assertions.assertEquals(expectedValue, groupe.valeur(j2),
                "La valeur de l'action composée est incorrecte.");

    }

}
