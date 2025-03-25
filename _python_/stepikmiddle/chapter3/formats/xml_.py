from xml.etree import ElementTree

tree = ElementTree.parse("test_files/example.xml")
root = tree.getroot()
tree.write("test_files/example_copy.xml")  # write copy to file

print("======================= Reading from xml file =================================")
print(root)
print(root.tag, root.attrib)
print(root[0][0].text)
for child in root:
    print(child.tag, child.attrib)

for element in root.iter("scores"):  # .findall for children
    score_sum = 0
    for child in element:
        score_sum += float(child.text)
    print(score_sum)  # sum of scores of each student


print("======================= Modifying xml file =================================")
tree = ElementTree.parse("test_files/example_modified.xml")
root = tree.getroot()

print("===== Modifying content of tag =====")
greg = root[0]
module1 = next(greg.iter("module1"))
print(module1, module1.text)
module1.text = str(float(module1.text) + 30)

print("===== Set new attribute of tag =====")
certificate = greg[2]
certificate.set("type", "with distinction")

print("===== Creating new tag =====")
description = ElementTree.Element("description")
description.text = "Showed excellent skills during the course"
greg.append(description)

print("===== Removing tag =====")
description = greg.find("description")
greg.remove(description)

tree.write("test_files/example_modified.xml")
print(ElementTree.ElementTree.__doc__)


print("======================= Creating xml object and writing to file =================================")
root = ElementTree.Element("student")

first_name = ElementTree.SubElement(root, "firstName")
first_name.text = "Greg"

second_name = ElementTree.SubElement(root, "secondName")
second_name.text = "Dean"

scores = ElementTree.SubElement(root, "scores")

module1 = ElementTree.SubElement(scores, "module1")
module1.text = "100"

module2 = ElementTree.SubElement(scores, "module2")
module2.text = "80"

module3 = ElementTree.SubElement(scores, "module3")
module3.text = "90"

tree = ElementTree.ElementTree(root)

# Automatic indentation (Python 3.9+)
ElementTree.indent(tree, space="    ", level=0)

tree.write("test_files/student.xml", xml_declaration=True, encoding="utf-8")
