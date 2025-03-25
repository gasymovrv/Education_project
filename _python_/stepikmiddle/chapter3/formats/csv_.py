import csv

print("csv.reader(f, delimiter=',')")
with open("test_files/example.csv") as f:
    reader = csv.reader(f, delimiter=",")
    for row in reader:
        print(row)

print("\ncsv.reader(f, delimiter='\t')")
with open("test_files/example.tsv") as f:
    reader = csv.reader(f, delimiter="\t")
    for row in reader:
        print(row)

students = [
    ["Greg", "Dean", 70, 80, 90, "Good job, Greg"],
    ["Wirt", "Wood", 80, 80.2, 80, "Nicely done"]
]

with open("test_files/example.csv", "a") as f:
    writer = csv.writer(f, quoting=csv.QUOTE_NONNUMERIC)  # QUOTE_NONNUMERIC - поставить в кавычки только строки
    writer.writerows(students)

