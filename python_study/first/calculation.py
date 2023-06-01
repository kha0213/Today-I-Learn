from math import floor, ceil, sqrt
from random import random, randint, randrange

print('사칙연산 -----------------------------------------')
print(1 + 1)
print(1 - 1)
print(10 / 4) # 2.5

print(2 ** 3)  # 2^3 = 8
print(5 % 3)  # 2 나머지 구하기
print(10 // 3)  # 3 몫 구하기
print(divmod(10,3)) # 몫과 나머지 한 번에 구하기. 튜플로 나온다. (3,1)


# & |
print('& | -----------------------------------------')
print(True & False)
print(True and False)

print(True | False)
print(True or False)

num = 10
print(num)
num += 2
print(num)


# math
print('math -----------------------------------------')
print(abs(-10)) # 절대값
print(pow(4, 2)) # 제곱
print(4 ** 2) # 제곱
print(max(5, 6, 10)) # 최댓값
print(round(3.14)) # 반올림
print(floor(4.92))
print(ceil(4.92))
print(sqrt(4))

# random
print('random -----------------------------------------')
print(random()) # 0.0 ~ 1.0 임의의 값 생성
print(int(random() * 10))
print(randint(1, 45)) # 1이상 45이하의 값 출력
print(randrange(1, 45)) # 1이상 45미만의 값 출력

n1 = n2 = n3 = 5
print(n1,n2,n3)

print('type -----------------------------------------')
print(type(5))
print(type(2.0))
print(type(2.0 + 5))
print(type(5 + 2.0))

# int() 정수로 변환
print('int() -----------------------------------------')
print(int(True)) # 1
print(int(False)) # 0
# int('1 안녕') -> ValueError 타입 에러

# bool() bool로 변환
print('bool() -----------------------------------------')
print(bool(True))
print(bool(0)) # False
print(bool([])) # False