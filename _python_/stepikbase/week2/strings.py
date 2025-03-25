genome = "acggtgttat"

cCount = genome.upper().count("C")
gCount = genome.upper().count("G")
percent = len(genome) / 100

print((gCount + cCount) / percent)

dna = "ATTCGGAGCT"
print("dna=", dna)
print("dna[1]=", dna[1])  # char 1
print("dna[1:4]=", dna[1:4])  # from 1 to 4
print("dna[:4]=", dna[:4])  # from 0 to 4
print("dna[4:]=", dna[4:])  # from 4 to the end
print("dna[1:-1:2]=", dna[1:-1:2])  # from 1 to -1 with step 2
print("dna[::-1]=", dna[::-1])  # reverse (step -1)
