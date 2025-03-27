# Based on Euclid’s algorithm
def find_largest_squares(a, b):
    min_ = min(a, b)
    max_ = max(a, b)
    new_square = max_ % min_

    if new_square == 0:
        return min_
    else:
        return find_largest_squares(min_, new_square)


print(find_largest_squares(1680, 640))
print(find_largest_squares(50, 25))
print(find_largest_squares(60, 25))
