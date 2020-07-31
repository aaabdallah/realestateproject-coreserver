SELECT 
tpropertylistings.idk AS propertylistingidk, trentals.idk AS rentalidk,
tapartments.idk AS apartmentidk,
tpropertylistings.propertyidk, communityamenitiesidk AS communityamenityidk,
minimumrenewaltermmonths, periodmonths, rentforperiod, currencyidk,
countryidk, regionidk, cityidk, districtidk,
lotsizesquaremeters, yearbuilt, generalconditionidk,
tresidentialfeatures.unitsizesquaremeters AS runitsizesquaremeters,
bedrooms, bathrooms, furnished
FROM
tpropertylistings, trentals, tproperties, tapartments, tresidentialfeatures
WHERE 
timepublicaccessstarts <= CURRENT_DATE AND CURRENT_DATE < timepublicaccessends AND
forrentidk > 999 AND trentals.idk = forrentidk AND
tproperties.idk = tpropertylistings.propertyidk AND
tapartments.propertyidk = tpropertylistings.propertyidk AND
tresidentialfeatures.idk = tapartments.residentialfeaturesidk



