import sys

input = sys.stdin.readline


def palindrome(st, ed, type):
    while st < ed:
        if word[st] != word[ed] and type == 0:
            left = palindrome(st + 1, ed, type + 1)
            right = palindrome(st, ed - 1, type + 1)
            return min(left, right)     # 유사회문으로 잘 가면 1, 아닐 경우 2
        if word[st] != word[ed] and type == 1:
            return 2
        st += 1
        ed -= 1

    return type


if __name__ == '__main__':
    T = int(input())

    for _ in range(T):
        word = input().strip()
        if word == word[::-1] == 0:
            print(0)
            continue
        print(palindrome(0, len(word)-1, 0))