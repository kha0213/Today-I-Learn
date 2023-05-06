from math import floor, ceil, sqrt
from random import random, randint, randrange

print(1 + 1)
print(1 - 1)
print(10 / 4)

print(2 ** 3)  # 2^3 = 8
print(5 % 3)  # 나머지 구하기
print(10 // 3)  # 몫 구하기

# & |
print(True & False)
print(True and False)

print(True | False)
print(True or False)

num = 10
print(num)
num += 2
print(num)

abs(num) # 절대값
pow(4, 2) # 제곱
max(5, 6, 10) # 최댓값
round(3.14) # 반올림

# math
print(floor(4.92))
print(ceil(4.92))
print(sqrt(4))

# random
print(random()) # 0.0 ~ 1.0 임의의 값 생성
print(int(random() * 10))
print(randint(1, 45)) # 1이상 45이하의 값 출력
print(randrange(1, 45)) # 1이상 45미만의 값 출력