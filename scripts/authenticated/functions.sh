#!/bin/bash

# This script provides functions to interact with an Oracle WebCenter Sites (OWCS) instance
X_CSRF_TOKEN="X-CSRF-TOKEN"

# Function to request a ticket from the OWCS instance
# Usage: requestTicket <baseUrl> <service> <username> <password>
requestTicket() {
    local baseUrl="$1"
    local service="$2"
    local username="$3"
    local password="$4"
    if [[ -z "$baseUrl" || -z "$service" || -z "$username" || -z "$password" ]]; then
        echo "Request Ticket Error: All parameters (baseUrl, service, username, password) are required."
        return 1
    fi
    local formBody=$(printf "pagename=%s&username=%s&password=%s&action=ticket&service=%s" \
        "fatwire/wem/sso/processticket" "$username" "$password" "$service")
    local requestUrl="${baseUrl}/Satellite"
    local response=$(curl -s -X POST "$requestUrl" \
        -H "PRAGMA: auth-redirect=false" \
        -d "$formBody")
    if [[ $? -ne 0 ]]; then
        echo "Request Ticket Error: Failed to get ticket for service ($service)."
        return 1
    fi
    if [[ -z "$response" ]]; then
        echo "Request Ticket Error: Failed to get ticket: 0 byte response. Please check username and password."
        return 1
    fi
    # Remove leading and trailing whitespace
    response=${response//[[:space:]]/}
    echo "$response"
}

# Function to get a ticket for a specific service
# Usage: getTicket <baseUrl> <service> <username> <password>
getTicket() {
    local baseUrl="$1"
    local service="$2"
    local username="$3"
    local password="$4"
    requestTicket "$baseUrl" "$service" "$username" "$password"
}

# Function to get a multi-ticket for all services
# Usage: getMultiTicket <baseUrl> <username> <password>
getMultiTicket() {
    local baseUrl="$1"
    local username="$2"
    local password="$3"

    requestTicket "$baseUrl" "*" "$username" "$password"
}

# Function to encrypt a ticket
# Usage: encryptTicket <baseUrl> <ticket>
encryptTicket() {
    local baseUrl="$1"
    local ticket="$2"
    if [[ -z "$baseUrl" || -z "$ticket" ]]; then
        echo "Encrypt Ticket Error: Both baseUrl and ticket are required."
        return 1
    fi

    local formBody=$(printf "multiticket=%s" "$ticket")
    local requestUrl="${baseUrl}/wem/service/encrypttoken"
    local response=$(curl -s -X POST "$requestUrl" \
        -H "PRAGMA: auth-redirect=false" \
        -d "$formBody")

    if [[ $? -ne 0 ]]; then
        echo "Encrypt Ticket Error: Failed to encrypt ticket."
        return 1
    fi

    if [[ -z "$response" ]]; then
        echo "Encrypt Ticket Error: Failed to encrypt ticket: 0 byte response."
        return 1
    fi

    # Remove leading and trailing whitespace
    response=${response//[[:space:]]/}
    echo "$response"
}

# Function to get a token for the OWCS instance
# Usage: getToken <baseUrl> <username> <password>
getToken() {
    local baseUrl="$1"
    local username="$2"
    local password="$3"

    local ticket=$(getMultiTicket "$baseUrl" "$username" "$password")
    if [[ $? -ne 0 ]]; then
        echo "Get Token Error: Failed to get ticket."
        return 1
    fi
    encryptTicket "$baseUrl" "$ticket"|jq -r '.encryptedtoken'
}

get() {
    local url="$1"
    local token="$2"
    if [[ -z "$url" || -z "$token" ]]; then
        echo "Get Error: URL and token are required."
        return 1
    fi
    curl -s -X GET "$url" \
        -H "${X_CSRF_TOKEN}: $token" \
        -H "Accept: application/json"
}

post() {
    local url="$1"
    local data="$2"
    local token="$3"
    if [[ -z "$url" || -z "$data" || -z "$token" ]]; then
        echo "Post Error: URL, data, and token are required."
        return 1
    fi
    curl -s -X POST "$url" \
        -H "${X_CSRF_TOKEN}: $token" \
        -H "Content-Type: application/json" \
        -d "$data"
}

put() {
    local url="$1"
    local data="$2"
    local token="$3"
    if [[ -z "$url" || -z "$data" || -z "$token" ]]; then
        echo "Put Error: URL, data, and token are required."
        return 1
    fi
    curl -s -X PUT "$url" \
        -H "${X_CSRF_TOKEN}: $token" \
        -H "Content-Type: application/json" \
        -d "$data"
}

head() {
    local url="$1"
    local token="$2"
    if [[ -z "$url" || -z "$token" ]]; then
        echo "Head Error: URL and token are required."
        return 1
    fi
    curl -s -I "$url" \
        -H "${X_CSRF_TOKEN}: $token"
}

delete() {
    local url="$1"
    local token="$2"
    if [[ -z "$url" || -z "$token" ]]; then
        echo "Delete Error: URL and token are required."
        return 1
    fi
    curl -s -X DELETE "$url" \
        -H "${X_CSRF_TOKEN}: $token"
}

baseUrl() {
    local host="$1"
    local port="$2"
    local context="${3:-/sites}"
    if [[ -z "$host" || -z "$port" ]]; then
        echo "Base URL Error: Host and port are required."
        return 1
    fi
    echo "http://${host}:${port}${context}"
}