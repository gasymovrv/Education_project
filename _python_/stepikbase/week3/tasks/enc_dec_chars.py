dec_sample = input()
enc_sample = input()

line_to_encode = input()
line_to_decode = input()

enc_dictionary = {}
dec_dictionary = {}
for i in range(len(dec_sample)):
    enc_dictionary[dec_sample[i]] = enc_sample[i]
    dec_dictionary[enc_sample[i]] = dec_sample[i]

for ch in line_to_encode:
    if ch in enc_dictionary:
        print(enc_dictionary[ch], end="")
    else:
        print(ch, end="")
print()

for ch in line_to_decode:
    if ch in dec_dictionary:
        print(dec_dictionary[ch], end="")
    else:
        print(ch, end="")
print()
