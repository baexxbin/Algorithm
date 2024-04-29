import sys

input = sys.stdin.readline


if __name__ == '__main__':
    S = list(map(str, input().strip()))
    T = list(map(str, input().strip()))

    # T에서 거꾸로 S 만들기
    while len(T) != len(S):
        if T[-1] == 'A':
            T.pop()
        else:
            T.pop()
            T.reverse()

    print(1) if T == S else print(0)

# 그리디 시간복잡도 어떻게 되는지
