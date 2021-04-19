#!/bin/sh

#  upload-dsym-to-firebase.sh
#  itspaid
#
#  Created by Roxana Coste on 13/12/2018.
#  Copyright © 2018 Cobalt Sign SRL. All rights reserved.

#  Both CONFIGURATION and PATH_TO_GOOGLE_PLISTS should be defined in project as User-Defined variables

case "${CONFIGURATION}" in

"UAT UP (Debug)" | "UAT UP (Release)" )
cp -r "${PATH_TO_GOOGLE_PLISTS}/GoogleService-Info-uat-up.plist" "${BUILT_PRODUCTS_DIR}/${PRODUCT_NAME}.app/GoogleService-Info.plist" ;;

"UAT CS (Debug)" | "UAT CS (Release)" )
cp -r "${PATH_TO_GOOGLE_PLISTS}/GoogleService-Info-uat-cs.plist" "${BUILT_PRODUCTS_DIR}/${PRODUCT_NAME}.app/GoogleService-Info.plist" ;;

"PROD UP (Debug)" | "PROD UP (Release)" )
cp -r "${PATH_TO_GOOGLE_PLISTS}/GoogleService-Info-prod-up.plist" "${BUILT_PRODUCTS_DIR}/${PRODUCT_NAME}.app/GoogleService-Info.plist" ;;

"PROD CS (Debug)" | "PROD CS (Release)" )
cp -r "${PATH_TO_GOOGLE_PLISTS}/GoogleService-Info-prod-cs.plist" "${BUILT_PRODUCTS_DIR}/${PRODUCT_NAME}.app/GoogleService-Info.plist" ;;

*)
;;
esac
