import sys
from collections import defaultdict

input = sys.stdin.readline

if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        N = int(input())

        dic = defaultdict(list)
        for _ in range(N):
            c, kind = map(str, input().split())
            dic[kind].append(c)

        ans = 1
        for val in dic.values():
            ans *= len(val)+1
        print(ans-1)