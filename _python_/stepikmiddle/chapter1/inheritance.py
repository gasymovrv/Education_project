class D:
    def method(self):
        print("I'm D method")
class E:
    pass
class B(D, E):  # Множественное наследование
    def method(self):
        print("I'm B method")
class C:
    def method(self):
        print("I'm C method")
class A(B, C):  # Множественное наследование
    pass
class F(B, C):
    # overriding
    def method(self):
        super(B, self).method()  # exclude calling B"s method and bound upper method from D


x = A()

print("Issubclass:")
print(issubclass(A, A))  # True
print(issubclass(C, D))  # False
print(issubclass(A, D))  # True
print(issubclass(C, object))  # True
print(issubclass(object, C))  # False

print("Isinstance:")
print(isinstance(x, A))  # True
print(isinstance(x, B))  # True
print(isinstance(x, object))  # True
print(isinstance(x, str))  # False

# Как определяется порядок поиска атрибутов и методов при множественном наследовании:
print("Method Resolution Order:", A.mro())  # [A, B, D, E, C, object]
x2 = F()
x.method()
x2.method()


class MyList(list):
    def length(self):
        return len(self)

    def pop(self):
        x = super().pop()  # equal to list.pop(self)
        print("I popped:", x)
        return x


x = MyList()
print(x)
x.extend([1, 2, 3, 4, 5, 6])
print(x)
x.append(7)
print(x)
print(x.length())
x.pop()
print(x)


class Base:
    def __init__(self):
        self.val = 0

    def add_one(self):
        self.val += 1

    def add_many(self, x):
        for i in range(x):
            self.add_one()


class Derived(Base):
    def add_one(self):
        self.val += 10

    def __repr__(self):
        return "val=" + str(self.val)


a = Derived()
print(a)
a.add_one()
print(a)

b = Derived()
print(b)
b.add_many(3)
print(b)


class A:
    def foo(self):
        print("A")
class B(A):
    pass
class C(A):
    def foo(self):
        print("C")
class D:
    def foo(self):
        print("D")
class E(B, C, D):
    pass

# Интересный кейс: B и C наследуются от одного и того же класса A и он отодвигается дальше C:
# E, B, C, A, D, object
print("Method Resolution Order:", E.mro())
E().foo()  # C


class A:
    pass
class B(A):
    pass
class C:
    pass
class D(C):
    pass
class E(B, C, D):
    pass

# e = E()  # Cannot create a consistent method resolution order (MRO) for bases object, C, D
# to fix this, we need to swap C and D in the inheritance list or just remove C
