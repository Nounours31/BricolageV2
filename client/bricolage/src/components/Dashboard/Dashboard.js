import React from 'react';
import { useTranslation } from "react-i18next";
import "../../nls/i18n";

const Dashboard = function () {
    const { t } = useTranslation();
    
    return (
        <div className="dashboard">
            <h1>{t('_nomComponant', { val: 'dashboard'})}</h1>
        </div>
    );
}
    
export default Dashboard;