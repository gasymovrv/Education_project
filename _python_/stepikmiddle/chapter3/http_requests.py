import requests

res = requests.get("https://yandex.ru/search/",
                   params={
                       "text": "Stepic",
                       "test": "test1",
                       "name": "Name With Spaces",
                       "list": ["test1", "test2"]
                   })
print(res.status_code)
print(res.headers["Content-Type"])
print(res.url)
print(res.content)  # bytes
print(res.text)  # str


# res = requests.get("https://www.python.org/static/img/python-logo.png")
# print(res.content)
#
# with open("python.png", "wb") as f:
#     f.write(res.content)

# print(res.text)

