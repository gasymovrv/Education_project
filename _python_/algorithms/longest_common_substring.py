def longest_common_substring(s1: str, s2: str) -> str:
    """
    This algorithm uses dynamic programming to find the longest common substring

    Time and space complexity: O(m * n)
    Where:
    m is the length of string s1
    n is the length of string s2
    """
    if not s1 or not s2:
        return ""

    m, n = len(s1), len(s2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    max_len = 0
    end_pos = 0  # end position in s1

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i - 1] == s2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
                if dp[i][j] > max_len:
                    max_len = dp[i][j]
                    end_pos = i

    print_matrix(dp, s1, s2)
    return s1[end_pos - max_len:end_pos]


def print_matrix(dp, s1, s2):
    # Print the matrix
    print("DP Matrix:")
    print("      " + "  ".join(s2))
    for i in range(len(dp)):
        row = dp[i]
        label = s1[i - 1] if i > 0 else " "
        print(label + " " + " ".join(f"{val:2}" for val in row))


# Example usage
s1 = "abcdef"
s2 = "zabcf"
print("Longest Common Substring:", longest_common_substring(s1, s2))
