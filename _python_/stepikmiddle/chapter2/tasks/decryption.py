# simple-crypt deleted from repo, so just copy-paste from github
import simplecrypt

with open("test_files/encrypted.bin", "rb") as file:
    data = file.read()

with open("test_files/passwords.txt", "r") as inp:
    for line in inp:
        password = line.strip()
        try:
            print("=================================================================")
            decrypted_text = simplecrypt.decrypt(password, data)
            print(f"Decrypted content: {decrypted_text}")
            print("=================================================================")
        except Exception as e:
            print(f"Decryption failed: {e}")
