class NonPositiveError(Exception):
    pass


class PositiveList(list):
    def append(self, x):
        if x > 0:
            super().append(x)
        else:
            raise NonPositiveError(x)


npe = PositiveList()
npe.append(1)
npe.append(-1)

