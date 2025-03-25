from xml.etree import ElementTree

root = ElementTree.fromstring(input())

color_values = {"red": 0, "green": 0, "blue": 0}

def count_colors(node: ElementTree.Element, colors_map: dict[str, int], level=1):
    if node.tag == "cube":
        colors_map[node.attrib["color"]] += level

    for child in node:
        if child.tag == "cube":
            count_colors(child, colors_map, level + 1)

count_colors(root, color_values)
print(color_values["red"], color_values["green"], color_values["blue"])

# Вам дано описание пирамиды из кубиков в формате XML.
# Кубики могут быть трех цветов: красный (red), зеленый (green) и синий (blue).
# Самый верхний кубик, соответствующий корню XML документа имеет ценность 1.
# Кубики, расположенные прямо под ним, имеют ценность 2.
# Кубики, расположенные прямо под нижележащими кубиками, имеют ценность 3. И т. д.

# Sample Input:
# <cube color="blue"><cube color="red"><cube color="green"></cube></cube><cube color="red"></cube></cube>

# Sample Output:
# 4 3 1
