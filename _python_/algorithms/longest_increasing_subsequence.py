def length_of_lis(nums: list[int]) -> int:
    """
    Dynamic programming approach to find the length of the longest increasing subsequence
    Complexity: O(n^2)
    There is also a O(n log n) solution using binary search
    """
    if not nums:
        return 0

    n = len(nums)
    dp = [1] * n  # dp[i] = length of LIS ending at index i

    for i in range(1, n):
        for j in range(i):
            if nums[j] < nums[i]:
                dp[i] = max(dp[i], dp[j] + 1)

    return max(dp)


print(length_of_lis([10, 9, 2, 5, 3, 7, 101, 18]))  # 4 (2, 3, 7, 101)
print(length_of_lis([0, 1, 0, 3, 2, 3]))  # 4 (0, 1, 2, 3)
print(length_of_lis([7, 7, 7, 7, 7, 7, 7]))  # 1 (7)
print(length_of_lis([]))  # 0
