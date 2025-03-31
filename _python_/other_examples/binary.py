
print("\nbitwise operations")
# Bitwise AND (&)
a = 5  # (0b0101)
b = 3  # (0b0011)
result = a & b  # (0b0001)
print(bin(result))  # Output: 0b1
print(result)  # Output: 1

# Bitwise OR (|)
a = 5  # (0b0101)
b = 3  # (0b0011)
result = a | b  # (0b0111)
print(bin(result))  # Output: 0b111
print(result)  # Output: 7

# Bitwise XOR (^)
a = 5  # (0b0101)
b = 3  # (0b0011)
result = a ^ b  # (0b0110)
print(bin(result))  # Output: 0b110
print(result)  # Output: 6

# Bitwise NOT (~)
a = 5  # (0b0101)
result = ~a  # (-0b0110)
print(bin(result))  # Output: -0b110
print(result)  # Output: -6

a = 5  # (0b0101)
result = a << 2  # (0b10100)
print(bin(result))  # Output: 0b10100
print(result)  # Output: 20

a = 5  # (0b0101)
result = a >> 1  # (0b0010)
print(bin(result))  # Output: 0b10
print(result)  # Output: 2


print("\nformatted binary")
num1 = 0b1010
num2 = 0b1111
binary_sum = num1 + num2
print(f"{num1:08b}")
print(f"{num2:08b}")
print(f"{binary_sum:08b}")
print(bin(binary_sum))


def sum_binary(a, b):
    return bin(int(a, 2) + int(b, 2))[2:]

def sum_binary2(a, b):
    return f"{(int(a, 2) + int(b, 2)):b}"


print("\nsum_binary")
print(sum_binary("1010", "1111"))
print(sum_binary("11", "1"))
print(sum_binary2("1010", "1111"))
print(sum_binary2("11", "1"))
