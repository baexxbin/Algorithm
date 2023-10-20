import math
from collections import deque


def solution(n, k):
    ans = []
    lines = deque([i for i in range(1,n+1)])

    for i in range(n, 0, -1):
        fac = math.factorial(i-1)
        num = lines[(k-1)//fac]
        ans.append(num)
        lines.remove(num)
        k %= fac

    print(ans)


n = 3
k = 5
solution(n, k)

# 나중에 다시 풀기..