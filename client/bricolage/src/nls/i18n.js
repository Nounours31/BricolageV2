// ---------------------------------------------------------
// Voir https://www.i18next.com/
// ---------------------------------------------------------
import i18n from "i18next";
import { initReactI18next } from "react-i18next";
 
import { TRANSLATIONS_FR } from "./lang_fr.js";
import { TRANSLATIONS_TST } from "./lang_tst.js";
 
i18n
 .use(initReactI18next)
 .init({
   resources: {
    fr: {
        translation: TRANSLATIONS_FR
      },
      tst: {
        translation: TRANSLATIONS_TST
      }
     }
 });
 
i18n.changeLanguage("fr");