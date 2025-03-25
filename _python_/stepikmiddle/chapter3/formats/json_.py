import json

student1 = {
    "first_name": "Greg",
    "last_name": "Dean",
    "scores": [70, 80, 95],
    "description": "Good job, Greg",
    "certificate": True
}

student2 = {
    "first_name": "Wirt",
    "last_name": "Wood",
    "scores": [80, 80.2, 85],
    "description": "Nicely Done",
    "certificate": True
}

data = [student1, student2]
# Serialization from object to json string
data_json = json.dumps(data, indent=2, sort_keys=True)
print(data_json)

# Deserialization from json string to object
data_again = json.loads(data_json)
print(sum(data_again[0]["scores"]))

# Serialization from python object to file
with open("test_files/students.json", "w") as f:
    json.dump(data, f, indent=2, sort_keys=True)

# Deserialization from file to object
with open("test_files/students.json", "r") as f:
    data_again = json.load(f)
    print(sum(data_again[1]["scores"]))
