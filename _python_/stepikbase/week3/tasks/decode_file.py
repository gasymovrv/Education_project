import stepikbase.week3.my_module as mm

with open("../test_files/encoded.txt", "r") as inf:
    with open("../test_files/decoded.txt", "w") as ouf:
        for line in inf:
            ouf.write(mm.decode_duplicates(line.strip()))

# os.remove("../test_files/decoded.txt")
