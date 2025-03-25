import re

import requests


def print_links(html):
    pattern = re.compile(r'<a[^>]*?href=["\'](?:https?://|ftp://)?([^.][^:/\s"\'<>]+)')
    links = re.findall(pattern, html)

    res = set()
    for link in links:
        res.add(link)
    list_ = sorted(res)

    for link in list_:
        print(link)

# Input from file:
# with open("test_files/input.html") as f:
#     html = "".join(f.readlines())
#     print_links(html)

# Input from link from stdin:
# print_links(requests.get(input().strip()).text)

print_links(requests.get("https://pastebin.com/raw/7543p0ns").text)


# Sample Input 1:
# <a href="http://stepik.org/courses">
# <a href='https://stepik.org'>
# <a href='http://neerc.ifmo.ru:1345'>
# <a href="ftp://mail.ru/distib" >
# <a href="ya.ru">
# <a href="www.ya.ru">
# <a href="../skip_relative_links">

# Sample Output 1:
# mail.ru
# neerc.ifmo.ru
# stepik.org
# www.ya.ru
# ya.ru
