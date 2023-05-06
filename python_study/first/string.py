sentence = '나는 소년입니다.'

sentence2 = '파이썬은 쉬워요'

sentence3 = """
나는 소년입니다.
파이썬은 쉬워요
하하
"""

print(sentence)
print(sentence2)
print(sentence3)

juminNo = '890120-1234567'
print('성별 : ' + juminNo[7]) # 8번째 텍스트 가져오기
print('연도 : ' + juminNo[0:2]) # 0포함 2미포함 가져오기
print('생년월일 : ' + juminNo[:6]) # 0생략가능
print('뒤 7자리 : ' + juminNo[7:]) # 생략하면 끝까지
print('뒤 7자리 : ' + juminNo[-7:-1]) # -면 뒤에서

python = 'Python is Amazing'
print(python.lower())
print(python.upper())
print(len(python))
print(python.replace('Ama', 'Pro'))
print(python.index('i'))

