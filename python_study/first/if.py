disaster = True
if disaster:
    print("It's True")
else:
    print("It's False")

x = 10
cal = 5 < x < 10
print(cal) # False

vowels = 'aeiou'
letter = 'o'
print(letter in vowels) # True
print('ea' in vowels) # False

secret = 9
guess = 7
if guess > secret:
    print('too low')
elif guess == secret:
    print('just right')
else:
    print('too high')

if (right := len('안녕 하세요')) > 2:
    print(right)
else:
    print(right)