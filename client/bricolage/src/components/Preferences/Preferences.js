import React from 'react';
import { useTranslation } from "react-i18next";
import "../../nls/i18n";

const Preferences = function () {
    const { t } = useTranslation();
    
    return (
        <div className="Preferences">
            <h1>{t('_nomComponant', { val: 'Preferences'})}</h1>
        </div>
        );
    }
    
export default Preferences;