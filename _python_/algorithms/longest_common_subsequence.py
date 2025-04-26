def longest_common_subsequence_length(s1: str, s2: str) -> int:
    """
    This algorithm uses dynamic programming to find the longest common subsequence

    Time and space complexity: O(m * n)
    Where:
    m is the length of string s1
    n is the length of string s2
    """
    m, n = len(s1), len(s2)

    # Initialize the dynamic programming table with zeroes
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    # Fill the table based on the recurrence relation
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i - 1] == s2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    print_matrix(dp, s1, s2)
    return dp[m][n]


def print_matrix(dp, s1, s2):
    print("Dynamic Programming Table (Length of Longest Common Subsequence):")
    print("      " + "  ".join(s2))
    for i in range(len(s1) + 1):
        label = s1[i - 1] if i > 0 else " "
        row = " ".join(f"{val:2}" for val in dp[i])
        print(label + " " + row)


# Example usage
s1 = "abcdeh"
s2 = "afcse"
print("Length of the longest common subsequence:", longest_common_subsequence_length(s1, s2))
