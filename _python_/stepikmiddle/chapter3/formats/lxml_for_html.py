import requests
from lxml import etree

res = requests.get("https://docs.python.org/3/")
print(res.status_code)
print(res.headers["Content-Type"])

parser = etree.HTMLParser()
root = etree.fromstring(res.text, parser)

print("root=", root)  # <html> tag

# Find all <a> tags
for element in root.iter("a"):
    print(element, element.attrib)
