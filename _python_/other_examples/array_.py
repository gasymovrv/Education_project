import array
import sys

print("===================== Integer =====================")
lst = [i for i in range(100000)]  # Python list
arr = array.array('i', [i for i in range(100000)])  # Integer array

print(sys.getsizeof(lst))  # Larger memory footprint
print(sys.getsizeof(arr))  # Smaller memory footprint


print("\n===================== Bytes =====================")
lst = [0] * 1_000_000  # List of 1 million zeros
arr = array.array('B', [0] * 1_000_000)  # Array of 1 million zeros

print(sys.getsizeof(lst))  # ~8,000,000 bytes (8MB)
print(sys.getsizeof(arr))  # ~1,000,000 bytes (1MB)


print("\n===================== Bytes conversions =====================")
arr = array.array('B', [65, 66, 67])  # ASCII values for 'A', 'B', 'C'
byte_data = arr.tobytes()  # Convert array to bytes
print(byte_data)  # Output: b'ABC'

# Convert bytes back to an array
arr2 = array.array('B')
arr2.frombytes(byte_data)
print(arr2)  # Output: array('B', [65, 66, 67])

print("\n===================== Bytes to file =====================")
arr = array.array('B', [65, 66, 67])

with open("test_files/data.bin", "wb") as f:
    arr.tofile(f)  # Store as binary
    print("stored:", arr)

print("\n===================== Bytes from file =====================")
arr2 = array.array('B')

with open("test_files/data.bin", "rb") as f:
    arr2.frombytes(f.read(5))  # Read 5 elements

print(arr2.tobytes())
