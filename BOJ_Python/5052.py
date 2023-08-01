import sys

input = sys.stdin.readline


if __name__ == '__main__':
    t = int(input())

    for _ in range(t):
        n = int(input())
        lst = [input().strip() for _ in range(n)]
        lst.sort()      # 숫자형태의 문자열은 정렬 시 앞의 문자부터 아스키코드로 비교, 따라서 비슷한 문자열끼리 정렬 됨
        flag = True

        for i in range(n-1):
            length = len(lst[i])
            if lst[i] == lst[i+1][:length]:     # lst[i+1]문자열을 length길이까지만 자름
                flag = False
                break
        print("YES") if flag else print("NO")