import re

import requests


def has_direct_link(links, url):
    for link in links:
        # This is to fix stepic bug:
        # link = link.replace("stepic", "stepik")
        # url = url.replace("stepic", "stepik")
        if link.startswith(url):
            return True
    return False

def has_2_step_path(url1, url2):
    response = requests.get(url1)
    if response.status_code != 200:
        return "No"

    pattern = re.compile(r'<a[^>]*?href="(.*?)"[^>]*?>')
    links = re.findall(pattern, response.text)

    for link in links:
        # Exclude circular links
        if not link.startswith(url1):
            response = requests.get(link)
            nested_links = re.findall(pattern, response.text)
            if response.status_code == 200 and has_direct_link(nested_links, url2):
                return "Yes"

    return "No"


print(has_2_step_path(input(), input()))

# Sample Input 1:
# https://stepik.org/media/attachments/lesson/24472/sample0.html
# https://stepik.org/media/attachments/lesson/24472/sample2.html
# Sample Output 1: Yes

# Sample Input 2:
# https://stepik.org/media/attachments/lesson/24472/sample0.html
# https://stepik.org/media/attachments/lesson/24472/sample1.html
# Sample Output 2: No
