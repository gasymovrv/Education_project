import time


class Loggable:
    def log(self, msg):
        print(str(time.ctime()) + ": " + str(msg))


class LoggableList(list, Loggable):
    def append(self, x):
        super().append(x)
        self.log(x)


l = LoggableList()
l.append(1)
l.append(2)
l.append(3)
