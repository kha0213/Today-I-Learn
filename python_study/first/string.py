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

name = 'Henny'
name2 = name.replace('H','P')
name3 = 'Z' + name[1:]
print('name:' + name + ' name2:' + name2 + ' name3:' + name3) # Henny

print(f'Henny의 len():{len("Henny")}')
print(f'mask,cat,dog의 split(\',\'):{"mask,cat,dog".split(",")}')
print(f'   mask     의 stlip():{"   mask     ".strip()}')
print(f'ab!@#의 stlip(\'!@#\'):{"ab!@#".strip("!@#")}')

poem = '''All that doth flow we cannot liquid name
Or else would fire and water be the same;
But that is liquid which is moist and wet
Fire that property can never get.
Then ’tis not cold that puts the fire3 out
But ’tis the wet that makes it die, no doubt.'''

print(poem[:13])
print(len(poem))
print(poem.startswith('All'))
print(poem.endswith('doubt'))