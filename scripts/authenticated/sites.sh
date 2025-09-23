#!/bin/bash

PORT=7003
HOST=localhost
SITE=""
USERNAME="fwadmin"
CWD=$(pwd)
PASSWORD="xceladmin"


usage() {
    echo "Usage: $0 --host host [--port <port>] [--site <site>] [--username <username>] --password <password>"
    echo "  --host     The host for the OWCS instance (optional, default: localhost)"
    echo "  --port     The port for the OWCS instance (optional, default: 7003)"
    echo "  --username The username for authentication (optional, default: fwadmin)"
    echo "  --password The password for authentication (required)"
    echo "  --site     The site name (optional)"
}

source "${CWD}/functions.sh"

OPTS=$(getopt -o "" --long host:,port:,site:,username:,password: -- "$@")
if [[ $? -ne 0 ]]; then
    usage
    exit 1;
fi

eval set -- "$OPTS"
while [[ $# -gt 0 ]]; do
  case "$1" in
    --host)
      HOST="$2"
      shift 2
      ;;
    --port)
      PORT="$2"
      shift 2
      ;;
    --site)
      SITE="$2"
      shift 2
      ;;
    --username)
      USERNAME="$2"
      shift 2
      ;;
    --password)
      PASSWORD="$2"
      shift 2
      ;;
    --) shift; break ;;
    *) echo "Unknown option: $1"; exit 1 ;;
  esac
done

if [[ -z "$PASSWORD" ]]; then
    echo "Error: --password is required."
    usage
    exit 1
fi

# Function to get a list of sites
getSites() {
    local baseurl="$1"
    local token="$2"
   
    get "${baseurl}/REST/sites" "${token}"
}

# Function to get a specific site by name
getSite() {
    local baseurl="$1"
    local site="$2"
    local token="$3"
    if [[ -z "$site" ]]; then
        echo "Error: Site name is required."
        return 1
    fi
    get "${baseurl}/REST/sites/${site}" "${token}"
}

TOKEN=$(getToken "${HOST}:${PORT}/sites" "${USERNAME}" "${PASSWORD}")
BASEURL=$(baseUrl "${HOST}" "${PORT}" "/sites")
if [[ ! -z "${SITE}" ]]; then
    getSite "${BASEURL}" "${SITE}" "${TOKEN}"
else
    getSites "${BASEURL}" "${TOKEN}"
fi



