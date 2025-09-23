#!/bin/bash

VALID_ARGS=$(getopt -o u:s:t:i: --long url:,site:,type:,delta: -- "$@")
if [[ $? -ne 0 ]]; then
    exit 1;
fi

eval set -- "$VALID_ARGS"
while [ : ]; do
  case "$1" in
    -u | --url)
	URL="$2"
        shift 2
        ;;
    -s | --site)
	SITE="$2"
        shift 2
        ;;
    -t | --type)
	TYPE="$2"
        shift 2
        ;;
    -i | --id)
	ID="$2"
        shift 2
        ;;
    --) shift; 
        break 
        ;;
  esac
done

curl -H "Accept: application/json" -X GET "${URL}/sites/REST/resources/v1/aggregates/${SITE}/${TYPE}/${ID}"
