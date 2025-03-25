import requests

root = "https://stepik.org/media/attachments/course67/3.6.3"

file = requests.get(f"{root}/699991.txt")

while True:
    next_file_name = file.text.strip()
    file = requests.get(f"{root}/{next_file_name}")
    print(next_file_name)
    content = file.text
    if content.strip().lower().find("we") == 0:
        print("End!")
        with open("final.txt", "w") as ouf:
            ouf.write(content)
        break
