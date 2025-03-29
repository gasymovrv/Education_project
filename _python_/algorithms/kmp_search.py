import random
import string as str_constants
import time


def simple_find(string, substring):
    """ Simple find with complexity O(n*m) where n is the length of the string and m is the length of the substring. """
    if not substring:
        return 0  # Empty substring always matches at index 0

    for i in range(len(string) - len(substring) + 1):
        j = 0

        while j < len(substring) and string[i + j] == substring[j]:
            j += 1

        if j == len(substring):
            return i  # Match found, return starting index

    return -1  # No match found


# Example usage with simple find:
print(simple_find("Hello, world!", "world"))  # Output: 7
print(simple_find("abcabcabc", "abc"))  # Output: 0
print(simple_find("aaaaab", "aab"))  # Output: 3
print(simple_find("abcdef", "xyz"))  # Output: -1
print(simple_find("abcdef", ""))  # Output: 0 (empty substring case)
print(simple_find("mississippi", "issip"))  # Output: 4


def compute_lps(substring):
    """ Computes the LPS (Longest Prefix Suffix) array for the KMP algorithm.

    How LPS Helps in Searching:
    When a mismatch occurs while searching for a substring in the main text,
    instead of starting over from the next character,
    LPS allows skipping unnecessary comparisons.

    For example, in "ABABCABAB", "ABABCA" if a mismatch occurs at index 4,
    instead of restarting from 0, we use the LPS value to skip ahead.
    """
    lps = [0] * len(substring)
    length = 0  # Length of the previous longest prefix suffix
    i = 1  # Start from the second character

    while i < len(substring):
        if substring[i] == substring[length]:
            length += 1
            lps[i] = length
            i += 1
        else:
            if length != 0:
                length = lps[length - 1]  # Go back in LPS table
            else:
                lps[i] = 0
                i += 1

    return lps


def kmp_find(string, substring):
    """
    Implements Python's find() using the Knuth-Morris-Pratt algorithm.
    Complexity O(n+m) where n is the length of the string and m is the length of the substring.
    """
    if not substring:
        return 0  # Empty substring always matches at index 0

    lps = compute_lps(substring)
    i, j = 0, 0  # Pointers for string and substring

    while i < len(string):
        if string[i] == substring[j]:
            i += 1
            j += 1
            if j == len(substring):
                return i - j  # Match found, return starting index
        else:
            if j != 0:
                j = lps[j - 1]  # Use LPS to skip redundant checks
            else:
                i += 1  # Move to the next character in string

    return -1  # No match found


# Example usage with KMP:
print()
print(kmp_find("Hello, world!", "world"))  # Output: 7
print(kmp_find("abcabcabc", "abc"))  # Output: 0
print(kmp_find("aaaaab", "aab"))  # Output: 3
print(kmp_find("abcdef", "xyz"))  # Output: -1
print(kmp_find("abcdef", ""))  # Output: 0 (empty substring case)
print(kmp_find("mississippi", "issip"))  # Output: 4


def generate_random_text(length):
    return ''.join(random.choices(str_constants.ascii_letters, k=length))


def performance_test():
    string_size = 1000000  # string size
    substring_size = 1000  # substring size
    string = generate_random_text(string_size)

    # Insert substring in the middle
    substring = string[string_size // 2: string_size // 2 + substring_size]

    # Measure simple_find search time
    start = time.time()
    simple_find(string, substring)
    simple_find_time = time.time() - start

    # Measure kmp_find search time
    start = time.time()
    kmp_find(string, substring)
    kmp_time = time.time() - start

    # Print results
    print(f"simple_find search time: {simple_find_time:.6f} seconds")
    print(f"kmp_find search time: {kmp_time:.6f} seconds")
    print(f"Speedup: {simple_find_time / kmp_time:.2f}x faster")


print()
# Run the performance test
performance_test()
