import json
import os
from datetime import datetime, timezone

import requests


def authorize():
    params = {
        "client_id": "e6be6382707c153622a1",
        "client_secret": "a25d6e93a2ff9afe1fc98ec1fc73b7e5"
    }
    resp = requests.post("https://api.artsy.net/api/tokens/xapp_token", params=params)

    if resp and resp.status_code // 200 == 1:
        return resp.json()
    else:
        raise Exception(f"Error: {resp.text}")


def find_artist(id: str, token: str):
    headers = {
        "X-Xapp-Token": token
    }
    resp = requests.get(f"https://api.artsy.net/api/artists/{id}", headers=headers)
    resp.encoding = 'utf-8'

    if resp and resp.status_code // 200 == 1:
        return resp.json()
    else:
        raise Exception(f"Error: {resp.text}")


def get_token():
    """
    If file test_files/artsy-token.json exists and token non expired in the file, return it.
    Otherwise, issue new token by Artsy API, save to the file and return the new token.
    """
    file_path = "test_files/artsy-token.json"

    if os.path.exists(file_path):
        with open(file_path, "r+") as token_file:
            token_info = json.load(token_file)
            expires_at = datetime.fromisoformat(token_info["expires_at"])
            current_dt = datetime.now(timezone.utc)

            if expires_at <= current_dt:
                token_info = authorize()
                token_file.seek(0)  # Reset pointer to start
                token_file.truncate()  # Clear old content
                json.dump(token_info, token_file)  # Save new token info
                token_file.flush()  # Ensure data is written immediately
                print(
                    f"Found token, but it's expired at {expires_at}, "
                    f"issued new one with expiration at {token_info["expires_at"]}"
                )
            else:
                print(f"Found non expired token, expires at {expires_at}")

            return token_info["token"]
    else:
        with open(file_path, "w") as token_file:
            token_info = authorize()
            json.dump(token_info, token_file)
            print(f"Not found token, issued new one with expiration at {token_info["expires_at"]}")
            return token_info["token"]


token = get_token()
result: list[tuple[int, str]] = []

with open("test_files/artsy-input.txt", "r", encoding="utf-8") as artists_file:
    for id_ in artists_file:
        id_ = id_.strip()
        if id_:
            artist = find_artist(id_, token)
            result.append((
                int(artist["birthday"]),
                artist["sortable_name"]
            ))

result.sort()

for a in result:
    print(a[1])
